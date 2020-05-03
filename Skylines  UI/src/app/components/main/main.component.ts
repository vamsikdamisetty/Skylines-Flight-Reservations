import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit() {
  }

  logout()
  {
    localStorage.userId=0;
    sessionStorage.userId=0;

    this.router.navigate(['/home/login']);
  }

}
