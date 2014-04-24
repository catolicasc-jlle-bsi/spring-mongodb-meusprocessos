package br.org.pucsc.meusprocessos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.org.pucsc.meusprocessos.controller.ExpenseController;
import br.org.pucsc.meusprocessos.model.Expense;
import br.org.pucsc.meusprocessos.mongodb.services.AttachmentRepository;

import com.mongodb.Mongo;

@Configuration
@EnableTransactionManagement
@ImportResource("/WEB-INF/spring-security.xml")
@ComponentScan(basePackageClasses = {AttachmentRepository.class,
		 ExpenseController.class,Expense.class})

public class ComponentConfig {
	
	@Bean
	public 	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new Mongo("localhost",27017), "meusprocessos");
	}
	
	@Bean
	public 	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10000000);
		return multipartResolver;
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

}
