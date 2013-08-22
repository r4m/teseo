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
 * Header file.
 *
 * @date 18/09/2008 11:36
 * @author Filippo Zanella <filippo.zanella@dei.unipd.it>
 */


#ifndef TMOTE_COMM_H
#define TMOTE_COMM_H

/* Messagge send by mobile node to PC */
typedef nx_struct ping_client_msg
{
  nx_uint8_t pcktID;          // ID of packet [n-th]
  nx_uint16_t step;  // Number of step[n-th]
  nx_uint8_t mobileNodeID;   // ID of mobile node
  nx_uint8_t power;          // Transmission power of mobile node
  nx_uint8_t channel;        // Transmission channel of mobile node

} ping_client_msg;

/* Messagge sent by mobile node to fixed nodes */
typedef nx_struct ping_node_msg
{
  nx_uint8_t pcktID;   // ID of packet [n-th]
} ping_node_msg;

/* Messagge sent by fixed nodes to mobile node */
typedef nx_struct data_msg
{
  nx_uint8_t fixedNodeID;      // ID of fixed node
  nx_uint8_t pcktID;        // ID of packet [n-th]
  nx_int8_t  rss;          // RSS [dBm] received signal strength indicator (fixed node -> mobile node)
  nx_uint8_t  lqi;         // LQI [dBm] link quality indicator (fixed node -> mobile node)
} data_msg;

/* Messagge sent by PC to mobile node */
typedef nx_struct mote_ctrl_msg
{
  nx_uint8_t work;  // Start/stop of mote
} mote_ctrl_msg;

typedef enum
  {
    IDLE = 4,          // Idle state of fixed nodes
    TRANSMISSION,      //Send data_msg to mobile node
  } state_a_t;

typedef enum
  {
    SEND_CLIENT = 4,   // Send ping_client_msg
    SEND_NODE,     // Send ping_node_msg
    AUDIT_NODE,    // Audit data_msg  
    DO_NOTHING,    // Nothing to do
    START_MN = 0xA,    // Start mobile node
    STOP_MN = 0xB,     // Stop mobile node
    WAIT_CMD = 0xC,    // Wait a command from the user
  } state_m_t;

enum
  {
    CHANNEL_RADIO = 14,        // Channel radio
    POWER_RADIO = 31,         // Power of radio chip CC2420 [dBm]

    AM_PING_CLIENT_MSG = 83,      // ID of ping_client_msg sent by mobile node to PC
    AM_PING_NODE_MSG = 84,    // ID of ping_node_msg sent by mobile node to fixed nodes
    AM_DATA_MSG = 85,         // ID of data_msg sent by fixed nodes to mobile node then redirected to PC
    AM_MOTE_CTRL_MSG = 86,    // ID of mote_ctrl_msg sent by PC to mobile node

    RSSI_OFFSET = -45,        // [dBm] !!EMPIRICAL!! Offset of RSSI
    RSS_BOUND = -75,          // [dBm] !!EMPIRICAL!! Lower bound of RSS 
    LQI_BOUND = 90,          // [dBm] !!EMPIRICAL!! Lowe bound of LQI

    TIMER_STEP = 250,     // [ms] Sending period of ping_node_msg to fixed nodes
    TIMER_TRANSMISSION = 15,   // [ms] Sending period od data_msg to fixed nodes
    TIMER_SEND_PING_PC =  2,   // [ms] Clock to send ping_client_msg to PC
    TIMER_SEND_PING_NODE = 10, // [ms] Clock to send ping_node_msg to fixed nodes
    
	MAX_PING_PC_MSG = 1,      // Number max of ping to send to PC
    MAX_PING_NODE_MSG = 4,    // Number max of ping to send to fixed nodes
  
    QUEUE_DATA_SIZE = 40,     // FIFO stack dimension for data_msg
    
    MAX_STEP_NUM = 4096, // Maximum number of step (to avoid overflow)
    ZERO = 0,                 // Zero
  };

#endif
