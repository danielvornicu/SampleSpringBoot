package tech.dev;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description de la classe
 * <p>
 * Date: 28/01/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

//using application.properties mechanism
//@Configuration
//@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

/*	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver  = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver ;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages/common");
		source.setDefaultEncoding("UTF-8");
		return source;
	}*/
}
