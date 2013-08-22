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
 * Configuration file of the module FixedNodeP.nc
 *
 * @date 18/09/2008 11:36
 * @author Filippo Zanella <filippo.zanella@dei.unipd.it>
 */

#include <Timer.h>
//#include <math.h>  // /opt/msp430/msp430/include
#include <UserButton.h>
#include "../TmoteComm.h"

#define DEBUG


configuration FixedNodeC
{
}
implementation
{
  components MainC, LedsC;

  components FixedNodeP as App;

  components new TimerMilliC() as TimeToSend;

  components CC2420ControlC;
  components CC2420PacketC;
//  components CC2420ActiveMessageC;

  components ActiveMessageC;
  	components new AMSenderC(AM_DATA_MSG) as AMSenderData;
  	components new AMReceiverC(AM_PING_NODE_MSG) as AMReceiverPing;
  
//  components RandomC;


  App.Boot         -> MainC.Boot;

  App.Leds         -> LedsC.Leds;

  App.TimeToSend   -> TimeToSend.Timer;

  App.InfoRadio    -> CC2420PacketC;
  App.CC2420Config -> CC2420ControlC.CC2420Config;
//  App.CcaOverride -> CC2420ActiveMessageC.RadioBackoff[AM_DATA_MSG];

  App.AMCtrlRadio  -> ActiveMessageC;
  	App.AMSendData   -> AMSenderData;
  	//App.AMPacketData -> AMSenderData.AMPacket;
  	App.ReceivePing  -> AMReceiverPing;
  
//  App.Random       -> RandomC;
}
