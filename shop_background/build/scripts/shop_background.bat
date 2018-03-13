@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  shop_background startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and SHOP_BACKGROUND_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\jackson-module-jaxb-annotations-2.8.10.jar;%APP_HOME%\lib\cxf-rt-rs-client-3.2.0.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-1.5.9.RELEASE.jar;%APP_HOME%\lib\tomcat-juli-8.5.23.jar;%APP_HOME%\lib\shiro-config-core-1.4.0.jar;%APP_HOME%\lib\spring-core-4.3.13.RELEASE.jar;%APP_HOME%\lib\stax-api-1.0.1.jar;%APP_HOME%\lib\jackson-databind-2.8.10.jar;%APP_HOME%\lib\shiro-crypto-cipher-1.4.0.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.25.jar;%APP_HOME%\lib\logback-classic-1.1.11.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\hawtbuf-1.11.jar;%APP_HOME%\lib\mybatis-spring-boot-starter-1.3.0.jar;%APP_HOME%\lib\shiro-event-1.4.0.jar;%APP_HOME%\lib\shop_background-1.0-SNAPSHOT.jar;%APP_HOME%\lib\spring-tx-4.3.13.RELEASE.jar;%APP_HOME%\lib\cxf-rt-frontend-jaxrs-3.2.0.jar;%APP_HOME%\lib\shiro-crypto-core-1.4.0.jar;%APP_HOME%\lib\spring-context-support-4.3.13.RELEASE.jar;%APP_HOME%\lib\commons-codec-1.10.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\httpcore-4.4.8.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.25.jar;%APP_HOME%\lib\poi-ooxml-schemas-3.11.jar;%APP_HOME%\lib\tomcat-embed-core-8.5.23.jar;%APP_HOME%\lib\tomcat-embed-el-8.5.23.jar;%APP_HOME%\lib\commons-pool2-2.4.3.jar;%APP_HOME%\lib\cxf-spring-boot-autoconfigure-3.2.0.jar;%APP_HOME%\lib\quartz-2.2.1.jar;%APP_HOME%\lib\hibernate-validator-5.3.6.Final.jar;%APP_HOME%\lib\hamcrest-core-1.3.jar;%APP_HOME%\lib\activemq-broker-5.14.5.jar;%APP_HOME%\lib\jxl-2.6.10.jar;%APP_HOME%\lib\spring-context-4.3.13.RELEASE.jar;%APP_HOME%\lib\shiro-spring-1.4.0.jar;%APP_HOME%\lib\jboss-logging-3.3.1.Final.jar;%APP_HOME%\lib\mysql-connector-java-5.1.35.jar;%APP_HOME%\lib\geronimo-j2ee-management_1.1_spec-1.0.1.jar;%APP_HOME%\lib\spring-webmvc-4.3.13.RELEASE.jar;%APP_HOME%\lib\cxf-spring-boot-starter-jaxrs-3.2.0.jar;%APP_HOME%\lib\tomcat-embed-websocket-8.5.23.jar;%APP_HOME%\lib\shiro-web-1.4.0.jar;%APP_HOME%\lib\commons-io-1.3.2.jar;%APP_HOME%\lib\commons-beanutils-1.9.3.jar;%APP_HOME%\lib\spring-boot-starter-1.5.9.RELEASE.jar;%APP_HOME%\lib\shiro-cache-1.4.0.jar;%APP_HOME%\lib\spring-boot-devtools-1.5.9.RELEASE.jar;%APP_HOME%\lib\poi-ooxml-3.11.jar;%APP_HOME%\lib\xmlbeans-2.6.0.jar;%APP_HOME%\lib\shiro-lang-1.4.0.jar;%APP_HOME%\lib\spring-jdbc-4.3.13.RELEASE.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.8.5.jar;%APP_HOME%\lib\spring-boot-starter-cache-1.5.9.RELEASE.jar;%APP_HOME%\lib\shiro-core-1.4.0.jar;%APP_HOME%\lib\cxf-core-3.2.0.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-1.5.9.RELEASE.jar;%APP_HOME%\lib\activemq-openwire-legacy-5.14.5.jar;%APP_HOME%\lib\spring-boot-starter-logging-1.5.9.RELEASE.jar;%APP_HOME%\lib\logback-core-1.1.11.jar;%APP_HOME%\lib\stax2-api-3.1.4.jar;%APP_HOME%\lib\xmlschema-core-2.2.2.jar;%APP_HOME%\lib\spring-aop-4.3.13.RELEASE.jar;%APP_HOME%\lib\tomcat-annotations-api-8.5.23.jar;%APP_HOME%\lib\spring-messaging-4.3.13.RELEASE.jar;%APP_HOME%\lib\mybatis-3.4.4.jar;%APP_HOME%\lib\spring-expression-4.3.13.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-activemq-1.5.9.RELEASE.jar;%APP_HOME%\lib\c3p0-0.9.1.1.jar;%APP_HOME%\lib\snakeyaml-1.17.jar;%APP_HOME%\lib\log4j-1.2.14.jar;%APP_HOME%\lib\junit-4.12.jar;%APP_HOME%\lib\jackson-core-2.8.10.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.25.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\jedis-2.9.0.jar;%APP_HOME%\lib\spring-beans-4.3.13.RELEASE.jar;%APP_HOME%\lib\cxf-rt-transports-http-3.2.0.jar;%APP_HOME%\lib\shop_util-1.0-SNAPSHOT.jar;%APP_HOME%\lib\mybatis-spring-1.3.1.jar;%APP_HOME%\lib\spring-boot-1.5.9.RELEASE.jar;%APP_HOME%\lib\pinyin4j-2.5.0.jar;%APP_HOME%\lib\shiro-config-ogdl-1.4.0.jar;%APP_HOME%\lib\log4j-api-2.7.jar;%APP_HOME%\lib\mybatis-spring-boot-autoconfigure-1.3.0.jar;%APP_HOME%\lib\javax.annotation-api-1.3.jar;%APP_HOME%\lib\spring-boot-starter-web-1.5.9.RELEASE.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.8.10.jar;%APP_HOME%\lib\classmate-1.3.4.jar;%APP_HOME%\lib\itext-asian-5.2.0.jar;%APP_HOME%\lib\log4j-core-2.7.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\jna-3.0.9.jar;%APP_HOME%\lib\spring-jms-4.3.13.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-1.5.9.RELEASE.jar;%APP_HOME%\lib\itextpdf-5.2.0.jar;%APP_HOME%\lib\jackson-annotations-2.8.0.jar;%APP_HOME%\lib\geronimo-jms_1.1_spec-1.1.1.jar;%APP_HOME%\lib\httpclient-4.5.3.jar;%APP_HOME%\lib\shiro-crypto-hash-1.4.0.jar;%APP_HOME%\lib\poi-3.11.jar;%APP_HOME%\lib\activemq-client-5.14.5.jar;%APP_HOME%\lib\woodstox-core-5.0.3.jar;%APP_HOME%\lib\spring-web-4.3.13.RELEASE.jar;%APP_HOME%\lib\tomcat-jdbc-8.5.23.jar;%APP_HOME%\lib\javax.ws.rs-api-2.1.jar

@rem Execute shop_background
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SHOP_BACKGROUND_OPTS%  -classpath "%CLASSPATH%" com.background.SpringBootBackgrouondMainClass %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SHOP_BACKGROUND_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SHOP_BACKGROUND_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
