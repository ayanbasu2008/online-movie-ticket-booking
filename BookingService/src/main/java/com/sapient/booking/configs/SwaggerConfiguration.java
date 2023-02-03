/*
 * This code has been developed by Sandeep Kumar (8826615501) for Publicis Sapient case stuty
 * assignment purpose. It should not be used for any business and production 
 * purpose.
 * 
 */
package com.sapient.booking.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfiguration.
 * @author Sandeep Kumar
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	/** The domain. */
	@Value("${applicaiton.domain.url:127.0.0.1}")
	private String domain;
	
    /**
     * Product api.
     * This method returns Docket object and used by Spring Context for Swagger Configuration
     * @return the docket
     */
    @Bean
    public Docket productApi() {
        
    	ApiInfo apiInfo = new ApiInfoBuilder()
    			.title("Booking Microservice")
    			.description("This microservice is part of Theatre application example. It has be created for demostration only. It cound not be used for any production needs.")
				.contact(new Contact("Admin", "https://www.sk.com", "sk@test.com"))
				.version("0.0.1")
				.build();
        
        return new Docket(DocumentationType.SWAGGER_2)
				.host(domain + ":8084")
        		.select()
        		.paths(PathSelectors.any())
        		.build()
        		.apiInfo(apiInfo);
    }
    
}
