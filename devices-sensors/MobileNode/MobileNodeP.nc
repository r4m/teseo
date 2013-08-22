/*
 * Copyright (c) 2010, Department of Information Engineering, University of Padova.
 * All rights reserved.
 *
 * This file is part of Teseo.
 *
 * Teseo is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Teseo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Teseo.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ===================================================================================
 */

/**
 *
 * Mobile node application.
 *
 * @date 18/09/2008 11:36
 * @author Filippo Zanella <filippo.zanella@dei.unipd.it>
 */

module MobileNodeP
{
  uses
    {
      interface Leds;
      interface Boot;

      interface Timer<TMilli> as ClockStep;
      interface Timer<TMilli> as ClockSendPingClient;
      interface Timer<TMilli> as ClockSendPingNode;       
	interface CC2420Config;
      interface CC2420Packet as InfoRadio;
      
      interface SplitControl as AMCtrlRadio; 
	      interface AMSend as AMSendPingNode;
	      //interface AMPacket as AMPacketPingNode;
	      interface Receive as ReceiveData;

      interface SplitControl as AMCtrlSerial;
	      interface AMSend as SAMSendPingPc;
	      //interface AMPacket as SAMPacketPingPc;
	      interface AMSend as SAMSendData;
	      //interface AMPacket as SAMPacketData;
	      interface Receive as ReceiveMoteCtrl;

      interface Queue<data_msg> as QueueData;

      interface Get<button_state_t> as GetBS;
      interface Notify<button_state_t> as NotifyBS;
    }

  provides command void start();
  provides command void stop();
}
implementation
{
  message_t pingPcPckt;    
  message_t pingNodePckt;  
  message_t dataPckt;      
  //message_t moteCtrlPckt;  
  uint8_t   pcktnodeID;    
  uint8_t   pcktPcID;      
  uint16_t  step; 		  
  bool      lockedRadio;  
  bool      lockedSerial; 
  uint8_t   curState;     
  uint8_t   cmd;	  

  task void sendPingPcMsg();
  task void sendPingNodeMsg();
  task void sendDataMsg();
  
  /****************************** INIT *****************************/

  event void Boot.booted()
  {
    call Leds.set(ZERO);					// !!DEBUG LEDS OFF
    lockedSerial = FALSE;
    lockedRadio = FALSE;
    step = ZERO;
    curState = DO_NOTHING;
    cmd = WAIT_CMD; 

    call CC2420Config.setChannel(CHANNEL_RADIO);
    call CC2420Config.sync(); 

    call NotifyBS.enable();
  }

  event void CC2420Config.syncDone(error_t error)
  {
    if (error == SUCCESS)
    {
      call AMCtrlRadio.start();
      call Leds.led0On();					// !!DEBUG LED 0 ON
    }
    else
      call CC2420Config.sync();
  }

  event void AMCtrlRadio.startDone(error_t error)
  {
    if (error == SUCCESS) 
    {
      call AMCtrlSerial.start();
      call Leds.led1On();                     	        	// !!DEBUG LED 1 ON
    }
    else 
      call AMCtrlRadio.start();
  }

  event void AMCtrlSerial.startDone(error_t error)
  {
    if (error == SUCCESS)
      {
        call Leds.led2On();					// !!DEBUG LED 2 ON
	call InfoRadio.setPower(&pingNodePckt,POWER_RADIO);
      }
    else { call AMCtrlRadio.start(); }
  }

  event void AMCtrlRadio.stopDone(error_t error){} 
 
  event void AMCtrlSerial.stopDone(error_t error) {}


  /****************************** TIMER: ClockStep *****************************/
 
  /* Setting range to send/receive messages during the algorithm execution */
  event void ClockStep.fired()
  {
    pcktnodeID = 1;
    pcktPcID = 1;

    if(step > MAX_STEP_NUM)
      step = 1;
    else
      step++;
      #ifdef DEBUG  
	call Leds.set(ZERO);					// !!DEBUG LEDS OFF
      #endif

    curState = SEND_CLIENT;
    call ClockSendPingClient.startPeriodic(TIMER_SEND_PING_PC);
  }


  /****************************** TIMER: ClockSendPingClient *****************************/

  /* Timing ping_client_msg dispatch */
  event void ClockSendPingClient.fired()
  {
    if(curState==SEND_CLIENT)
      {
	if(pcktPcID <= MAX_PING_PC_MSG)
	  post sendPingPcMsg();
	else
	  {
	    curState=SEND_NODE;
	    call ClockSendPingClient.stop();
            call ClockSendPingNode.startPeriodic(TIMER_SEND_PING_NODE);
	  }
      }
  }


  /************************** SERIALE: SAMSenderPingClient *****************************/  

  /* Sending ping_client_msg to the serial port */    
  task void sendPingPcMsg()
  {
    if(lockedSerial)
      {
	return;
      }
    else
      {
	ping_client_msg* rsm = (ping_client_msg*)call SAMSendPingPc.getPayload(&pingPcPckt, sizeof(ping_client_msg));
	atomic
	  {
	    rsm->pcktID = pcktPcID; 
	    rsm->step = step;
	    rsm->mobileNodeID = TOS_NODE_ID;
	    rsm->power        = call InfoRadio.getPower(&pingNodePckt);
	    rsm->channel      = call CC2420Config.getChannel();
	  }
	if (call SAMSendPingPc.send(AM_BROADCAST_ADDR, &pingPcPckt, sizeof(ping_client_msg)) == SUCCESS) 
	  {
	    lockedSerial = TRUE;
      	    #ifdef DEBUG  
	      call Leds.led1Toggle();				// !!DEBUG LED 1 TOGGLE
            #endif
	  }
      }

    pcktPcID++;
  }

  event void SAMSendPingPc.sendDone(message_t* msg, error_t error) 
  {
    if (&pingPcPckt == msg) 
      {
	lockedSerial = FALSE;
      	    #ifdef DEBUG  
	      call Leds.led2Toggle();				// !!DEBUG LED 2 TOGGLE
            #endif
      }
  }


  /****************************** TIMER: ClockSendPingNode *****************************/

  /* Timing ping_node_msg dispatch */
  event void ClockSendPingNode.fired()
  {
    if(curState==SEND_NODE)
      {
	if(pcktnodeID <= MAX_PING_NODE_MSG)
	  post sendPingNodeMsg();
	else
	  {
	    curState=AUDIT_NODE;
	    call ClockSendPingNode.stop();
	  }
      }
  }


  /****************************** RADIO: AMSenderPingNode *********************************/

  /* Sending ping_node_msg via radio */    
  task void sendPingNodeMsg()
  {
    if(lockedRadio)
      {
	return;
      }
    else
      {
	ping_node_msg* rpm = (ping_node_msg*)call AMSendPingNode.getPayload(&pingNodePckt, sizeof(ping_node_msg));

	atomic
	  {
	    rpm->pcktID = pcktnodeID; 
	  }
	if (call AMSendPingNode.send(AM_BROADCAST_ADDR, &pingNodePckt, sizeof(ping_node_msg)) == SUCCESS) 
	  {
	    lockedRadio = TRUE;
      	    #ifdef DEBUG  
	      call Leds.led1Toggle();				// !!DEBUG LED 1 TOGGLE
            #endif
	  }
      }

    pcktnodeID++;
  }
   
  event void AMSendPingNode.sendDone(message_t* msg, error_t error) 
  {
    if (&pingNodePckt == msg) 
      {
	lockedRadio = FALSE;
      }
  }


  /****************************** RADIO: AMReceiverData *********************************/

  /* Signaling data_msg reception */
  event message_t* ReceiveData.receive(message_t* msg, void* payload, uint8_t len)
  {
    data_msg pktToSerial;
    int8_t rss;
    uint8_t lqi;

    if(curState==AUDIT_NODE)
      {
	if (len == sizeof(data_msg))
	  {
	    data_msg* receivedPckt = (data_msg*)payload;
	    atomic
	      {    
		rss  = call InfoRadio.getRssi(msg) + RSSI_OFFSET;
	        lqi  = call InfoRadio.getLqi(msg);
	      } 

	    if(rss > RSS_BOUND && lqi > LQI_BOUND)
	      {
	    	atomic
	      	  {
		     pktToSerial.fixedNodeID    = receivedPckt->fixedNodeID;
		     pktToSerial.pcktID     = receivedPckt->pcktID;	
		     pktToSerial.rss       = rss;
		     pktToSerial.lqi       = lqi;
		     //pktToSerial.power     = receivedPckt->power;
		     //pktToSerial.channel   = receivedPckt->channel;
	          }
     
	        if((call QueueData.size() < QUEUE_DATA_SIZE))
	          {	    
		     call QueueData.enqueue(pktToSerial);
      	    	     #ifdef DEBUG  
	               call Leds.led0Toggle();			// !!DEBUG LED 0 TOGGLE
                     #endif
	      	  }
	        
		post sendDataMsg();  
	      }   
	 } 
      }
    return msg; 
  }


  /************************** SERIALE: SAMSenderData *****************************/  

  /* Sending data_msg to serial port */    
  task void sendDataMsg()
  {
    if(!(call QueueData.empty()))
      {
	if(lockedSerial)
	  {
	    return;
	  }
	else
	  {	
	    data_msg toSend = call QueueData.dequeue();
	    data_msg* rsm = (data_msg*)call SAMSendData.getPayload(&dataPckt, sizeof(data_msg));
	    atomic
	      {
		rsm->fixedNodeID   = toSend.fixedNodeID;
		rsm->pcktID    = toSend.pcktID;
		rsm->rss      = toSend.rss;
		rsm->lqi      = toSend.lqi;
	      }
	    if (call SAMSendData.send(AM_BROADCAST_ADDR, &dataPckt, sizeof(data_msg)) == SUCCESS)
	      {
		lockedSerial = TRUE;
	      }
	  }
      }
  }
 
  event void SAMSendData.sendDone(message_t* msg, error_t error) 
  {
    if (&dataPckt == msg) 
      {
	lockedSerial = FALSE;
      	#ifdef DEBUG  
	  call Leds.led2Toggle();				// !!DEBUG LED 2 TOGGLE
        #endif
      }
  }


  /************************** SERIALE: AMReceiverMoteCtrl *****************************/ 

  /* Signaling mote_ctrl_msg reception */
  event message_t* ReceiveMoteCtrl.receive(message_t* msg, void* payload, uint8_t len) 
  {
    if (len == sizeof(mote_ctrl_msg)) 
     {
	mote_ctrl_msg* rcm = (mote_ctrl_msg*)payload;

	uint8_t newCmd = rcm->work;
      	#ifdef DEBUG  
	  //call Leds.set(newCmd);				// !!DEBUG LEDS ?
        #endif		
        curState = DO_NOTHING;

	if(cmd!=newCmd)
	{
	switch(newCmd)
	{
	  case START_MN:
	    {
	      call start();
	      cmd = newCmd;
	      break;
	    }
	  case STOP_MN:
	    {
              call stop();  
	      cmd = newCmd;
	      break;
	    }
	  default:
              cmd = WAIT_CMD;
	  }
	}
    }
    return msg;
  }


  /***************************** USER BUTTON **************************/

  /* Signaling user button push */
  event void NotifyBS.notify(button_state_t state)
  {
    uint8_t curCmd = cmd;
    curState = DO_NOTHING;

    if (state == BUTTON_PRESSED)
      {     
	  if(curCmd==STOP_MN || curCmd==WAIT_CMD)
	    {
	      call start();
	      cmd = START_MN;
	    }
	  else if(curCmd==START_MN)
	    {
              call stop();	  
	      cmd = STOP_MN;
	    }
      } 
    else if (state == BUTTON_RELEASED) {}
  }

  
  /***************************** PROVIDES: start **************************/
  command void start()
  {
    call ClockStep.startPeriodic(TIMER_STEP);
  }


  /***************************** PROVIDES: stop **************************/
  command void stop()
  {
    call ClockSendPingClient.stop();
    call ClockSendPingNode.stop();
    call ClockStep.stop();
    if(!(call QueueData.empty()))
    {	    
      call QueueData.dequeue();
    }  
    step = ZERO;
    #ifdef DEBUG  
      call Leds.set(111);				// !!DEBUG LEDS ON
    #endif	
  }
}
