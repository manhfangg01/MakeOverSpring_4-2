package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserRepository UserRepository) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.handleHello();
        model.addAttribute("eric", test);
        model.addAttribute("hoidanit", "from controller with model");
        System.out.println(this.userService.getAllUsers());
        return "hello";
    }

    @RequestMapping("/admin/user") // RequestMapping mặc định sử dụng method GET
    public String getTableUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/table-user";
    }

    @RequestMapping("/admin/user/create") // RequestMapping mặc định sử dụng method GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST) // Với method này thì khi đối tượng được
                                                                               // tạo xong bởi form thì, trang form sau
                                                                               // khi create sẽ tự động mapping qua
                                                                               // value của map này
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit) { // Với annotation
                                                                                          // ModelAttribute nó sẽ hứng
                                                                                          // đối tượng trả ra của form ở
                                                                                          // đây tên là newUser rồi sau
                                                                                          // đó truyền hết thông tin đó
                                                                                          // cho một đối tượng(User) tên
                                                                                          // là hoidanit

        this.userService.handleSaveUser(hoidanit);
        return "admin/user/table-user";
    }

}