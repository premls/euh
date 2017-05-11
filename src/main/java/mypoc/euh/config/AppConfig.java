package mypoc.euh.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "mypoc.euh")
@EnableSwagger2
public class AppConfig {
	@Autowired
	private Logger logger;
	@Bean
    public Docket registerApi() {
		logger.info("Registering API's");
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()                                  
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))            
                .paths(PathSelectors.ant("/api/**"))                          
                .build()
                .apiInfo(apiInfo());
    }
     
    private ApiInfo apiInfo() {
    	logger.info("Set API info");
        return new ApiInfoBuilder()
                .title("Its euh demo application")
                .description("euh lighting application")
                .contact("premls")
                .version("1.0")
                .build();
    }
    
	@Bean
	public Logger logger(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
	}
}
