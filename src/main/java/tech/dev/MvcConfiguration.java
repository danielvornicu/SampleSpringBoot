package tech.dev;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Description de la classe
 * <p>
 * Date: 28/01/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

//using application.properties mechanism
@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {


	//manual pring boot bean configuration vs spring boot auto-configuration via application.properties
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver  = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver ;
	}

	@Bean
	public MessageSource messageSource() {
	    //v1
		//ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		//messageSource.setBasename("messages/common");
        //v2
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/common");

		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	//for activate the Bean Validation with Spring Boot this is the only way becasuse no auto-configuration echivalent
	//in application.properties
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

	//with this we indicate to Spring Boot to serve a resource stored in a WAR’s webapp/resources folder
	//by default Spring boot serve only the resources from src/main/resources
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}
}
