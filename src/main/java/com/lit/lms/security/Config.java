
package com.lit.lms.security;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import com.auth0.spring.security.api.JwtWebSecurityConfigurer;

import org.h2.server.web.WebServlet;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class Config  {


//	@Bean
//	public PasswordEncoder passwordEncode() {
//		return new BCryptPasswordEncoder();
//	}
    /*
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()  
        .antMatchers("/").permitAll()  
        .antMatchers("/img/**").permitAll()  
        .anyRequest().authenticated();

    }
   
}
 */
    @Bean
    ServletRegistrationBean h2servletRegistration(){
    ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    return registrationBean;
  }





}

	

