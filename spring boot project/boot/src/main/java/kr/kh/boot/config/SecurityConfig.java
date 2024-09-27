package kr.kh.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.kh.boot.model.util.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //csrf : 외부 접근을 막을때 ?
		http.csrf(csrf ->csrf.disable())
			//URL에 접근 권한을 설정. MemberInterceptor, AdminInterceptor를 합친거라고 생각하면 됨
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/post/insert/*", "/post/update/*", "/post/delete/*")
                //위 URL을 권한이 "USER"인 회원만 접근하도록 설정
//                .hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name()) //권한을 여러개 추가시                 
//                .hasRole(UserRole.USER.name()) // 위 URL 권한이 "ROLE_USER"인 회원만 접근하도록
                .hasAuthority(UserRole.USER.name()) 
                .requestMatchers("/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
                .anyRequest().permitAll()  // 그 외 요청은 인증 필요
            )
            .formLogin((form) -> form
            	// 커스텀 로그인 페이지 설정하는 경우, 아이디창의 name을 username, 비번창의 name을 password로 해야 된다
                //.loginPage("/login")  // 커스텀 로그인 페이지 설정
                .permitAll()           // 로그인 페이지는 접근 허용
                .loginProcessingUrl("/login")//
                .defaultSuccessUrl("/") //성공했을때 보낼 URL
            )
            .logout((logout) -> logout
            		.logoutUrl("/logout") //이 URL로 post방식으로 전송하면 로그아웃이 자동으로 실행
            		.logoutSuccessUrl("/") //로그아웃 성공시 보낼 URL
            		.clearAuthentication(true)
            		.invalidateHttpSession(true)
            		.permitAll());  // 로그아웃도 모두 접근 가능
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
