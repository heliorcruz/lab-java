package br.com.fatec.socialnet.core;


import java.sql.Connection;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 * @author carlosolr
 * 
 * @version 1.0.0
 */
public class LiquibaseRunnerService {

	public static void run() {
		Connection conn;
		try {
			
			conn = ConfigDBMapper.getDefaultConnection();

			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
					new JdbcConnection(conn));
			Liquibase liquibase = new Liquibase("br/com/fatec/socialnet/liquibase/changelog-master.xml",
					new ClassLoaderResourceAccessor(), database);
			liquibase.forceReleaseLocks();
			liquibase.update("fatec");
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro na execução do Liquibase", e);
		}
	}
}