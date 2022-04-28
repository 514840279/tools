FROM java:8
LABEL "MAINTAINER"="514840279@qq.com" 
ADD tools-rollcall.jar tools-rollcall.jar
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "tools-rollcall.jar" ]
#设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
