@ECHO OFF

REM ****************************************************************************************
REM *ʹ��ʱ�����û�������RABBIT_HOME����RABBIT_HOME������eclipse�Ĺ���·�������ҵ���������:*
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
REM *rabbit.bat�ļ�������.thrift�ļ���ͬһ��Ŀ¼,��Ϊ�ڶ�ȡ�ļ�ʱֻ��ȡ��ǰ·����thrift�ļ�*
REM ****************************************************************************************

REM ���
IF EXIST "gen-java" (RD /S /Q gen-java >> stdout.txt)
IF EXIST "gen-php" (RD /S /Q gen-php >> stdout.txt)
IF EXIST "gen-cocoa" (RD /S /Q gen-cocoa >> stdout.txt)

REM ��ȡ����Ŀ¼
SET "rabbit_home=%RABBIT_HOME%"
REM eclipse��zhangpai-rsc-thrift��Ŀ��·����������ɵ��ļ����Ƶ��˴���
REM ��ȷ�������rabbit.bat��ˢ����eclipse��zhangpai-rsc-thrift����
SET ECLIPSE_PROJECT_DIR="%rabbit_home%\zhangpai-rsc-thrift\src\main\java"
REM ���ɵ�php�ļ��´������Ŀ¼
SET PHP_FILE_SVN_DIR="%rabbit_home%\zhangpai-thrift-code\trunk\php\application\libraries"
REM ���ɵ�.h,.m�ļ��´������Ŀ¼
SET COCOA_FILE_SVN_DIR="%rabbit_home%\zhangpai-thrift-code\trunk\ios"
REM svn��thrift�ļ����ύĿ¼
SET THRIFT_FILE_SVN_DIR="%rabbit_home%\zhangpai-thrift-code\trunk\thrift"
REM ����SVN·����Ҳ����eclipse·��
SET LOCAL_SVN_THRIFT_DIR="%rabbit_home%\zhangpai-rsc-thrift\src\main\thrift"

SET PROJECT_THRIFT=zhangpai-rsc-thrift
SET FULL_PROJECT_THRIFT=%rabbit_home%\%PROJECT_THRIFT%
SET JAR_DIR=%rabbit_home%\%PROJECT_THRIFT%\target\zhangpai-rsc-thrift.jar


@REM �����ļ���ָ���ļ���
FOR /R %CD% %%i IN (*.thrift) DO (
	thrift-0.9.1.exe --gen java %%i >> error.txt
	thrift-0.9.1.exe --gen php %%i >> error.txt
	thrift-0.9.1.exe --gen cocoa %%i >> error.txt
)

GOTO COPY_JAVA_FILES

REM ����java�ļ���eclipse workspace��thrift�ļ���
:COPY_JAVA_FILES
XCOPY /E /I /Y "gen-java" %ECLIPSE_PROJECT_DIR% >> stdout.txt
GOTO COPY_PHP_FILES

REM ����PHP�ļ���ָ��Ŀ¼
:COPY_PHP_FILES
XCOPY /E /I /Y "gen-php\application\libraries" %PHP_FILE_SVN_DIR% >> stdout.txt
GOTO COPY_COCOA_FILE

REM ����cocoa�ļ���ָ��Ŀ¼
:COPY_COCOA_FILE
XCOPY  /E /I /Y "gen-cocoa" %COCOA_FILE_SVN_DIR% >> stdout.txt
GOTO CLEAN

:CLEAN
REM ���
RD /S /Q gen-java
RD /S /Q gen-php
RD /S /Q gen-cocoa
GOTO COPY_THRIFT_FILE

REM ������SVN·��
:COPY_THRIFT_FILE
XCOPY  /E /I /Y *.thrift %THRIFT_FILE_SVN_DIR% >> stdout.txt
PING 127.0.0.1 -n 2 >> stdout.txt
REM ȡ���˲�����ʹ��ʱ�ܿ�����eclipse�����£��Լ������Լ�û�б�Ҫ
:COPY_THRIFT_FILE
REM XCOPY  /E /I /Y "%LOCAL_SVN_THRIFT_DIR%\*.thrift" %LOCAL_SVN_THRIFT_DIR% >> stdout.txt
GOTO ANDROID

:ANDROID
CALL CD %FULL_PROJECT_THRIFT%
CALL mvn install
GOTO COPY_JAR_FILE

:COPY_JAR_FILE
COPY  /Y %JAR_DIR% %rabbit_home%\zhangpai-thrift-code\trunk\andriod >> stdout.txt


ECHO "��ϲ��ִ�гɹ���"  >> stdout.txt
PAUSE