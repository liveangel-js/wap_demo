package com.example;

import com.example.dao.CustomerRepository;
import com.example.dao.RoleRepository;
import com.example.dao.UserRepository;
import com.example.model.Customer;
import com.example.model.Role;
import com.example.model.User;
import com.example.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
//			System.out.println(beanName);
		}
	}

    @Autowired
	public void loadCsv(EntityManagerFactory entityManagerFactory){
        System.out.println("sessionFactory");
        SessionFactory sessionFactory = null;
        if(entityManagerFactory.unwrap(SessionFactory.class)==null){
            throw new NullPointerException("factory is not a hibernate factory");
        }else{
            sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
            System.out.println("AAAAAAAAAAAAA");
        }
        Session session = sessionFactory.openSession();
//        session.createSQLQuery("LOAD DATA INFILE :filename INTO TABLE testtable (text,price)").setString("filename", "/path/to/MyFile.csv").executeUpdate();
        session.createSQLQuery("insert into supermarket ( select * from csvread('files/SUPERMARKET_MST.csv'))").executeUpdate();
        session.createSQLQuery("insert into staff ( select staff_id,gender,name, position, rank, supermarket_id from csvread('files/STAFF_MST.csv'))").executeUpdate();
        session.createSQLQuery("insert into region ( select * from csvread('files/REGION_MST.csv'))").executeUpdate();
        session.createSQLQuery("insert into product ( select product_mst_id, price, price_unit, product_name, product_type from csvread('files/PRODUCT_MST.csv'))").executeUpdate();
        session.createSQLQuery("insert into customer ( select customer_id, gender, name, tel from csvread('files/CUSTOMER_MST.csv'))").executeUpdate();

    }

	@Bean
	public CommandLineRunner demo(CustomerRepository repository, RoleRepository roleRepository , UserRepository userRepository, @Autowired
    UserService userService) {
		return (args) -> {
			// save a couple of customers
//			repository.save(new Customer("Jack", "Bauer", "Jack Bauer", "jb@gmail.com", "Nanjing"));
//			repository.save(new Customer("Chloe", "O'Brian", "Chloe O'Brian", "co@gmail.com", "Nanjing"));
//			repository.save(new Customer("Kim", "Bauer", "Kim Bauer", "k@gmail.com", "Shanghai"));
//			repository.save(new Customer("David", "Palmer", "David Palmer", "dp@gmail.com", "Beijing"));
//			repository.save(new Customer("Michelle", "Dessler", "Michelle Dessler", "md@gmail.com", "Hangzhou"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
            if (customer!=null){
                log.info(customer.toString());
            }
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
//			for (Customer bauer : repository.findByLastName("Bauer")) {
//				log.info(bauer.toString());
//			}
			log.info("");

            roleRepository.save(new Role("User"));
            roleRepository.save(new Role("Admin"));
            Role role = roleRepository.findByName("Admin");
            User admin = new User("jsyu32","jiangshu1992");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            admin.setRoles(roles);
            userService.save(admin);
		};
	}
}
