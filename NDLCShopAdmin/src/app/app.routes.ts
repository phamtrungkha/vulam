import { Routes } from '@angular/router';
import { AdminUserComponent } from './components/admin-user/admin-user.component';

export const routes: Routes = [
    {path: 'user', component: AdminUserComponent},
    {path: '', component: AdminUserComponent}
];
