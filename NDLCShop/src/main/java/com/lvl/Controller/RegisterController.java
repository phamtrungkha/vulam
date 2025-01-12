package com.lvl.Controller;

import com.lvl.DTO.RegisterRequest;
import com.lvl.DTO.RegisterResponse;
import com.lvl.Entity.User;
import com.lvl.Entity.AppRole;
import com.lvl.Entity.UserRole;
import com.lvl.Service.UserRegister;
import com.lvl.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegister userRegister;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = new RegisterResponse();
        
        // Kiểm tra xem email đã tồn tại chưa
        User existingUser = userRegister.findByEmail(registerRequest.getEmail());
        if (existingUser != null) {
            response.setSuccess(false);
            return response;  // Nếu email đã tồn tại, trả về false
        }

        // Tạo mới người dùng
        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(registerRequest.getPassword());
        newUser.setName(registerRequest.getName());

        // Lấy role với ID = 1
        AppRole role = userRegister.getRoleById(1);  // ID = 1 là role mặc định

        // Gán role cho người dùng
        UserRole userRole = new UserRole();
        userRole.setUser(newUser);
        userRole.setRole(role);

        // Lưu người dùng và role vào cơ sở dữ liệu
        userService.save(newUser);  // Lưu người dùng
        userService.save(userRole);  // Lưu role của người dùng

        response.setSuccess(true);  // Đăng ký thành công
        return response;
    }
}
