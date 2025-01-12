//package com.lvl.Controller;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.lvl.Entity.User;
//import com.lvl.Service.EmailService;
//import com.lvl.Service.UserService;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private EmailService emailService; // Service gửi email
//
//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
//        User user = userService.findByEmail(email);
//
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Email không tồn tại trong hệ thống!");
//        }
//
//        // Tạo mã OTP ngẫu nhiên (6 chữ số)
//        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
//        user.setOtp(otp);
//        user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(10)); // OTP hết hạn sau 10 phút
//        userService.save(user);
//
//        // Gửi OTP qua email
//        String subject = "Mã OTP đặt lại mật khẩu";
//        String message = "Mã OTP của bạn là: " + otp + ". Mã này có hiệu lực trong 10 phút.";
//        emailService.sendEmail(email, subject, message);
//
//        return ResponseEntity.ok("Đã gửi mã OTP đến email!");
//    }
//}
