package vn.hoidanit.laptopshop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.ServletContext;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.handleHello();
        model.addAttribute("eric", test);
        model.addAttribute("hoidanit", "from controller with model");
        System.out.println(this.userService.getAllUsers());
        return "hello";
    }

    @GetMapping("/admin/user") // RequestMapping mặc định sử dụng method GET
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/admin/user/create") // RequestMapping mặc định sử dụng method GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User()); // Truyền một đối tượng mới tạo lên cho form, sau khi nhập thì đối
                                                   // tượng đó sẽ có thông tin và được chuyển quan PostMapping
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create") // Với method này thì khi đối tượng được
                                       // tạo xong bởi form thì, trang form sau
                                       // khi create sẽ tự động mapping qua
                                       // value của map này
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit,
            @RequestParam("hoidanitFile") MultipartFile file) { // Với annotation
        // ModelAttribute nó sẽ hứng
        // đối tượng trả ra của form ở
        // đây tên là newUser rồi sau
        // đó truyền hết thông tin đó
        // cho một đối tượng(User) tên
        // là hoidanit

        String avatar = this.uploadService.handleUploadSingleFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(hoidanit.getPassword());
        hoidanit.setAvatar(avatar);
        hoidanit.setPassword(hashPassword);
        hoidanit.setRole(userService.getRoleByName((hoidanit.getRole().getName())));
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user"; // Khi redirect thì phải chuyển tới đường link url chứ không phải là tên file
    }

    @GetMapping("/admin/user/{id}") // RequestMapping mặc định sử dụng method GET
    public String getUserDetailPage(Model model, @PathVariable long id) {

        model.addAttribute("id", id);
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("updatedUser") User hoidanit) {
        User currentUser = this.userService.getUserById(hoidanit.getId());
        if (currentUser != null) {
            currentUser.setFullName(hoidanit.getFullName());
            currentUser.setPhone(hoidanit.getPhone());
            currentUser.setAddress(hoidanit.getAddress());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id); // Lấy thông tin user từ DB
        if (user == null) {
            return "redirect:/admin/user"; // Nếu user không tồn tại, chuyển hướng
        }
        model.addAttribute("newUser", user);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User hoidanit) {
        this.userService.deleteUserById(hoidanit.getId());
        return "redirect:/admin/user";
    }

}