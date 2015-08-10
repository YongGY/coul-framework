set curp=%~dp0

pushd %curp%

set MAVEN_OPTS=-Xms1280m -Xmx1376m  -XX:MaxPermSize=128m

mvn -Dmaven.test.skip=true clean install
