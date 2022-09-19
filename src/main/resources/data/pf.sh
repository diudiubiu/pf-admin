#!/bin/bash
app="pf-admin-0.0.1-Beta.jar"
app_start(){
	echo  "${app}start..."
	nohup java -jar $app &
	echo  "${app}success"
}
app_stop(){
	pid=$(ps -aux | grep  $app | grep -v  grep | awk '{print $2}')
	if [  $pid ];then
		echo   "${app}stopping"
		kill -9  $pid
		echo   "${app}stopped"
	else
		echo "${app}noworking"
	fi
}
# 针对传入参数判断
if [ $1 == "start" ] ; then
	app_start
elif [ $1 == "stop" ] ; then
	app_stop
elif [ $1 == "restart" ] ; then
	app_stop
	app_start
else
	echo "pass：start/stop/restart"
fi