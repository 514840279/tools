FROM java:8
#设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
COPY chuxue-getway-1.0.jar chuxue-getway-1.0.jar
CMD [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "chuxue-getway-1.0.jar" ]