import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/services/flight.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Flight } from 'src/app/models/model';
import { Router } from '@angular/router';
import { I18nSelectPipe } from '@angular/common';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  flight: Flight;
  from: string = '';
  to: string = '';
  f: boolean = false;
  addPass: boolean = false;
  searchDiv: boolean = true;
  submitted: boolean;
  addForm: FormGroup;

  name: string;
  age: string;
  gender: string;

  constructor(private formBuilder: FormBuilder, private flightService: FlightService, private router: Router) { }

  ngOnInit() {

    
    if (localStorage.userId != 0) {
      this.addForm = this.formBuilder.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        age: ['', Validators.required],
        gender: ['', Validators.required],
      });
    }
    else {
      this.router.navigate(['/home/login']);
    }

  }

 
  selectChangeHandler(event: any) {
    this.from = event.target.value;
  }

  selectChangeHandler2(event: any) {
  
    this.to = event.target.value;
  
  }

  selectChangeHandler3(event: any) {
    
    this.gender = event.target.value;
  
  }
  searchFlight() {

    if (this.from == "" || this.to == "") {
      alert("Please select the cities first")
    }
    else if (this.from == this.to) {
      alert("Both cities cannot be same.");
    }
    else {

      this.searchDiv = false;
      console.log(this.from + this.to);
      this.flightService.searchFlight(this.from, this.to).subscribe(data => this.flight = data);

      console.log(this.flight);
      this.f = true;

    }
   

  }

  bookFlight() {
    this.addPass = true;
  }
  addPassenger() {
    this.submitted = true;
    if (this.addForm.invalid) {
      return;
    }

    console.log(this.addForm.controls.firstName.value);

    this.name = this.addForm.controls.firstName.value + " " + this.addForm.controls.lastName.value;

    this.age = this.addForm.controls.age.value;

    this.router.navigate(['main/payment', this.flight.flightId, this.name, this.age, this.gender]);

  }

}
