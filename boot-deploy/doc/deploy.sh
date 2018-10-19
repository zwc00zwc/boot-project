#!/bin/bash

tomcat_home="/usr/local/apache-tomcat-9.0.12"
tomcat_port=8080
tomcat_pid="lsof -n -P -t -i :${tomcat_port}"

war_path="${tomcat_home}/webapps/openlottery-web/"
war_filename="${tomcat_home}/webapps/openlottery-web.war"

if [ -n "${tomcat_pid}" ]; then
	${tomcat_home}/bin/shutdown.sh

	while [ -n "${tomcat_pid}" ]
	
	do
		sleep 1
		tomcat_pid=`lsof -n -P -t -i :${tomcat_port}`
		echo "正在关闭Tomcat["${tomcat_port}"]..."
	done
	echo "成功关闭tomcat"
fi

#如果文件或者文件夹存在则删除
deleteWhenExist(){
 if [ -e $1 ]; then
  rm -rf $1
 fi
}

deleteWhenExist #{war_path}
deleteWhenExist ${war_path}

#拷贝新编译的包到Tomcat
cp /usr/local/openlottery-project/openlottery-web.war  ${tomcat_home}/webapps/

${tomcat_home}/bin/startup.sh

echo "正在启动tomcat["${tomcat_port}"]..."


#检测Tomcat是否启动完成
while [ -z "${tomcat_pid}" ]
do
 sleep 1
 #echo "TOMCAT_PID["${TOMCAT_PID}"]"
	tomcat_pid=`lsof -n -P -t -i :${tomcat_port}`
 echo "正在启动Tomcat["${tomcat_port}"]..."
done
 
echo "成功启动Tomcat."
