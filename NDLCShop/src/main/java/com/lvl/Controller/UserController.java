package com.lvl.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvl.DTO.LoginRequest;
import com.lvl.DTO.LoginResponse;
import com.lvl.Entity.AppRole;
import com.lvl.Entity.User;
import com.lvl.Entity.UserRole;
import com.lvl.Repository.UserRepository;
import com.lvl.Service.UserRegister;
import com.lvl.Service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegister userRegister;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(true);
            response.setRole(user.getRoles().iterator().next().getName()); 
            response.setMessage("Đăng nhập thành công!");
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setMessage("Tên đăng nhập hoặc mật khẩu không đúng.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	user.setUserId(null);
    	// Lấy role với ID = 1
        AppRole role = userRegister.getRoleById(1);  // ID = 1 là role mặc định

        // Gán role cho người dùng
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        // Lưu người dùng và role vào cơ sở dữ liệu
        User createdUser = userService.createUser(user);
        userService.save(userRole);  // Lưu role của người dùng
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUserId(id); // Đảm bảo ID được set
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
