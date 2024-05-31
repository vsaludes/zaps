import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoginService } from '../../../auth/Services/login.service';
import { User } from '../../../auth/interface/user';
import { UserService } from '../../../auth/Services/user.service';
import { environment } from '../../../../environments/environment';
import { LoginComponent } from '../../../auth/pages/login/login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: ``
})
export class HeaderComponent implements OnInit, OnDestroy {

  errorMessage:String="";
  user?:User;

  userLoginOn:boolean=false;
  constructor(private userService:UserService, private loginService:LoginService  ){
    this.userService.getUser(environment.userId).subscribe({
      next: (userData) => {
        this.user=userData;
      },
      error: (errorData) => {
        this.errorMessage=errorData
      },
      complete: () => {
        console.info("User Data ok");
      }
    })
  }

  ngOnDestroy(): void {
    this.loginService.currentUserLoginOn.unsubscribe();
  }

  ngOnInit(): void {
    this.loginService.currentUserLoginOn.subscribe(
      {
        next:(userLoginOn) => {
          this.userLoginOn=userLoginOn;
        }
      }
    )
  }

  logout(): void {
    this.loginService.logout();
  }



}
