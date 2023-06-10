package com.example.webPOS.config;

import com.example.webPOS.dao.interfaces.MemberDAO;
import com.example.webPOS.dao.impl.MemberDaoImpl;
import com.example.webPOS.service.MemberRegisterService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.webPOS")
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

}
