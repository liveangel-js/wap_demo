package com.example;

import com.example.dao.*;
import com.example.model.*;
import com.example.service.UserService;
import com.example.util.Utility;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private Utility utility;
    private Session session;

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
        this.session = sessionFactory.openSession();
        session.close();
    }

	@Bean
	public CommandLineRunner demo(
            CustomerRepository repository, RoleRepository roleRepository ,
            CustomerRestRepository customerRestRepository,
            ProductRestRepository productRestRepository, CostRepository costRepository,
            SupermarketRestRepository supermarketRestRepository, PromotionRepository promotionRepository,
            SaleRepository saleRepository,
            UserRepository userRepository, @Autowired
    UserService userService) {

        CommandLineRunner commandLineRunner =(args) -> {
            utility.hello();
            //call CSVWRITE ( '/Users/liveangel/Desktop/cost.csv', 'SELECT * FROM cost' )
            //call CSVWRITE ( '/Users/liveangel/Desktop/promotion.csv', 'SELECT * FROM promotion' )
            //call CSVWRITE ( '/Users/liveangel/Desktop/sales.csv', 'SELECT * FROM sales' )
            boolean skip = true;

            boolean skip_sales = true;

            if(skip){
                this.session.createSQLQuery("insert into cost ( select * from csvread('files/cost.csv'))").executeUpdate();
                this.session.createSQLQuery("insert into promotion ( select * from csvread('files/promotion.csv'))").executeUpdate();
                this.session.createSQLQuery("insert into sales ( select * from csvread('files/sales.csv'))").executeUpdate();
            }

            log.info("Init Product Cost");
            if(!skip){
                for (Product product: productRestRepository.findAll()){
                    Cost cost = new Cost();
                    cost.setProductId(product.getProductMstId());
                    double costPercentage = utility.random(0.2, 0.98);
                    double costPrice = product.getPrice()*costPercentage;
                    cost.setCost(utility.decimalTwo(costPrice));
                    costRepository.save(cost);
                }
            }else{

            }



            log.info("Init Promotion ");
            int promotionBar = 10;

            int promotionDayRange = 200;
            int promotionExistMin = 7;
            int promotionExistMax = 30;
            Date today = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Iterable<Supermarket>  supermarketIterable = supermarketRestRepository.findAll();
            if(!skip){
                for (Product product: productRestRepository.findAll()){
                    for (Supermarket supermarket : supermarketIterable){
                        boolean isPromotion = utility.random(0,100)<promotionBar?true:false;
                        if (isPromotion){
                            int promotionDays = (int)utility.random(promotionExistMin,promotionExistMax);
                            int promotionStartDaysAgo = (int)utility.random(0, promotionDayRange);
                            Date promotionStartDate = utility.dayAfterToday(-promotionStartDaysAgo);
                            Date promotionEndDate = utility.dayAfterToday(-promotionStartDaysAgo + promotionDays);
                            double discount = utility.random(0.3, 0.98);
                            discount = utility.decimalTwo(discount);
                            Promotion promotion = new Promotion();
                            promotion.setProduct(product);
                            promotion.setSupermarket(supermarket);
                            promotion.setStartDate(promotionStartDate);
                            promotion.setEndDate(promotionEndDate);
                            promotion.setDiscount(discount);
                            promotionRepository.save(promotion);
                        }
                    }
                }

            }

            log.info("Init Sales ");
            Iterable<Customer> customerIterable = customerRestRepository.findAll();
            int buyTimesMax = 100;
            int buyMin = 1;
            int buyMax = 20;
            int totalProduct = 798;
            int saleDayRange=300;

            if(!skip_sales){
                for (Supermarket supermarket: supermarketIterable){
                    // only generate 1 data;
                    if (supermarket.getSupermarketId()!=1 && supermarket.getSupermarketId()!=2){
                        continue;
                    }
                    for (Customer customer: customerIterable){
                        if (customer.getCustomerId()>50){
                            continue;
                        }

                        int buyTimes = (int)utility.random(1, buyTimesMax);
                        // buy Times
                        for(int i=0; i<buyTimes; i++){
                            // buy date
                            int buyDaysAgo = (int)utility.random(0,saleDayRange);
                            Date buyDate = utility.dayAfterToday(-buyDaysAgo);
                            // Every time buy amount product
                            int amount = (int)utility.random(buyMin, buyMax);

                            for(int j=0;j<amount;j++){
                                long product_id = (long)utility.random(1, 30);
//                            log.info("Try to find Product by ID: " + product_id);
                                Product product = productRestRepository.findOne(product_id);
                                Sales sale = new Sales();
                                sale.setSourceProduct(product);
                                sale.setDate(buyDate);
                                sale.setCustomer(customer);
                                sale.setAmount(1);
                                sale.setSupermarket(supermarket);


                                Cost cost = costRepository.findOne(product_id);
                                sale.setCostPrice(cost.getCost());

                                double salesPrice = product.getPrice();
                                sale.setIsPromotion("N");
                                Promotion promotion = promotionRepository.findByStartDateBeforeAndEndDateAfterAndSupermarketAndProduct(buyDate, buyDate, supermarket, product);
                                if(promotion!=null){
//                                log.info("Buy date" + df.format(buyDate));
//                                log.info("Promotion: start" + df.format(promotion.getStartDate()) + "End" + df.format(promotion.getEndDate()));
                                    salesPrice = promotion.getDiscount()*salesPrice;
                                    sale.setIsPromotion("Y");
                                }

                                sale.setSalePrice(utility.decimalTwo(salesPrice));
                                double profit = sale.getSalePrice()-sale.getCostPrice();
                                sale.setProfit(utility.decimalTwo(profit));
                                saleRepository.save(sale);




                            }
                        }
                    }
                }


            }






            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
			}
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


        loadCsv(entityManagerFactory);





        // Execute aggregate
        String qlString = "SELECT AVG(price), SUM(price) FROM Product ";
        EntityManager em =  entityManagerFactory.createEntityManager();
        Query q = em.createQuery(qlString);
        Object[] results = (Object[]) q.getSingleResult();

        for (Object object : results) {
            System.out.println(object);
        }

        Query multiQuery = em
                .createQuery("SELECT AVG(price), SUM(price) FROM Product Group by product_type");
        List<Object[]> sumEntityList = multiQuery.getResultList();
        for(Object[] object : sumEntityList){

            for(Object a : object){
                System.out.print(a +" ");
            }
            System.out.println();
        }


		return commandLineRunner;
	}
}
