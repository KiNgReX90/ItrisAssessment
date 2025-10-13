import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/users/user.types';
import { CancelButton } from 'src/app/html-elements/buttons/cancel.button'; 
import { ConfirmButton } from 'src/app/html-elements/buttons/confirm.button'; 

@Component({
  selector: 'user-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, CancelButton, ConfirmButton],
  templateUrl: './user-form.component.html'
})
export class UserFormComponent {
  userForm: FormGroup;
  @Input() isSubmitting = false;
  @Input() userId?: number;

  @Input() errorMessage = '';
  @Input() successMessage = '';

  @Output() submit = new EventEmitter<Partial<User>>();

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {
    this.userForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onSubmit(): void {
    if (this.userForm.valid && !this.isSubmitting) {
      this.isSubmitting = true;
      this.errorMessage = '';
      this.successMessage = '';

      const userData: Partial<User> = {
        name: this.userForm.value.name,
        email: this.userForm.value.email,
      };  
      this.submit.emit(userData);
    }
  }

  onCancel(): void {
    this.router.navigate(['/users']);
  }
}
