import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';  // Dịch vụ đăng nhập
import { Router } from '@angular/router';  // Router để điều hướng
import { CookieService } from 'ngx-cookie-service';  // CookieService để làm việc với cookie
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  userName: string | null = null;  // Tên người dùng
  isLoggedIn: boolean = false;  // Trạng thái đăng nhập

  constructor(
    private authService: AuthService,
    public router: Router,
    private cookieService: CookieService  // Inject CookieService
  ) {}

  ngOnInit(): void {
    // Kiểm tra xem người dùng đã đăng nhập hay chưa
    const userInfo = this.authService.getUserFromCookie();
    if (userInfo && userInfo.email) {
      // Nếu có email trong cookie, lấy thông tin người dùng từ backend
      this.isLoggedIn = true;
      this.userName = userInfo.email;  // Hiển thị email hoặc tên người dùng
    } else {
      this.isLoggedIn = false;
    }
  }

  // Đăng xuất
  logout(): void {
    // Xóa cookie người dùng
    this.cookieService.delete('user');
    this.isLoggedIn = false;
    this.userName = null;
    // Điều hướng về trang đăng nhập
    this.router.navigate(['/login']);
  }
}
