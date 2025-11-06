package com.project01.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
@PropertySource("classpath:application.properties")
public class DbConfig {
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	
	private static final Logger logger = LoggerFactory.getLogger(DbConfig.class);
	
	public DbConfig() {
		logger.info("DbConfig 配置成功...");
	}
	
	//配置JdbcTemplate
	@Bean
	public JdbcTemplate createJdbcTemplate(DataSource datasource) {
		JdbcTemplate template = new JdbcTemplate(datasource);
		return template;
	}
	
	
	
}
