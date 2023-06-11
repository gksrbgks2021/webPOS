package com.example.webPOS.config;

import com.example.webPOS.dao.impl.*;
import com.example.webPOS.dao.interfaces.*;
import com.example.webPOS.dto.Inventory;
import com.example.webPOS.service.MemberRegisterService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "com.example.webPOS")
@EnableWebMvc
public class JavaConfig {

    /**
     * Bean 등록할때,public {타입} {빈이름} 으로 등록.
     *
     * database 접근 메소드
     * @return dataSource
     */

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/posdb?characterEncoding=utf8&serverTimezone=UTC");
        ds.setUsername("posmanager");
        ds.setPassword("admin1234");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
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


    /**
     * 멤버관련 빈 등록
     */

    @Bean
    public MemberDAO memberDAO(DataSource dataSource) {
        return new MemberDaoImpl(dataSource);
    }

    @Bean
    public MemberRegisterService memberRegisterService(MemberDAO memberDAO){
        return new MemberRegisterService(memberDAO);
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

}
