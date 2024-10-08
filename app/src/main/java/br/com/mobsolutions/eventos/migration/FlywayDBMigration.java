package br.com.mobsolutions.eventos.migration;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;

@Singleton @Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class FlywayDBMigration {

	@Resource(lookup = "java:jboss/datasources/EventosDS")
	private DataSource dataSource;

	@PostConstruct
	void migrate() {
		Flyway
			.configure()
			.dataSource(dataSource)
			.locations("classpath:/db/migration")
//			.baselineVersion("")
//			.baselineOnMigrate(true)
			.load()
			.migrate();

	}

}
