import { Routes } from '@angular/router';
import { UsersOverviewPage } from './users/users-overview.page';
import { CreateUserComponent } from './users/user/create-user/create-user.component';
import { EditUserComponent } from './users/user/edit-user/edit-user.component';
import { DashboardPage } from './dashboard/dashboard.page';

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardPage , title: 'Dashboard', data: { icon: ['fas', 'home'] } },
  { path: 'users', component: UsersOverviewPage, title: 'Users Overview', data: { icon: ['fas', 'users'] } },
  { path: 'users/create', component: CreateUserComponent, title: 'Add User', data: { icon: ['fas', 'user-plus'] } },
  { path: 'users/:id/edit', component: EditUserComponent, title: 'Edit User', data: { icon: ['fas', 'user-pen'] } }
];
