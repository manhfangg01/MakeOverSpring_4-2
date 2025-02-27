package vn.hoidanit.laptopshop.service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.UserService;

@Service // Muốn dùng dependence Injection thì phải là @Service mới lấy được tài nguyên
         // khác để bơm vào class hiện tại được
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> { // ConstraintValidator<
                                                                                              // @Annotation ràng buộc,
    private final UserService userService;
    // Variable được kiểm tra>

    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // Check if password fields match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords must match") // Thông báo message của lỗi
                    .addPropertyNode("confirmPassword") // Trường thông tin bị lỗi
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Additional validations can be added here
        // Check existed Email
        if (this.userService.checkEmailExist(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email này đã tồn tại") // Thông báo message của lỗi
                    .addPropertyNode("email") // Trường thông tin bị lỗi
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
