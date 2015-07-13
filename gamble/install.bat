echo [INFO]开始打本地包...

call mvn clean install -Dmaven.test.skip=true -e

echo [INFO]打包结束

pause