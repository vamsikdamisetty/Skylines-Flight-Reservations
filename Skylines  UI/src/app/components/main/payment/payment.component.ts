import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FlightService } from 'src/app/services/flight.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  flightId : number;
  name: string;
  age:string;
  gender:string;
  payForm : FormGroup;

  submitted:boolean = false;
  result:boolean;

  constructor(private route:ActivatedRoute,private flightService:FlightService,private router:Router,private formBuilder:FormBuilder) { }

  ngOnInit() {

    this.route.params.subscribe(params=>{
      this.flightId=params['flightId'];
    })
    this.route.params.subscribe(params=>{
      this.name=params['name'];
    })
      this.route.params.subscribe(params=>{
        this.age=params['age'];
      })
        this.route.params.subscribe(params=>{
          this.gender=params['gender'];
        })    
        
    this.payForm=this.formBuilder.group({
      
      owner: ['', [Validators.required]],
      cvv: ['', [Validators.required]],
      cardNumber : ['',[Validators.required]]

    });
    

  }

  bookFlight()
  {
      this.submitted = true;
      if(this.payForm.invalid)
      {
        return;
      }
    // alert(localStorage.userId);
      this.flightService.bookFlight(this.flightId,localStorage.userId,this.name,this.age,this.gender).subscribe(data=> {

      this.result = data;
      if(this.result)
      {
        // setTimeout(function(){alert("booking Succesful");},3000);
        alert("booking Succesful");
      }
      else{
        alert("Booking Failed");
      }
          },
      err => {
     
      });
      
      this.router.navigate(['/main/bookings']);
  
  }
}

