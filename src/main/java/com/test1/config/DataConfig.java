package com.test1.config;

import java.beans.PropertyVetoException;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@MapperScan("com.test1.dao")
public class DataConfig {
	private static final String DATABASE_DRIVER="db.driver";
	private static final String DATABASE_USERNAME="db.user";
	private static final String DATABASE_PASSWORD="db.pwd";
	private static final String DATABASE_URL="db.url";
	
	@Resource
	private Environment env;
	
	@Bean
	public ComboPooledDataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(env.getRequiredProperty(DATABASE_DRIVER));
			dataSource.setJdbcUrl(env.getRequiredProperty(DATABASE_URL));
			dataSource.setUser(env.getRequiredProperty(DATABASE_USERNAME));
			dataSource.setPassword(env.getRequiredProperty(DATABASE_PASSWORD));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("com.test1.model");
		return sessionFactory;
	}
}
