//package com.sapient.app.config;
//
////import jakarta.activation.DataSource;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import javax.sql.DataSource;
//
//
//@Configuration
//public class MyConfiguration {
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource);
//        emf.setEntityManagerFactoryInterface(EntityManagerFactory.class);
//
//        // Set the JPA vendor adapter
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        emf.setJpaVendorAdapter(vendorAdapter);
//
//        // Set the package to scan for entities
//        emf.setPackagesToScan("com.sapient.app.model");
//
//        return emf;
//    }
//}