import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user.types';
import { Router } from '@angular/router';
import { UserFormComponent } from '../../user-form/user-form.component';

@Component({
  selector: 'app-create-user',
  standalone: true,
  imports: [CommonModule, UserFormComponent],
  templateUrl: './create-user.component.html',
  styles: `
    :host {
      display: block;
    }
  `
})
export class CreateUserComponent {
  isSubmitting = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  onSubmit(userData: Partial<User>): void {
    this.isSubmitting = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.userService.createUser(userData as User).subscribe({
      next: () => {
        this.successMessage = 'User created successfully!';
        this.isSubmitting = false;
        setTimeout(() => {
          this.router.navigate(['/users']);
        }, 1500);
      },
      error: (error) => {
        this.errorMessage = error.error.message || 'Failed to create user. Please try again.';
        this.isSubmitting = false;
        console.error('Error creating user:', error);
      }
    });
  }
}
