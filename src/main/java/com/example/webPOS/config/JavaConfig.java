package com.example.webPOS.config;

import com.example.webPOS.controller.LoginController;
import com.example.webPOS.controller.MemberController;
import com.example.webPOS.dao.MemberDAO;
import com.example.webPOS.dao.MemberDaoImpl;
import com.example.webPOS.dto.MemberDTO;
import com.example.webPOS.service.LoginService;
import com.example.webPOS.service.MemberRegisterService;
import com.example.webPOS.service.MemberService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.beans.BeanProperty;

@Configuration
@ComponentScan(basePackages = "com.example.webPOS")
public class JavaConfig {
    //Bean 등록할때,public {타입} {빈이름} 으로 등록.
    //database 접근 메소드
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

    /*로그인 관련 빈 등록*/

    /*멤버 관련 빈 등록*/
    @Bean
    @Scope("prototype")
    public MemberDTO memberDTO() {
        return new MemberDTO();
    }

    @Bean
    public MemberDAO memberDAO(DataSource dataSource) {
        return new MemberDaoImpl(dataSource);
    }

    @Bean
    public MemberService memberService(MemberDAO memberDAO) {
        return new MemberService(memberDAO);
    }
    @Bean
    public MemberRegisterService memberRegisterService(MemberDAO memberDAO){
        return new MemberRegisterService(memberDAO);
    }

}
