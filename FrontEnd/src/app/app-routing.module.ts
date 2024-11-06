import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgotPasswordComponent } from './auth/forgot-password/forgot-password.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { ResetPasswordComponent } from './auth/reset-password/reset-password.component';
import { BookingsComponent } from './dashboard/bookings/bookings.component';
import { BusComponent } from './dashboard/bus/bus.component';
import { CarpoolComponent } from './dashboard/carpool/carpool.component';
import { HomeComponent } from './dashboard/home/home.component';
import { OperatorsComponent } from './dashboard/operators/operators.component';
import { UsersComponent } from './dashboard/users/users.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { NotFoundComponent } from './not-found/not-found.component';

const routes: Routes = [
  {
    path: 'admin',
    component: MainLayoutComponent,
    children: [
      { path: 'dashboard', component: HomeComponent },
      { path: 'users', component: UsersComponent },
      { path: 'operators', component: OperatorsComponent },
      { path: 'bus', component: BusComponent },
      { path: 'car-pool', component: CarpoolComponent },
      { path: 'bookings', component: BookingsComponent },
    ],
  },
  {
    path: 'auth',
    component: AuthLayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'forgot-password', component: ForgotPasswordComponent },
      { path: 'reset-password', component: ResetPasswordComponent },
    ],
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
