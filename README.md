Teseo
=====

This application allows to track multiple agents in a indoor wireless sensor network through an implementation of a variant extended Kalman filter. 

This project was originally hosted at [Sourceforge](http://sourceforge.net/projects/teseus/).



## Getting Started

The programming of the whole system has been addressed through NesC, via [TinyOS 2.1.0](http://docs.tinyos.net/index.php/Installing_TinyOS_2.1), and Java 6. NesC has become indispensable for low-level management of individual agents while Java was chosen to provide the user with a simple and intuitive graphical interface with whom showing and coordinating the localization and tracking.

A detailed explanation of the TinyOS and Java applications can be found in the journal paper [*Teseo: a multi-agent tracking application in 
wireless sensor networks*](http://www.naun.org/multimedia/UPress/saed/2014-108.pdf) published in the International Journal of Systems Applications, Engineering & Development, Issue 1, Volume 7, 2013.

In the following it is briefly explained how to setup the system.

### Mobile agent programming

The first thing to is to ensure that the mobile agent is the only one connected to the USB port of the notebook/pc. Then, to install the module `MobileNodeP.nc`, dalla cartella che contiene il \verb|Makefile| relativo a \verb|MobileNodeC.nc|  basta eseguire il comando
\begin{verbatim}
make tmote install.100
\end{verbatim}
verrà compilata una immagine per la piattaforma \verb|telosb| e installata nel nodo mobile assegnando ad esso l'ID $100$.

\subsubsection{Programmazione dei nodi fissi}
Per installare il modulo \verb|AnchorNodeP.nc| su più nodi fissi contemporaneamente si può agire direttamente dalla \emph{base station}. Altrimenti, procedendo in maniera manuale come per il nodo mobile, può essere utilizzato lo script denominato \verb|sensnet-load|:
\begin{verbatim}
#!/bin/sh
motelist -c | while read line
do
	code=`echo $line | cut -d "," -f 1`
	dev=`echo $line | cut -d "," -f 2`
	cat sensnet-topology | while read t_line
		do
			t_address=`echo $t_line |cut -d "," -f 1`
			t_code=`echo $t_line |cut -d "," -f 2`
			if [ $code = $t_code ];
			then
				echo "$code, $t_address, $dev"
				make telosb reinstall,$t_address bsl,$dev >/dev/null 2>/dev/null &
			fi	
		done
done
\end{verbatim}
Questo \emph{script} confronta quanto restituito dal comando \verb|motelist| con quanto contenuto nel file \verb|sensnet-topology|.
Il file \verb|sensnet-topology| è un file di testo che contiene, riga per riga, l'ID e la \verb|Reference| del mote secondo tale sintassi: \verb|<ID>,<Reference>|. Lo script dunque non fa altro che reinstallare il software in tutti quei nodi fissi che sono presenti si nella \verb|motelist| che nell'elenco \verb|sensnet-topology|.

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


