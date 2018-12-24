### 系统介绍

* 系统：     Linux centos7（IP：47.94.196.103）
* 语言：     Java
* JDK :      Jdk1.8， 对应目录 /usr/local/jdk1.8.0_181
* Web服务器：Tomcat7，对应目录/usr/local/apache-tomcat-7.0.90
* 后台地址 ：https://mystore.yifoutech.com
* 数据库   ：Mysql5.7（账号：root 密码：sycSYC2018! 端口：3306）

### 部署流程

* 使用putty.exe客户端登录linux系统，HostName：root@47.94.196.103，密码：.ppk私钥文件。
* 将小程序与后台管理源代码打包成war包，小程序对应项目名称：api，后台管理对应项目名称：ROOT（Tomcat根目录）。
* 修改war包名称(小程序后台api.war，后台管理ROOT.war)，使用ftp上传工具（FlashFXP）上传两个war包，放入Tomcat服务器中（toamcat的webapps目录下）。
* 启动Tomcat
    * 查询当前是否有tomcat正在运行（查看tomcat进程命令：ps -ef |grep tomcat），如果有，则杀死tomcat进程（杀死进程命令：kill -9 xxx 【xxx指查看到的tomcat进程号】）。
    * 执行启动命令（在tomcat的bin目录下执行：nohup ./startup.sh &）。
    
