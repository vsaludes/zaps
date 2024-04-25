import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: ``
})
export class HomeComponent implements OnInit{

  ngOnInit(): void {

  }

  images = [
    { src: 'assets/images/back1.jpg' },
    { src: 'assets/images/back2.jpg' },
    { src: 'assets/images/back3.jpg' },
  ]
}
