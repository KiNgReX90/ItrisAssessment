import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/users/user.types';
import { Router } from '@angular/router';
import { ConfirmButton } from '../html-elements/buttons/confirm.button';

@Component({
  selector: 'users-overview-page',
  standalone: true,
  imports: [ConfirmButton],
  templateUrl: './users-overview.page.html',
  styles: `
    :host {
      display: block;
    }
  `
})
export class UsersOverviewPage {
  users: User[] = [];

  constructor(private userService: UserService, public router: Router) {}

  ngOnInit(): void {
    this.userService.getUsers().subscribe((users) => {
      this.users = users;
    });
  }

  onEditUser(id: number): void {
    this.router.navigate(['/users', id, 'edit']);
  }

  onDeleteUser(id: number): void {
    this.userService.deleteUser(id).subscribe(() => {
      this.users = this.users.filter((user) => user.id !== id);
    });
  }
}
