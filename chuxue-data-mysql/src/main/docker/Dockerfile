FROM runcare/openjdk-jre1.8:latest
#设置时区
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
COPY chuxue-data-mysql-1.0.jar chuxue-data-mysql-1.0.jar
CMD [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "chuxue-data-mysql-1.0.jar" ]