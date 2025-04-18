package com.example.webPOS.config;

import com.example.webPOS.dao.impl.*;
import com.example.webPOS.dao.interfaces.*;
import com.example.webPOS.service.MemberService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.example.webPOS")
@EnableWebMvc
public class JavaConfig {

    private static String[] loadCredentials() {
        Properties props = new Properties();
        String filepath = "db/db_credentials.txt";

        ClassPathResource resource = new ClassPathResource(filepath);
        if (!resource.exists()) {
            throw new RuntimeException("db_credentials.txt not found in classpath");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream()))) {
            props.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load DB credentials", e);
        }

        String username = props.getProperty("username");
        String password = props.getProperty("password");
        if (username == null || password == null) {
            throw new RuntimeException("DB credentials are missing in the file");
        }
        return new String[]{username, password};
    }
    /**
     * Bean 등록할때,public {타입} {빈이름} 으로 등록.
     *
     * database 접근 메소드
     * @return dataSource
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        // get db credentials from file
        String[] credentials = loadCredentials();
        String username = credentials[0];
        String password = credentials[1];
        //set
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/posdb?characterEncoding=utf8&serverTimezone=UTC");
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setInitialSize(2);
        ds.setMaxActive(100);//size of the connection pool
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(60000 * 3);
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
        return ds;
    }
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class); //jstlView mapping
        viewResolver.setPrefix("/WEB-INF/views/");  // Set your view prefix here
        viewResolver.setSuffix(".jsp");  // Set your view suffix here
        return viewResolver;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 멤버관련 빈 등록
     */

    @Bean
    public MemberDAO memberDAO(DataSource dataSource) {
        return new MemberDaoImpl(dataSource);
    }

    @Bean
    public MemberService memberRegisterService(MemberDAO memberDAO){
        return new MemberService(memberDAO);
    }

    /**
     * storeInfo 관련 빈 등록
     */

    @Bean
    public StoreInfoDAO storeInfoDAO(DataSource dataSource){return new StoreInfoDaoImpl(dataSource);}
    /**
     *
     * 인벤토리 관련 빈 등록
     */

    @Bean
    public InventoryDAO inventoryDAO(DataSource dataSource){
        return new InventoryDaoImpl(dataSource);
    }


    /**
     * product 관련 빈 드등록
     */

    @Bean
    public ProductDAO productDAO(DataSource dataSource){return new ProductDaoImpl(dataSource);}

    /**
     * TradeLog 빈 등록
     */
    @Bean
    public TradeLogDAO tradeLogDAO(DataSource dataSource){return new TradeLogDaoImpl(dataSource);}

    /**
     * 테스트 빈
     */
    @Bean
    public TestBean testBean() {
        return new TestBean();
    }
}
