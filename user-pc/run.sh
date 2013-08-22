#!/bin/sh
CLASSPATH=":build/classes/:build/classes/teseo/lib/swing-layout-1.0.3.jar:build/classes/teseo/lib/tinyos.jar:build/classes/teseo/lib/Jama-1.0.2.jar:$CLASSPATH"
#java -verbose:jni teseo.Teseo
java -splash:build/classes/teseo/img/splash.jpg teseo.Teseo
