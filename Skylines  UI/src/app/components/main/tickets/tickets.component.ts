import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FlightService } from 'src/app/services/flight.service';
import { Booking } from 'src/app/models/model';

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {

  bookings:Booking[];
  booked:boolean = true;
  

  constructor(public router:Router,public flightService:FlightService) { }
 
  ngOnInit() {
    
    
    
    this.getbookings();
    // if(localStorage.username!=null){
     
    // }
    // else{
    //   this.router.navigate(['/login']);
    // }
  }
  getbookings() {
    //localStorage.userId = 1;
    this.flightService.myBookings(localStorage.userId).subscribe(data => {
      // on resolve or on success
      console.log(localStorage.userId);
      this.bookings=data;
      console.log(this.bookings);
      this.booked = false;
    },
      err => {
        // on reject or on error
        console.log(err.stack);
      });
  }

  cancelFlight(bookingId:number,flightId:number)
  {
    this.flightService.cancelFlight(bookingId,flightId).subscribe();
    alert("Flight cancelled succesfully and amount will be credited in less than seven working days ");
    window.location.reload();
    //this.router.navigate(['/main/bookings']);
    
  }
}
