HEADER_H=TmoteComm.h
BUILD_EXTRA_DEPS += Teseo.class

Teseo.class: $(wildcard *.java) DataMsg.java PingClientMsg.java MoteCtrlMsg.java

DataMsg.java: $(HEADER_H)
	mig -target=null -java-classname=DataMsg java $(HEADER_H) data_msg -o $@

PingClientMsg.java: $(HEADER_H)
	mig -target=null -java-classname=PingClientMsg java $(HEADER_H) ping_client_msg -o $@

MoteCtrlMsg.java: $(HEADER_H)
	mig -target=null -java-classname=MoteCtrlMsg java $(HEADER_H) mote_ctrl_msg -o $@

