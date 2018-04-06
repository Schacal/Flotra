package pl.sobczakpiotr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Piotr Sobczak, created on 13-03-2018
 */


@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

//  @Bean
//  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//    return args -> {
//
//      System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//      String[] beanNames = ctx.getBeanDefinitionNames();
//      Arrays.sort(beanNames);
//      int i = 1;
//      for (String beanName : beanNames) {
//        System.out.println(i++ + " " + beanName);
//      }
//    };
//  }

}
