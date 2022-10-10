FROM runcare/openjdk-jre1.8:latest
#设置时区
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
COPY tools-jijin-1.0.jar tools-jijin-1.0.jar
CMD [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "tools-jijin-1.0.jar" ]