@echo off
REM 불멍 감정 소각장 실행 스크립트
REM Gradle/앱 모두 JDK 21로 구동 (시스템 기본 JDK가 11이라 반드시 지정해야 함)
set "JAVA_HOME=C:\Users\ibank\.jdks\jdk-21.0.11+10"
echo Using JAVA_HOME=%JAVA_HOME%
call gradlew.bat bootRun
