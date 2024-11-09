import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
// import { NavbarComponent } from './components/navbar/navbar.component';
import { BusTrackingComponent } from './components/bus-tracking/bus-tracking.component';
// import { BusListComponent } from './components/bus-list/bus-list.component';
import { NearestMetroComponent } from './components/nearest-metro/nearest-metro.component';
import { CarpoolingComponent } from './components/carpooling/carpooling.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { TrackingComponent } from './components/tracking/tracking.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    // NavbarComponent,
    BusTrackingComponent,
    // BusListComponent,
    NearestMetroComponent,
    CarpoolingComponent,
    TrackingComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
