import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/services/flight.service';
import { Users } from 'src/app/models/model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user : Users;
  constructor(private flightService:FlightService) { }

  ngOnInit() {  
    
    this.flightService.getUser(localStorage.userId).subscribe(data=>{
      this.user = data;
      console.log(this.user);
    });

    
  }

}
