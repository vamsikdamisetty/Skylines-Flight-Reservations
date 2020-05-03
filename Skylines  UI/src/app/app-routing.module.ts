import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/home/login/login.component';
import { SignUpComponent } from './components/home/sign-up/sign-up.component';
import { InsideComponent } from './components/home/inside/inside.component';
import { MainComponent } from './components/main/main.component';
import { BookComponent } from './components/main/book/book.component';
import { TicketsComponent } from './components/main/tickets/tickets.component';
import { PaymentComponent } from './components/main/payment/payment.component';
import { AboutComponent } from './components/home/about/about.component';
import { ProfileComponent } from './components/main/profile/profile.component';



const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'home',component:HomeComponent,children:[
                                              
                                              {path:'',component:InsideComponent},
                                              {path:'login',component:LoginComponent},
                                              {path:'signup',component:SignUpComponent},
                                              {path:'about',component :AboutComponent },
                                              {path:'**',component:InsideComponent}
                                            ]
                                          },
  {path:'main',component:MainComponent,children:[
                                              {path:'',component:BookComponent},
                                              {path:'book',component:BookComponent},
                                              {path:'bookings',component:TicketsComponent},
                                              {path:'payment/:flightId/:name/:age/:gender',component:PaymentComponent},
                                              {path:'profile',component:ProfileComponent}
                                            ]
                                          }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
