@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  kotlinzen startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and KOTLINZEN_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\kotlinzen-1.0.0-SNAPSHOT.jar;%APP_HOME%\lib\vertx-web-openapi-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-web-validation-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-web-client-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-web-openapi-router-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-web-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-pg-client-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-lang-kotlin-coroutines-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-lang-kotlin-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-reactive-streams-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\kotlinx-coroutines-core-jvm-1.6.4.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.7.21.jar;%APP_HOME%\lib\client-2.1.jar;%APP_HOME%\lib\vertx-config-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-web-common-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-auth-common-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-bridge-common-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-uri-template-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-openapi-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-json-schema-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-sql-client-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\vertx-core-4.5.4-SNAPSHOT.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.106.Final.jar;%APP_HOME%\lib\netty-codec-http2-4.1.106.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.106.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.106.Final.jar;%APP_HOME%\lib\netty-handler-4.1.106.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.106.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.106.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.106.Final.jar;%APP_HOME%\lib\netty-codec-4.1.106.Final.jar;%APP_HOME%\lib\netty-transport-4.1.106.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.106.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.106.Final.jar;%APP_HOME%\lib\netty-common-4.1.106.Final.jar;%APP_HOME%\lib\jackson-core-2.16.1.jar;%APP_HOME%\lib\snakeyaml-2.0.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.7.21.jar;%APP_HOME%\lib\kotlin-stdlib-1.7.21.jar;%APP_HOME%\lib\common-2.1.jar;%APP_HOME%\lib\reactive-streams-1.0.3.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.7.21.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\saslprep-1.1.jar;%APP_HOME%\lib\stringprep-1.1.jar


@rem Execute kotlinzen
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %KOTLINZEN_OPTS%  -classpath "%CLASSPATH%" io.vertx.core.Launcher %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable KOTLINZEN_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%KOTLINZEN_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
