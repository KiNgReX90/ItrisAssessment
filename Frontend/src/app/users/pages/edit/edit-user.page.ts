import { Component, OnInit, AfterViewInit, viewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user.types';
import { Router, ActivatedRoute } from '@angular/router';
import { UserFormComponent } from '../../components/user-form.component';

@Component({
  selector: 'edit-user-page',
  standalone: true,
  imports: [CommonModule, UserFormComponent],
  templateUrl: './edit-user.page.html'
})
export class EditUserPage implements OnInit {
  private userFormComponent = viewChild.required(UserFormComponent);

  userId!: number;
  userData!: User;
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
  
  loadUser(): void {
    if (!this.userId) return;
    
    // Always use the db as the source of truth, so we can be sure we're getting the latest data.
    this.userService.getUserById(this.userId).subscribe({
      next: (user) => {
        this.userData = user;
        this.userFormComponent().userForm.patchValue({
          name: user.name,
          email: user.email,
        });
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
        this.successMessage = 'The user has been updated successfully!';
        this.isSubmitting = false;
        setTimeout(() => {
          this.router.navigate(['/users']);
        }, 1500);
      },
      error: (error) => {
        this.errorMessage = error.error.message;
        this.isSubmitting = false;
        console.error('Error updating user:', error);
      },
      complete: () => {
        this.userFormComponent().userForm.reset();
      }
    });
  }
}
