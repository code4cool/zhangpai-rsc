@ECHO OFF

REM ****************************************************************************************
REM *使用时先配置环境变量RABBIT_HOME，在RABBIT_HOME里配置eclipse的工程路径，如我的配置如下:*
REM *RABBIT_HOME=D:\Work\zhangpai\zhangpai-rsc                                                         *
REM *D:\Work\zhangpai\zhangpai-rsc									   *
REM *                \zhangpai-rabbit-framework							   *
REM *		     \zhangpai-rsc-consumer								   *
REM *                .									   *
REM *                .									   *
REM *                .									   *
REM *                \zhangpai-rsc-service						   *
REM *                .									   *
REM *                .									   *
REM *                .									   *
REM *                \zhangpai-rsc-thrift							   *
REM *rabbit.bat文件必须与.thrift文件在同一个目录,因为在读取文件时只获取当前路径的thrift文件*
REM ****************************************************************************************

REM 清除
IF EXIST "gen-java" (RD /S /Q gen-java >> stdout.txt)
IF EXIST "gen-php" (RD /S /Q gen-php >> stdout.txt)
IF EXIST "gen-cocoa" (RD /S /Q gen-cocoa >> stdout.txt)

REM 获取工程目录
SET "rabbit_home=%RABBIT_HOME%"
REM eclipse中zhangpai-rsc-thrift项目的路径，会把生成的文件复制到此处，
REM 正确运行完成rabbit.bat后，刷新下eclipse的zhangpai-rsc-thrift工程
SET ECLIPSE_PROJECT_DIR="%rabbit_home%\zhangpai-rsc-thrift\src\main\java"
REM 生成的php文件会拷贝到此目录
SET PHP_FILE_SVN_DIR="%rabbit_home%\zhangpai-thrift-code\trunk\php\application\libraries"
REM 生成的.h,.m文件会拷贝到此目录
SET COCOA_FILE_SVN_DIR="%rabbit_home%\zhangpai-thrift-code\trunk\ios"
REM svn中thrift文件的提交目录
SET THRIFT_FILE_SVN_DIR="%rabbit_home%\zhangpai-thrift-code\trunk\thrift"
REM 本地SVN路径，也就是eclipse路径
SET LOCAL_SVN_THRIFT_DIR="%rabbit_home%\zhangpai-rsc-thrift\src\main\thrift"

SET PROJECT_THRIFT=zhangpai-rsc-thrift
SET FULL_PROJECT_THRIFT=%rabbit_home%\%PROJECT_THRIFT%
SET JAR_DIR=%rabbit_home%\%PROJECT_THRIFT%\target\zhangpai-rsc-thrift.jar


@REM 复制文件到指定文件夹
FOR /R %CD% %%i IN (*.thrift) DO (
	thrift-0.9.1.exe --gen java %%i >> error.txt
	thrift-0.9.1.exe --gen php %%i >> error.txt
	thrift-0.9.1.exe --gen cocoa %%i >> error.txt
)

GOTO COPY_JAVA_FILES

REM 拷贝java文件到eclipse workspace的thrift文件夹
:COPY_JAVA_FILES
XCOPY /E /I /Y "gen-java" %ECLIPSE_PROJECT_DIR% >> stdout.txt
GOTO COPY_PHP_FILES

REM 拷贝PHP文件到指定目录
:COPY_PHP_FILES
XCOPY /E /I /Y "gen-php\application\libraries" %PHP_FILE_SVN_DIR% >> stdout.txt
GOTO COPY_COCOA_FILE

REM 拷贝cocoa文件到指定目录
:COPY_COCOA_FILE
XCOPY  /E /I /Y "gen-cocoa" %COCOA_FILE_SVN_DIR% >> stdout.txt
GOTO CLEAN

:CLEAN
REM 清除
RD /S /Q gen-java
RD /S /Q gen-php
RD /S /Q gen-cocoa
GOTO COPY_THRIFT_FILE

REM 拷贝到SVN路径
:COPY_THRIFT_FILE
XCOPY  /E /I /Y *.thrift %THRIFT_FILE_SVN_DIR% >> stdout.txt
PING 127.0.0.1 -n 2 >> stdout.txt
REM 取消此操作，使用时很可能在eclipse工程下，自己复制自己没有必要
:COPY_THRIFT_FILE
REM XCOPY  /E /I /Y "%LOCAL_SVN_THRIFT_DIR%\*.thrift" %LOCAL_SVN_THRIFT_DIR% >> stdout.txt
GOTO ANDROID

:ANDROID
CALL CD %FULL_PROJECT_THRIFT%
CALL mvn install
GOTO COPY_JAR_FILE

:COPY_JAR_FILE
COPY  /Y %JAR_DIR% %rabbit_home%\zhangpai-thrift-code\trunk\andriod >> stdout.txt


ECHO "恭喜，执行成功。"  >> stdout.txt
PAUSE