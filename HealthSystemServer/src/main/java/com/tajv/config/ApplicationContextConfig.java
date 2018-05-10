package com.tajv.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.tajv.dao.ClientDao;
import com.tajv.dao.ClientDaoImpl;
import com.tajv.dao.FileDao;
import com.tajv.dao.FileDaoImpl;
import com.tajv.model.Client;
import com.tajv.model.File;
import com.tajv.service.ClientService;
import com.tajv.service.ClientServiceImpl;
import com.tajv.service.FileService;
import com.tajv.service.FileServiceImpl;

@EnableWebMvc
@Configuration
@ComponentScan("com.tajv")
@EnableTransactionManagement
public class ApplicationContextConfig extends WebMvcConfigurerAdapter {

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSizePerFile(20971520); // 20 MB return resolver
		resolver.setMaxInMemorySize(10485760); //// 10MB
		return resolver;
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/**
	 * Connection to database
	 * 
	 * @return
	 * @throws IOException
	 */

	@Bean(name = "dataSource")
	public DataSource getDataSource() throws IOException {
		Properties properties = new Properties();
		String propertiesName = "persistence.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesName);
		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propertiesName + "' not found in the classpath");
		}
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(properties.getProperty("db.driver"));
		dataSource.setUrl(properties.getProperty("db.url"));
		dataSource.setUsername(properties.getProperty("db.username"));
		dataSource.setPassword(properties.getProperty("db.password"));

		return dataSource;
	}

	/**
	 * Hibernate properties
	 * 
	 * @return
	 */

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(Client.class, File.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired

	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	/**
	 * Connection between session factory and classes
	 * 
	 * @param sessionFactory
	 * @return
	 */

	/*
	 * @Autowired
	 * 
	 * @Bean(name = "internDao") public InternDAO getInternDao(SessionFactory
	 * sessionFactory) { return new InternDAOImpl(sessionFactory); }
	 * 
	 * @Autowired
	 * 
	 * @Bean(name = "internStudyDao") public InternStudyDAO
	 * getInternStudyDao(SessionFactory sessionFactory) { return new
	 * InternStudyDAOImpl(sessionFactory); }
	 * 
	 * @Autowired
	 * 
	 * @Bean(name = "internPositionDao") public InternPositionDAO
	 * getInternPositionDao(SessionFactory sessionFactory) { return new
	 * InternPositionDAOImpl(sessionFactory); }
	 * 
	 * @Autowired
	 * 
	 * @Bean(name = "additionalInfoDao") public AdditionalInfoDAO
	 * getAdditionalInfoDao(SessionFactory sessionFactory) { return new
	 * AdditionalInfoDAOImpl(sessionFactory); }
	 */

	/**
	 * MessageSource sets i18n messages source, message files basename and text
	 * encoding.
	 * 
	 * @return
	 */

	/*
	 * @Bean public MessageSource messageSource() {
	 * ReloadableResourceBundleMessageSource messageSource = new
	 * ReloadableResourceBundleMessageSource();
	 * messageSource.setBasename("/i18n/locale");
	 * messageSource.setDefaultEncoding("UTF-8"); return messageSource; }
	 */

	/**
	 * In this method default locale and cookie is created, which saves chosen
	 * locale for some time (1200 s)
	 * 
	 * @return
	 */

	/*
	 * @Bean public LocaleResolver localeResolver() { CookieLocaleResolver resolver
	 * = new CookieLocaleResolver(); resolver.setDefaultLocale(new Locale("en"));
	 * resolver.setCookieName("myLocaleCookie"); resolver.setCookieMaxAge(1200);//
	 * 20 min return resolver; }
	 */

	/**
	 * let choose locale
	 * 
	 */

	/*
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	 * interceptor.setParamName("lang"); registry.addInterceptor(interceptor); }
	 */

	/**
	 * Email configurations.
	 * 
	 * @return
	 */

	/*
	 * @Bean public JavaMailSender javaMailService() { JavaMailSenderImpl
	 * javaMailSender = new JavaMailSenderImpl();
	 * 
	 * javaMailSender.setHost("smtp.gmail.com"); javaMailSender.setPort(587);
	 * javaMailSender.setUsername("internquestionnairewebapp@gmail.com");
	 * javaMailSender.setPassword("internQuestionnaire");
	 * 
	 * javaMailSender.setJavaMailProperties(getMailProperties());
	 * 
	 * return javaMailSender; }
	 * 
	 * private Properties getMailProperties() { Properties properties = new
	 * Properties(); properties.setProperty("mail.transport.protocol", "smtp");
	 * properties.setProperty("mail.smtp.auth", "true");
	 * properties.setProperty("mail.smtp.starttls.enable", "true");
	 * properties.setProperty("mail.debug", "false");
	 * properties.setProperty("mail.smtp.ssl.trust", "*"); return properties; }
	 */

	/*
	 * @Autowired
	 * 
	 * @Bean(name = "emailService") public MailService getMailService() { return new
	 * MailService(); }
	 */

	@Autowired
	@Bean(name = "clientService")
	public ClientService getClientService() {
		return new ClientServiceImpl();
	}

	@Autowired
	@Bean(name = "clientDao")
	public ClientDao getClientDao() {
		return new ClientDaoImpl();
	}

	@Autowired
	@Bean(name = "fileDao")
	public FileDao getFileDao() {
		return new FileDaoImpl();
	}

	@Autowired
	@Bean(name = "fileService")
	public FileService getFileService() {
		return new FileServiceImpl();
	}
}