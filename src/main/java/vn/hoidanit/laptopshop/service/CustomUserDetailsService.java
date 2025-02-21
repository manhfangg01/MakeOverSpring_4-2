package vn.hoidanit.laptopshop.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        vn.hoidanit.laptopshop.domain.User user = this.userService.getUserByEmail(username);
        // Tùy vào từng hệ thông thì Username có thể các loại dữ liệu khác nhau như SĐT,
        // Email, CCCD, còn trong hệ thống này thì username=email;
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()))); // SingletonList
                                                                                                            // là
        // một list có 1
        // phần tử
        // Do User này là con của UserDetail nên theo tính đa hình của OOP thì User sẽ
        // tự được ép kiểu thành UserDetails

    }

}
