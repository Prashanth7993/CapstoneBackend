import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './home/home.component';
import { BookingsComponent } from './bookings/bookings.component';
import { CarpoolComponent } from './carpool/carpool.component';
import { BusComponent } from './bus/bus.component';
import { OperatorsComponent } from './operators/operators.component';



@NgModule({
  declarations: [
    UsersComponent,
    HomeComponent,
    BookingsComponent,
    CarpoolComponent,
    BusComponent,
    OperatorsComponent
  ],
  imports: [
    CommonModule
  ]
})
export class DashboardModule { }
