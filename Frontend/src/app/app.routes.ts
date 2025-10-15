import { Routes } from '@angular/router';
import { UsersOverviewPage } from './users/pages/overview/users-overview.page';
import { CreateUserPage } from './users/pages/create/create-user.page';
import { EditUserPage } from './users/pages/edit/edit-user.page';
import { DashboardPage } from './dashboard/dashboard.page';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardPage , title: 'Dashboard', data: { icon: ['fas', 'home'] } },
  { path: 'users', component: UsersOverviewPage, title: 'Users Overview', data: { icon: ['fas', 'users'] } },
  { path: 'users/create', component: CreateUserPage, title: 'Create User', data: { icon: ['fas', 'user-plus'] } },
  { path: 'users/:id/edit', component: EditUserPage, title: 'Edit User', data: { icon: ['fas', 'user-pen'] } },
  { path: '**', redirectTo: 'dashboard' }
];
