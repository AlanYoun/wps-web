FROM anapsix/alpine-java:8_server-jre_unlimited

LABEL maintainer="成都大成均图科技有限公司" desc="wps服务"

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' > /etc/timezone
RUN mkdir -p /blade/wps

WORKDIR /blade/wps

EXPOSE 8106

ADD ./target/blade-wps.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["-Xms100m -Xmx500m"]
