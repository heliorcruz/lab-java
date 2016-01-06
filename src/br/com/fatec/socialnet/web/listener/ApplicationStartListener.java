package br.com.fatec.socialnet.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;


/**
 * @author Carlos
 * 
 * @version
 */
public class ApplicationStartListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
				
		ContextSpecifier.setContext("br.com.fatec.socialnet");		
		ConfigDBMapper.setDefaultConnectionName("test");		
		LiquibaseRunnerService.run();		

	}

}
