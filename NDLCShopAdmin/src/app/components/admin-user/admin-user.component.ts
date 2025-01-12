import { Component } from '@angular/core';
import { User } from '../../model';
import { UserService } from '../../user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-user',
  imports: [FormsModule, CommonModule],
  templateUrl: './admin-user.component.html',
  styleUrl: './admin-user.component.css'
})
export class AdminUserComponent {
  users: User[] = [];
  selectedUser: User = { userId: 0, name: '', email: '', password: '', phone: '', gender: null, address: '', created_at: new Date() }; // Khởi tạo object User rỗng
  genders = [
    { display: 'Chọn giới tính', value: null }, 
    { display: 'Nam', value: true },
    { display: 'Nữ', value: false }// Cho phép giá trị null nếu cần
  ];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    });
    const uid = this.users[0].userId;
    const abc = 1;
  }

  clearFields() {
    this.selectedUser = { userId: 0, name: '', email: '', password: '', phone: '', gender: null, address: '', created_at: new Date() };
  }

  saveUser() {
    if (this.selectedUser.userId === 0) {
      this.userService.createUser(this.selectedUser).subscribe(() => {
        this.loadUsers();
        this.clearFields();
      });
    } else {
      this.userService.updateUser(this.selectedUser).subscribe(() => {
        this.loadUsers();
        this.clearFields();
      });
    }
  }

  editUser(user: User) {
    this.selectedUser = { ...user }; // Tạo bản sao để không ảnh hưởng đến danh sách
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user.userId).subscribe(() => {
      this.loadUsers();
    });
  }

  setGenderValue(displayGender: string): boolean { 
    return displayGender === 'Nam' ? true : false; 
  }
}
