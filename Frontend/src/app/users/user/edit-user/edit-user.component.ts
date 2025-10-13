import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/users/user.types';
import { Router } from '@angular/router';
import { UserFormComponent } from '../../user-form/user-form.component';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [CommonModule, UserFormComponent],
  templateUrl: './edit-user.component.html'
})
export class EditUserComponent implements OnInit {
  @Input() userId?: number;
  @ViewChild(UserFormComponent) userFormComponent?: UserFormComponent;
  
  isSubmitting = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.userId) {
      this.loadUser();
    }
  }

  loadUser(): void {
    if (!this.userId) return;
    
    this.userService.getUserById(this.userId).subscribe({
      next: (user) => {
        if (this.userFormComponent) {
          this.userFormComponent.userForm.patchValue({
            name: user.name,
            email: user.email,
          });
        }
      },
      error: (error) => {
        this.errorMessage = error.error.message;
        console.error('Error loading user:', error);
      }
    });
  }

  onSubmit(userData: Partial<User>): void {
    if (!this.userId) return;

    this.isSubmitting = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.userService.updateUser(this.userId, userData as User).subscribe({
      next: () => {
        this.successMessage = 'User updated successfully!';
        this.isSubmitting = false;
        setTimeout(() => {
          this.router.navigate(['/users']);
        }, 1500);
      },
      error: (error) => {
        this.errorMessage = error.error.message;
        this.isSubmitting = false;
        console.error('Error updating user:', error);
      }
    });
  }
}
