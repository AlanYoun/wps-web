FROM anapsix/alpine-java:8_server-jre_unlimited

LABEL maintainer="成都大成均图科技有限公司" desc="WPS对接服务"

RUN mkdir -p /dcjt/wps
RUN mkdir -p /usr/share/zoneinfo/Asia/Shanghai

# CentOS
RUN echo "Asia/shanghai" > /etc/timezone;

RUN cd /usr/share/zoneinfo/Asia/Shanghai
# Ubuntu
COPY * /etc/localtime/

WORKDIR /dcjt/wps

EXPOSE 8123

ADD ./target/wps-web.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=dev","-Xms100m -Xmx500m"]
