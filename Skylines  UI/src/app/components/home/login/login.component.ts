import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightService } from 'src/app/services/flight.service';
import { Users } from 'src/app/models/model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted: boolean;
  user : Users;
  userId : number;
  
  constructor(private formBuilder: FormBuilder, private router: Router, private flightService: FlightService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      
      username: ['', [Validators.required, Validators.pattern("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.*\\s).{6,10}$")]],
      password: ['', [Validators.required, Validators.pattern("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.*\\s).{6,10}$")]],

    });

    localStorage.userId=0;
    sessionStorage.userId=0;

  }

  logIn() {

    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    this.flightService.loginValidate(this.loginForm.value.username,this.loginForm.value.password).subscribe(data=>{
      this.userId = data;
    
        localStorage.userId=this.userId;
        sessionStorage.userId=this.userId;
        this.router.navigate(['../main/book']);
    
    } ,
    err =>{
      alert("Invalid Credentials");
      return;
    });
   
    



  }


}
