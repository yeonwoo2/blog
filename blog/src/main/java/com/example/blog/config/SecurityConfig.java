package com.example.blog.config;

import com.example.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//빈 등록: 스프링컨테이터에서 객체를 관리할 수 있게 하는 것

@Configuration //설정 빈등록
@EnableWebSecurity// 시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    //암호화 시켜줌
    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    //시큐리티가 대신 로그인해주는데 password를 가로채기함
    //해당 password가 뭘로 해쉬가 되어 회원가입이 되어있는지 알아야
    //같은 해쉬로 암호화해서 db에 있는 해쉬랑 비교할 수 있음.

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()//csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
            .authorizeRequests()//인증 요청을 하면
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")// 해당요청 제외한
                .permitAll()
                .anyRequest() // 다른 요청들은
                .authenticated() // 인증하겠다.
            .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당주소로 요청오는 로그인을 가로체서 대신 로그인 해준다.
                .defaultSuccessUrl("/")
                ;
    }
}
