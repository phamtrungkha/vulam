import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { LoginRequest } from '../interface/LoginRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api';  // URL backend

  constructor(
    private http: HttpClient,
    private cookieService: CookieService
  ) { }

  login(credentials: LoginRequest): Observable<any> {
    return this.http.post(this.apiUrl + '/login', credentials);
  }

  saveUserToCookie(userInfo: any) {
    this.cookieService.set('user', JSON.stringify(userInfo), 1, '/');
  }

  getUserFromCookie() {
    const userCookie = this.cookieService.get('user');
    return userCookie ? JSON.parse(userCookie) : null;
  }
}
