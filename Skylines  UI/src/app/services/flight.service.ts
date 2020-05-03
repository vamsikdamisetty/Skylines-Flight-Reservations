import { Injectable } from '@angular/core';
import { Users, Booking } from '../models/model';
import { Flight } from '../models/model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  signUp : boolean;
  baseUrl : string = "http://localhost:8094/";
  constructor(private http : HttpClient) { }


  // This will add the User details to the backend.
  addUser(user:Users){
    return this.http.post(this.baseUrl+"/addUser",user);
  }

  // This will Validate the login credentials
  loginValidate(uName:string,password:string)
  {
    return this.http.get<number>(this.baseUrl+"/login/"+uName+"/"+password);
  }

  // Fetch the details of the user
  getUser(userId:number)
  {
    return this.http.get<Users>(this.baseUrl+"/getUser/"+userId);
  }

  // Search and fetch the details of the Flight 
  searchFlight(from:string,to:string)
  {
    return this.http.get<Flight>(this.baseUrl+"/searchFlight/"+from+"/"+to);
  }


  // To make a Reservation 
  bookFlight(flightId:number,userId:number,name: string,age:string,gender:string)
  {
    return this.http.get<boolean>(this.baseUrl+"/bookFlight/"+flightId + "/" + userId + "/"
    + name  + "/" + age + "/" + gender);
  }


  // Fetch all the bookings of current user 
  myBookings(userId:number)
  {
    return this.http.get<Booking[]>(this.baseUrl+"/all/"+userId);
  }

  // Cancel the Flight 
  cancelFlight(bookingId:number,flightId:number)
  {
    return this.http.get<boolean>(this.baseUrl+"/cancelFlight/"+bookingId
    +"/"+flightId);
  }

}
