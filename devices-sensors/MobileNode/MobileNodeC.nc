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
 * Configuration file of the module MobileNodeP.nc
 *
 * @date 18/09/2008 11:36
 * @author Filippo Zanella <filippo.zanella@dei.unipd.it>
 */
 
#include <Timer.h> 
#include <math.h>  // /opt/msp430/msp430/include
#include <UserButton.h>
#include "../TmoteComm.h"

//#define DEBUG

configuration MobileNodeC 
{}
implementation 
{
  components MainC, LedsC;
  components MobileNodeP as App;

  components new TimerMilliC() as ClockStep;
  components new TimerMilliC() as ClockSendPingClient;
  components new TimerMilliC() as ClockSendPingNode;
  
  components CC2420ControlC; 
  components CC2420PacketC;
  
  components ActiveMessageC;
  	components new AMSenderC(AM_PING_NODE_MSG) as AMSenderPingNode;
  	components new AMReceiverC(AM_DATA_MSG) as AMReceiverData;
   
  components SerialActiveMessageC;
  	components new SerialAMSenderC(AM_PING_CLIENT_MSG) as SAMSenderPingClient;
  	components new SerialAMSenderC(AM_DATA_MSG) as SAMSenderData;
  	components new SerialAMReceiverC(AM_MOTE_CTRL_MSG) as AMReceiverMoteCtrl;

  components new QueueC(data_msg, QUEUE_DATA_SIZE) as QueueData;

  components UserButtonC;


  App.Boot               -> MainC.Boot;
  App.Leds               -> LedsC.Leds;

  App.ClockSendPingClient    -> ClockSendPingClient.Timer;
  App.ClockStep	       -> ClockStep.Timer;
  App.ClockSendPingNode  -> ClockSendPingNode.Timer;

  App.InfoRadio          -> CC2420PacketC;
  App.CC2420Config       -> CC2420ControlC.CC2420Config;

  App.AMCtrlRadio        -> ActiveMessageC;
  	App.AMSendPingNode     -> AMSenderPingNode;
  	//App.AMPacketPingNode   -> AMSenderPingNode.AMPacket;
  	App.ReceiveData        -> AMReceiverData; 

  App.AMCtrlSerial       -> SerialActiveMessageC;
  	App.SAMSendPingPc      -> SAMSenderPingClient;
  	//App.SAMPacketPingPc    -> SAMSenderPingClient.AMPacket;
  	App.SAMSendData        -> SAMSenderData;
  	//App.SAMPacketData      -> SAMSenderData.AMPacket;
  	App.ReceiveMoteCtrl    -> AMReceiverMoteCtrl;

  App.QueueData          -> QueueData.Queue;

  App.GetBS        -> UserButtonC.Get;
  App.NotifyBS     -> UserButtonC.Notify;
}


