import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/user.types';
import { Router } from '@angular/router';
import { ConfirmButton } from '../html-elements/buttons/confirm.button';
import { Slice } from '../models/paging.types';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'users-overview-page',
  standalone: true,
  imports: [ConfirmButton, FontAwesomeModule],
  templateUrl: './users-overview.page.html',
  styles: `
    :host {
      display: block;
    }
  `
})
export class UsersOverviewPage {
  users: User[] = [];
  page = 0;
  size = 50;
  isLast = false;
  loading = false;

  constructor(private userService: UserService, public router: Router) {}

  ngOnInit(): void {
    this.loadPage(0);
  }

  private loadPage(page: number): void {
    if (this.loading) return;
    if (this.isLast && page > 0) return;
    this.loading = true;
  
    this.userService.getUsersSlice(page, this.size).subscribe({
      next: (usersFromSlice: Slice<User>) => {
        this.users = [...this.users, ...usersFromSlice.content];
        this.page = usersFromSlice.number;
        this.isLast = usersFromSlice.last;
      },
      error: (err) => console.error(err),
      complete: () => (this.loading = false)
    });
  }

  loadMore(): void {
    if (!this.isLast) {
      this.loadPage(this.page + 1);
    }
  }

  onEditUser(id: number): void {
    this.router.navigate(['/users', id, 'edit']);
  }

  onDeleteUser(id: number): void {
    const user = this.users.find(u => u.id === id);
    if (!user) return;

    // Normally I would have made a custom confirm button component, but for this example, I'll use the window.confirm() method.
    const confirmed = window.confirm(`Are you sure you want to delete ${user.name}?`);
    if (!confirmed) return;

    this.userService.deleteUser(id).subscribe(() => {
      this.users = this.users.filter(u => u.id !== id);
      if (!this.isLast) this.loadMore();
    });
  }
}
