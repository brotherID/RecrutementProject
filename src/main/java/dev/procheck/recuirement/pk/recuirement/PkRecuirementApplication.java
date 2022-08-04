package dev.procheck.recuirement.pk.recuirement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/***
 * 
 * @author CSRA
 *
 */
//http://localhost:8080/swagger-ui.html
@SpringBootApplication
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class PkRecuirementApplication extends SpringBootServletInitializer  {

	 public static void main(String[] args) {
		SpringApplication.run(PkRecuirementApplication.class, args);
	 }
	 @Bean
	 public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("dev.procheck.recuirement.pk.recuirement")).build();
	 }
}
