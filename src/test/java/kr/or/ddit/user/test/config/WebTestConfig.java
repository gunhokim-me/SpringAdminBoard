package kr.or.ddit.user.test.config;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/root-context.xml", 
		                           "classpath:/kr/or/ddit/config/spring/datasource-context.xml",
		                           "classpath:/kr/or/ddit/config/spring/application-context.xml"})
@WebAppConfiguration
public class WebTestConfig {
	
	@Autowired
	private WebApplicationContext context;
	
	protected MockMvc mock;
	
	@Before
	public void init() {
		mock = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test @Ignore
	public void dummy() {}
}
