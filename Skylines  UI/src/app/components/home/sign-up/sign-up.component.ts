import { Component, OnInit } from '@angular/core';
import { Users } from 'src/app/models/model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightService } from 'src/app/services/flight.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  signUpForm: FormGroup;
  submitted: boolean;
  user: Users;
  constructor(private formBuilder: FormBuilder, private router: Router, private flightService: FlightService) { }


  ngOnInit() {
    this.signUpForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern("[A-Z][a-z\\s*A-Z]*\\s*")]],
      phoneNum: ['', [Validators.required, Validators.pattern("[6-9][0-9]{9}")]],
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, Validators.pattern("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.*\\s).{6,10}$")]],
      password: ['', [Validators.required, Validators.pattern("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.*\\s).{6,10}$")]],

    });
  }

  signUp() {

    this.submitted = true;
    if (this.signUpForm.invalid) {
      return;
    }
    this.flightService.addUser(this.signUpForm.value).subscribe(data => {

      this.router.navigate(['../home/login']);
     
      alert("Account succesfully created");

    }, err => {
      console.log(err.stack);
    });
  }



}
