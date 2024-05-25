import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoginService } from '../../../auth/Services/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: ``
})
export class HeaderComponent implements OnInit, OnDestroy {

  userLoginOn:boolean=false;
  constructor(private loginService:LoginService) { }

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

}
