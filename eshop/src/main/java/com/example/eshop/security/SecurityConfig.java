package com.example.eshop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // bật @PreAuthorize
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** Spring Boot tự ráp DaoAuthenticationProvider từ UserDetailsService + PasswordEncoder. */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    private JwtAuthenticationFilter jwtFilter() {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    // ================= CHAIN 1: REST API (/api/**) =================
    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable) // API dùng header Bearer, không dùng cookie
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .anonymous(a -> a.authorities("ROLE_GUEST"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                        .requestMatchers("/api/orders/**").hasRole("CUSTOMER")
                        .anyRequest().hasRole("SELLER"))
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))) // 401
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // ================= CHAIN 2: WEB (Thymeleaf) =================
    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http
                // Spring Security không lưu đăng nhập vào session – thông tin đăng nhập nằm trong JWT cookie.
                // Dùng RequestAttributeSecurityContextRepository để SecurityContext chỉ lưu trong HttpServletRequest
                // và cho phép HttpSession hoạt động bình thường cho Giỏ hàng (@SessionScope) và CSRF token.
                .securityContext(s -> s.securityContextRepository(new RequestAttributeSecurityContextRepository()))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .csrf(csrf -> csrf.ignoringRequestMatchers("/logout"))
                // Người chưa đăng nhập mang quyền ROLE_GUEST
                .anonymous(a -> a.authorities("ROLE_GUEST"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico", "/error", "/403").permitAll()
                        .requestMatchers("/", "/products", "/products/**", "/login").permitAll()
                        .requestMatchers("/register").hasRole("GUEST")
                        .requestMatchers("/admin/**").hasRole("SELLER")
                        .requestMatchers("/cart/**", "/checkout/**", "/orders/**").hasRole("CUSTOMER")
                        .anyRequest().authenticated())
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")) // chưa login → /login
                        .accessDeniedPage("/403")) // thiếu quyền → /403
                .logout(l -> l
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // hỗ trợ cả GET và POST /logout
                        .deleteCookies(JwtService.COOKIE_NAME, "JSESSIONID")
                        .invalidateHttpSession(true) // xoá luôn giỏ hàng
                        .logoutSuccessUrl("/login?logout"))
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        // CSRF để mặc định (bật): th:action của Thymeleaf tự chèn input _csrf
        return http.build();
    }
}
