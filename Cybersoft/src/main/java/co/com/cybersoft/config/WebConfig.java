package co.com.cybersoft.config;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


/**
 * Web configuration class for Spring MVC and other web-related features
 * @author Daniel Falla
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"co.com.cybersoft.maintenance.tables.web.controller","co.com.cybersoft.maintenance.tables.web.domain","co.com.cybersoft.purchase.tables.web.controller","co.com.cybersoft.purchase.tables.web.domain","co.com.cybersoft.man.controller"})
public class WebConfig extends WebMvcConfigurerAdapter{

	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	public void addInterceptors(InterceptorRegistry registry){
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor);
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}
	
	@Bean
	public LocalValidatorFactoryBean jsr303Validator(){
		return new LocalValidatorFactoryBean();
	}
	

	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJacksonHttpMessageConverter());
	}
	
	@Bean
	public ServletContextTemplateResolver templateResolver(){
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(StandardTemplateModeHandlers.HTML5.getTemplateModeName());
		resolver.setCharacterEncoding("UTF-8");
		//FIXME In a production environment, caching should be enabled
		resolver.setCacheable(false);
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine(){
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		HashSet<IDialect> dialects = new HashSet<IDialect>();
		dialects.add(new SpringSecurityDialect());
		engine.setAdditionalDialects(dialects);
		return engine;
	}
	
	@Bean
	public LiteDeviceDelegatingViewResolver viewResolver(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
//		viewResolver.setOrder(1);
//		viewResolver.setViewNames(new String[]{"*"});
		//FIXME In a production environment, caching should be enabled
		viewResolver.setCache(false);
		LiteDeviceDelegatingViewResolver delegatingViewResolver = new LiteDeviceDelegatingViewResolver(viewResolver);
		delegatingViewResolver.setMobilePrefix("mobile/");
		delegatingViewResolver.setNormalPrefix("normal/");
		return delegatingViewResolver;
	}
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}
	
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("redirect:/home");
		registry.addViewController("/settings").setViewName("settings");
		registry.addViewController("/maintenance").setViewName("maintenance");registry.addViewController("/maintenanceSettings").setViewName("maintenanceSettings");registry.addViewController("/purchaseSettings").setViewName("purchaseSettings");
		registry.addViewController("/preferences").setViewName("language");
		registry.addViewController("/login").setViewName("man/login");
		registry.addViewController("/about").setViewName("man/about");
	}
	
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
		PageableArgumentResolver resolver = new PageableArgumentResolver();
		resolver.setFallbackPageable(new PageRequest(1, 10));
		argumentResolvers.add(new ServletWebArgumentResolverAdapter(resolver));
	}
}