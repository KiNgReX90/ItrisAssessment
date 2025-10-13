import { Routes } from '@angular/router';
import { UsersOverviewPage } from './users/users-overview.page';
import { CreateUserComponent } from './users/user/create-user/create-user.component';
import { EditUserComponent } from './users/user/edit-user/edit-user.component';

export const routes: Routes = [
  { path: '', redirectTo: '/users', pathMatch: 'full' },
  { path: 'users', component: UsersOverviewPage, title: 'User Overview', data: { icon: ['fas', 'users'] } },
  { path: 'users/:id', component: CreateUserComponent, title: 'Add User', data: { icon: ['fas', 'user-plus'] } },

  { path: 'users/:id/edit', component: EditUserComponent, title: 'Edit User', data: { icon: ['fas', 'user-pen'] } }
];
