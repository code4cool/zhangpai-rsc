@echo off


thrift-0.9.1.exe --gen java  *.thrift
PING 127.0.0.1 -n 5
thrift-0.9.1.exe --gen php  *.thrift
PING 127.0.0.1 -n 5
thrift-0.9.1.exe --gen cocoa  *.thrift


pause