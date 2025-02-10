package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.RoleRepositoty;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.domain.Role;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepositoty roleRepositoty;

    public UserService(UserRepository userRepository, RoleRepositoty roleRepositoty) {
        this.userRepository = userRepository;
        this.roleRepositoty = roleRepositoty;
    }

    public String handleHello() {
        return "fangg";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void handleSaveUser(User hoidanit) {
        this.userRepository.save(hoidanit);
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepositoty.findByName(name);
    }
}
