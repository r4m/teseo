Teseo
=====

This application allows to track multiple agents in a indoor wireless sensor network through an implementation of a variant extended Kalman filter. The programming of the whole system has been addressed through NesC and Java. NesC has become indispensable for low-level management of individual agents while Java was chosen to provide the user with a simple and intuitive graphical interface with whom showing and coordinating the localization and tracking.

This project was originally hosted at [Sourceforge](http://sourceforge.net/projects/teseus/).

## Getting Started

Run the file `YourTubes.m` to start the application.


## Additional information

In these file are reported some notes that could be useful if you encounter some problems in the use of the Java application.

* If you update the structure of the `data_msg`, `mote_ctrl_msg`, `ping_client_msg` specified in the `TmoteComm.h` file, you need to:

   1. copy the modified file `TmoteComm.h` (that one in the NesC) in the folder `src/teseo`;
   
   2. compile files *.java using MIG, through the `Makefile` in the folder `src/teseo`;
  
   3. insert new files *.java in the package teseo, adding inside each file the line `"package teseo"`;
   
* To compile the code, whom files *.class will be placed in the folder `classes`, you need to run the script `make.sh`

* Folders `maps` and `img` are not automatically copied in the folder `classes` hence if you modify them you have to copy them in the folder `classes`.

* `tinyos.jar` library is imported from the TinyOS source folder hence you need to update it when you update the TinyOS

* To execute the software, once you have compiled it successfully, you can run it using the script `run.sh`.


## License

Copyright (c) 2010, Department of Information Engineering, University of Padova.
All rights reserved.

This file is part of Teseo.

Teseo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation,
either version 3 of the License, or (at your option) any later version.

Teseo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with Teseo.  If not, see <http://www.gnu.org/licenses/>.


