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
 * Fixed node application.
 *
 * @date 18/09/2008 11:36
 * @author Filippo Zanella <filippo.zanella@dei.unipd.it>
 */

module FixedNodeP
{
  uses
    {
      interface Boot;
      interface Leds;

      interface Timer<TMilli> as TimeToSend;

      interface CC2420Config;
      interface CC2420Packet as InfoRadio;

      interface SplitControl as AMCtrlRadio;
      		interface AMSend as AMSendData; 
      		//interface AMPacket as AMPacketData;
		interface Receive as ReceivePing;

//      interface Random;
    }
}
implementation
{
  message_t dataPckt;    
  bool lockedRadio;     
  uint8_t curState;     
  uint8_t maxDataMsg;   

  uint8_t pcktMobileID;  
  uint8_t failSendPckt; 

  task void sendDataMsg();

  /******************************* INIT ******************************/

  event void Boot.booted()
  {
    call Leds.set(ZERO);					// !!DEBUG LEDS OFF
    lockedRadio = FALSE;
    curState = IDLE;
  
    call CC2420Config.setChannel(CHANNEL_RADIO);
    call CC2420Config.sync();     
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
      call InfoRadio.setPower(&dataPckt,POWER_RADIO);
      call Leds.led1On();                             		// !!DEBUG LED 1 ON
    }
    else
      call AMCtrlRadio.start();
  }

  event void AMCtrlRadio.stopDone(error_t error){}


  /****************************** RADIO: AMReceiverPing *********************************/

  /* Signaling ping_node_msg reception */
  event message_t* ReceivePing.receive(message_t* msg, void* payload, uint8_t len)
  {
    if(curState==IDLE)
      {
	if (len == sizeof(ping_node_msg))
	  {
	    ping_node_msg* receivedPckt = (ping_node_msg*)payload;
	    
            pcktMobileID = ZERO;

	    atomic
	      {    
		maxDataMsg = floor((TIMER_STEP - (TIMER_SEND_PING_NODE * receivedPckt->pcktID))/ TIMER_TRANSMISSION);	
	      }     
	    	    
	    call TimeToSend.startPeriodic(TIMER_TRANSMISSION); 
            curState = TRANSMISSION;
	  }
      }
    return msg;
  }


  /****************************** TIMER: TimeToSend *****************************/

  /* Starting to send data to mobile node */
  event void TimeToSend.fired()
  {
	
        if (pcktMobileID<=maxDataMsg)
          {
	    post sendDataMsg();
          }
        else
          {
            curState = IDLE;
            lockedRadio = FALSE;
            call TimeToSend.stop();
          }
  }


  /****************************** RADIO: AMSenderData *********************************/

  task void sendDataMsg()
  {
    pcktMobileID++;

    if (!lockedRadio)
      {
	data_msg* rdm = (data_msg*)(call AMSendData.getPayload(&dataPckt, sizeof(data_msg)));
	rdm->fixedNodeID    = TOS_NODE_ID; 
	rdm->pcktID     = pcktMobileID;
	rdm->rss       = ZERO;
	rdm->lqi       = ZERO;	
	if (call AMSendData.send(AM_BROADCAST_ADDR, &dataPckt, sizeof(data_msg)) == SUCCESS) 
	  {
	    lockedRadio = TRUE;
	  }
	else
	   failSendPckt++; 
      }
  }

  event void AMSendData.sendDone(message_t* msg, error_t error)
  {
    if (&dataPckt == msg) 
      {
	lockedRadio = FALSE;
      }

    #ifdef DEBUG  
    	call Leds.led2Toggle();                           	 	// !!DEBUG LED 2 TOGGLE
    #endif
  }
}
