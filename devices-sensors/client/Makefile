HEADER_H=../TmoteComm.h
BUILD_EXTRA_DEPS += OutputSaver.class

OutputSaver.class: $(wildcard *.java) DataMsg.java PingClientMsg.java
	javac *.java

DataMsg.java: $(HEADER_H)
	mig -target=null -java-classname=DataMsg java $(HEADER_H) data_msg -o $@

PingClientMsg.java: $(HEADER_H)
	mig -target=null -java-classname=PingClientMsg java $(HEADER_H) ping_client_msg -o $@

clean:
	rm -f *.class $(GEN)

