package org.saurav.cf.casestudy.employee.db;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("cloud")
public class DataSourceConfiguration extends AbstractCloudConfig {
	



	@Bean
	public DataSource dataSource() {
	  return cloud().getSingletonServiceConnector(DataSource.class, null);
	}
	}
