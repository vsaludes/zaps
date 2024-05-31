import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../../Services/register.service';
import { RegisterRequest } from '../../interface/registerRequest';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
})
export class RegisterComponent implements OnInit {
  registerError: string = '';
  registerForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private registerService: RegisterService) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      nombre: ['', Validators.required],
      direccion: ['', Validators.required],
      telefono: ['', Validators.required]
    }, {
      validators: this.mustMatch('password', 'confirmPassword')
    });
  }

  get email() {
    return this.registerForm.get('email');
  }

  get password() {
    return this.registerForm.get('password');
  }

  get confirmPassword() {
    return this.registerForm.get('confirmPassword');
  }

  mustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl.errors && !matchingControl.errors['mustMatch']) {
        return;
      }

      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ mustMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.registerError = '';
      this.registerService.register(this.registerForm.value as RegisterRequest).subscribe({
        next: (userData) => {
          console.log(userData);
        },
        error: (errorData) => {
          console.error(errorData);
          this.registerError = errorData;
        },
        complete: () => {
          console.log('Registro completo');
          this.router.navigateByUrl('/login');
          this.registerForm.reset();
        }
      });
    } else {
      alert('Error al ingresar los datos');
    }
  }
}
