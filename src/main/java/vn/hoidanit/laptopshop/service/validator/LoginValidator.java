package vn.hoidanit.laptopshop.service.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.LoginDTO;
import vn.hoidanit.laptopshop.service.UserService;

@Service
public class LoginValidator implements ConstraintValidator<LoginChecked, LoginDTO> {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public LoginValidator(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isValid(LoginDTO user, ConstraintValidatorContext context) {
        boolean valid = true;
        if (!this.userService.checkEmailExisted(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("This email is invalid")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        } else {
            User existingUser = this.userService.getUserByEmail(user.getEmail());

            // Kiểm tra password
            if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                context.buildConstraintViolationWithTemplate("Password is wrong")
                        .addPropertyNode("password")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }

        }
        return valid;
    }
}
