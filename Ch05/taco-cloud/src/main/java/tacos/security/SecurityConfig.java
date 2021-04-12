package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation
					.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation
					.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web
					.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
					.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;

//import javax.sql.DataSource; JDBC 기반 사용자 스토어에 필요함


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/design", "/orders")
		.access("hasRole('ROLE_USER')")
		.antMatchers("/", "/**").access("permitAll")
		.and()
		.formLogin()
		.loginPage("/login")
		.and()
		.logout()
		.logoutSuccessUrl("/")
		.and()
		.csrf();
	}

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(encoder());
		
		// 아래 코드는 책의 LDAP 기반 사용자 스토어 예제 코드입니다.
		/*auth
		.ldapAuthentication()
		.userSearchBase("ou=people")
		.userSearchFilter("(uid={0})")
		.groupSearchBase("ou=groups")
		.groupSearchFilter("member={0}")
		.contextSource()
		.root("dc=tacocloud,dc=com")
		.ldif("classpath:users.ldif")
		.and()
		.passwordCompare()
		.passwordEncoder(new BCryptPasswordEncoder())
		.passwordAttribute("userPasscode");*/
		
		// 아래 코드는 책의 JDBC 기반 사용자 스토어 예제 코드입니다.
		/*@Autowired
	    DataSource dataSource;
	    
		auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(
			"select username, password, enabled from users " +
			"where username=?")
		.authoritiesByUsernameQuery(
			"select username, authority from authorities " +
			"where username=?")
		.passwordEncoder(new NoEncodingPasswordEncoder());*/
		
		// 아래 코드는 책의 인메모리 기반 사용자 스토어 예제 코드입니다.
		/*auth.inMemoryAuthentication()
		.withUser("user1")
		.password("{noop}password1")
		.authorities("ROLE_USER")
		.and()
		.withUser("user2")
		.password("{noop}password2")
		.authorities("ROLE_USER");*/
	}
}