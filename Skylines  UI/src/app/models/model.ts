export class Users {
    
    name: string;
    
    email: string;
    
    username: string;
    
    password: string;
    
    phoneNum: number;

}

export class Flight {

    flightId: number;

    from: string;

    to: string;

    timeD: string;

    fare: number;

    totalFare: number;

    availableSeats: number;
}

export class Booking {

    bookingId: number;
    
    flightData: Flight;
    
    userId: number;
    
    status: string;
    
    name: string;
    
    age: string;
    
    gender: string;

}