import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from './auth/registration/registration.component';
import { LoginComponent } from './auth/login/login.component';
import { BusTrackingComponent } from './components/bus-tracking/bus-tracking.component';
import { NearestMetroComponent } from './components/nearest-metro/nearest-metro.component';
import { CarpoolingComponent } from './components/carpooling/carpooling.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'bus-tracking',
    component: BusTrackingComponent
  },
  {
    path: 'nearest-metro',
    component: NearestMetroComponent
  },
  {
    path: 'carpooling',
    component: CarpoolingComponent
  },
  // { path: '**', redirectTo: '/login' } // Redirect any unknown routes to login
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
