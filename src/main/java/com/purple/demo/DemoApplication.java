package com.purple.demo;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

// SSL 적용 시 (SSL port : 8090)
// 	@Bean
// 	public ServletWebServerFactory serverFactory() {
//         TomcatServletWebServerFactory tomcatServletWebServerFactory
//                 = new TomcatServletWebServerFactory();
//         		tomcatServletWebServerFactory.addAdditionalTomcatConnectors(createStandardConnector());

//         return tomcatServletWebServerFactory;
//     }

// //	HTTP를 열기위해 8091 port번호 지정 + 사용 
// //	(HTTP로 열기위해선 8091을 사용해야한다)
//     private Connector createStandardConnector() {
//         Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//         connector.setPort(8091);

//         return connector;
//     }

}
