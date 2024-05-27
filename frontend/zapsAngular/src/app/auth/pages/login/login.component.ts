import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../Services/login.service';
import { LoginRequest } from '../../interface/loginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: ``
})
export class LoginComponent implements OnInit{
  loginError:String="";
  loginForm = this.formBuilder.group({
    username:['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
  })

  constructor(private formBuilder:FormBuilder, private router:Router, private loginService: LoginService){}

  ngOnInit(): void {

  }

  get email(){
    return this.loginForm.controls.username;
  }

  get password()
  {
    return this.loginForm.controls.password;
  }

  login(){
    if(this.loginForm.valid){
      this.loginError="";
      this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
        next: (userData) => {
          console.log(userData);
        },
        error: (errorData) => {
          console.error(errorData);
          this.loginError=errorData;
        },
        complete: () => {
          console.log("login completo");
          this.router.navigateByUrl('/home');
        this.loginForm.reset();
        }
      });

    }
    else {
      this.loginForm.markAllAsTouched();
      alert("error al ingresar los datos")
    }
  }

}
