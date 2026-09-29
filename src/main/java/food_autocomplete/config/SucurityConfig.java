package food_autocomplete.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Configuration設定類別
@Configuration
public class SucurityConfig {

    // 加上Bean。這個方法產生出來的物件，請交給你的 Spring Container 管理
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // ----------------回傳型別 // 方法名稱
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/member/register")
            )
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/member/register").permitAll()
                    .anyRequest().permitAll());
            return http.build();
    }

}
