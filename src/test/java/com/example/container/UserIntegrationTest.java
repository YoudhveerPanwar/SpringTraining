package com.example.container;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.entities.User;
import com.example.repository.UserRepository;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(initializers = {UserRepositoryTCIntegrationTest.Initializer.class})
public class UserIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11.1")  //new PostgreSQLContainer("postgres:11.1")
      .withDatabaseName("tests-db")
      .withUsername("test")
      .withPassword("test");

    @DynamicPropertySource
    static void properies(DynamicPropertyRegistry registry) {
    	registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    	registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    	registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    	
    }

    @Autowired
    private UserRepository userRepository;
    
    @Test
	void contextLoads() {
    	User user = new User();
    	user.setUserName("Youdhveer");
    	user.setPassword("Aeonnkgfdcv123$#%");
    	
    	userRepository.save(user);
    	System.out.println("user saved:"+user.getId());
		System.out.println("context loads...");
	}

    
    void testUser() {
    	User user = new User();
    	user.setUserName("Youdhveer");
    	user.setPassword("Aeonnkgfdcv123$#%");
    	
    	userRepository.save(user);
    	System.out.println("user saved:"+user.getId());
    }
    /*
    static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
              "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
              "spring.datasource.username=" + postgreSQLContainer.getUsername(),
              "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
    */
}
