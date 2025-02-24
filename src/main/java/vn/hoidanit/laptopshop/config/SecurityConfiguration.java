package vn.hoidanit.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import jakarta.servlet.DispatcherType;
import vn.hoidanit.laptopshop.service.CustomUserDetailsService;
import vn.hoidanit.laptopshop.service.UserService;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {// thông báo cho Spring Security rằng sẽ
        // không dùng UserDetailService nữa mà dùng
        // CustomDetailService
        return new CustomUserDetailsService(userService);// - Khi bạn trả ra một cái ...UserDetailService mới thì Spring
                                                         // sẽ ghi đè lại thằng UserDetailsService bằng
                                                         // CustomUserDetailsService
                                                         // - Còn với userService thì đây là một parameter của hàm tạo
                                                         // của CustomUserDetailsService, nên sẵn tiện ghi đè
                                                         // UserDetailService rồi thì truyền vào luôn

    }

    @Bean
    public DaoAuthenticationProvider authProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        // authProvider.setHideUserNotFoundExceptions(false);
        // set ẩn message của
        // UserNotFoundException
        // Tuy nhiên thì mặc định Spring sẽ ẩn dòng này đi là để tăng
        // mức độ bảo mật, để hacker không biết được tài khoản đó có
        // tồn tại
        // Giả sử nếu như ta hiện ra thông báo sai mật khẩu thì
        // hacker sẽ biết là tên tài khoản đúng rồi giờ chỉ cần tra
        // mật khẩu nữa thôi

        return authProvider;
    }
    // Hàm này có tác dụng nạp vào hết những thông tin mà ta tự customize và build
    // lên 1 cái authenticationManager từ những thông tin đó

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        // optionally customize
        rememberMeServices.setAlwaysRemember(true); // Default expire time is 30 days <==> 2592000 seconds
        return rememberMeServices;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // từ V6 trở đi thì Spring bắt đầu được viết theo kiểu lambda thế này
        http
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, // Forward cho phép truy cập tới các file, từ
                                                                        // một
                                                                        // đường link sang view, VD: nếu người dùng cố
                                                                        // truy cập vào /admin thì sẽ bị đá sang login

                                DispatcherType.INCLUDE) // Mặc định Spring nó sẽ chặn INCLUDE(Các tài nguyên trong sever
                                                        // truy cập lẫn nhau)
                                                        // FORWARD(các đường link trên view truy cập)
                        .permitAll()

                        // Khi lưu sẽ là ROLE_ADMIN hoặc ROLE_USER

                        .requestMatchers("/register", "/", "/login", "/client/**", "/css/**", "/js/**", "/images/**",
                                "/product/**")
                        .permitAll() // Đây
                        // chính
                        // là
                        // tất
                        // cả
                        // các
                        // đường
                        // link
                        // mà
                        // người
                        // dùng
                        // được
                        // quyền
                        // truy
                        // cấp
                        // File nào không có thì sẽ không thể truy cập được, VD: nếu bỏ file CSS thì
                        // view sẽ khoogn hiển thị file css

                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated())

                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .invalidSessionUrl("/logout?expired")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false))

                .logout(logout -> logout.deleteCookies("JSESSIONID").invalidateHttpSession(true))

                .rememberMe(r -> r.rememberMeServices(rememberMeServices()))
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .successHandler(customSuccessHandler()) // Điều hướng USER -> Homepage và ADMIN -> Admin sau
                        // khi đăng nhập và xác định role thành công
                        .permitAll())
                .exceptionHandling(ex -> ex.accessDeniedPage("/access-deny"));

        return http.build();
    }

}
