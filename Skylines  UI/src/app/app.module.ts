import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/home/login/login.component';
import { SignUpComponent } from './components/home/sign-up/sign-up.component';
import { InsideComponent } from './components/home/inside/inside.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MainComponent } from './components/main/main.component';
import { BookComponent } from './components/main/book/book.component';
import { TicketsComponent } from './components/main/tickets/tickets.component';
import { PaymentComponent } from './components/main/payment/payment.component';
import { AboutComponent } from './components/home/about/about.component';
import { ProfileComponent } from './components/main/profile/profile.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignUpComponent,
    InsideComponent,
    MainComponent,
    BookComponent,
    TicketsComponent,
    PaymentComponent,
    AboutComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
