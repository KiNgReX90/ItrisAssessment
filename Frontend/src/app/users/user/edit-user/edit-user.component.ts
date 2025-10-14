import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user.types';
import { Router, ActivatedRoute } from '@angular/router';
import { UserFormComponent } from '../../user-form/user-form.component';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [CommonModule, UserFormComponent],
  templateUrl: './edit-user.component.html'
})
export class EditUserComponent implements OnInit, AfterViewInit {
  @ViewChild(UserFormComponent) userFormComponent?: UserFormComponent;
  
  userId?: number;
  userData?: User;
  isSubmitting = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.userId = parseInt(idParam, 10);
      this.loadUser();
    }
  }

  ngAfterViewInit(): void {
    if (this.userData && this.userFormComponent) {
      this.userFormComponent.userForm.patchValue({
        name: this.userData.name,
        email: this.userData.email,
      });
    }
  }

  
  loadUser(): void {
    if (!this.userId) return;
    
    // Always use the db as the source of truth, so we can be sure we're getting the latest data.
    this.userService.getUserById(this.userId).subscribe({
      next: (user) => {
        this.userData = user;
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
