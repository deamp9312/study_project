//package jpabook.jpashop.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//@Profile("dev")
//public class MyConfig {
//
//    @Value("${myapp.profile.dev.enabled}")
//    private boolean devEnabled;
//
//    @Value("${myapp.profile.dev.ip}")
//    private String devIp;
//
//    @Bean
//    public MyBean devBean() {
//        return new MyBean("Dev Bean");
//    }
//
//    @Bean
//    public MyBean commonBean() {
//        return new MyBean("Common Bean");
//    }
//}
