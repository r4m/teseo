Teseo
=====

This application allows to track multiple agents in a indoor wireless sensor network through an implementation of a variant extended Kalman filter. 

This project was originally hosted at [Sourceforge](http://sourceforge.net/projects/teseus/).

## Getting Started

The programming of the whole system has been addressed through [NesC](http://nescc.sourceforge.net), via [TinyOS 2.1.0](http://docs.tinyos.net/index.php/Installing_TinyOS_2.1), and Java 6. NesC has become indispensable for low-level management of individual agents while Java was chosen to provide the user with a simple and intuitive graphical interface with whom showing and coordinating the localization and tracking.

A detailed explanation of the TinyOS and Java applications can be found in the journal paper [*Teseo: a multi-agent tracking application in 
wireless sensor networks*](http://www.naun.org/multimedia/UPress/saed/2014-108.pdf) published in the International Journal of Systems Applications, Engineering & Development, Issue 1, Volume 7, 2013.

In the following it is briefly explained how to setup the system.

### Mobile agent programming

The first thing to do is to ensure that the mobile agent is the only one connected to the USB port of the computer. Then, to install the module `MobileNodeP.nc`, you have to run the command

	make tmote install.100

from the folder `devices-sensors\MobileNode`, such that the corresponding `Makefile` is loaded. A compiled image for the `telosb` is installed in the mobile agent with assigned ID 100.

### Fixed agents programming

To install the module `AnchorNodeP.nc` into more than one fixed agent at the same time you can run the `sensnet-load` script located in the `\devices-sensors\FixedNode` directory. The scrpit compare the results of the `motelist` command with the content of  the `sensnet-topology` file.
The `sensnet-topology` text file contains, row by row, the ID and the reference of the agent with this convention: `<ID>,<Reference>`.

### Start-up of the GUI

The `make.sh` script sets the`CLASSPATH` and invokes the `Makefile` to compile the Java source code. Then you can run the Java application via the `run.sh` bash script.


## Additional information

In these file are reported some notes that could be useful if you encounter some problems in the use of the Java application.

* If you update the structure of the `data_msg`, `mote_ctrl_msg`, `ping_client_msg` specified in the `TmoteComm.h` file, you need to:
 
  1. copy the modified file `TmoteComm.h` (that one in the NesC) in the folder `src/teseo`;
  
  2. compile files *.java using MIG, through the `Makefile` in the folder `src/teseo`;
  
  3. insert new files *.java in the package teseo, adding inside each file the line `"package teseo"`.
   
* To compile the code, whom files *.class will be placed in the folder `classes`, you need to run the script `make.sh`.

* Folders `maps` and `img` are not automatically copied in the folder `classes` hence if you modify them you have to copy them in the folder `classes`.

* `tinyos.jar` library is imported from the TinyOS source folder hence you need to update it when you update the TinyOS.

* To execute the software, once you have compiled it successfully, you can run it using the script `run.sh`.


## License

Copyright (c) 2010, Department of Information Engineering, University of Padova.
All rights reserved.

This file is part of Teseo.

Teseo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation,
either version 3 of the License, or (at your option) any later version.

Teseo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with Teseo.  If not, see <http://www.gnu.org/licenses/>.


