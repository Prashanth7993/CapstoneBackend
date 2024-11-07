import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { AuthModule } from './auth/auth.module';
import { NotFoundComponent } from './not-found/not-found.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { SpinnerComponent } from './spinner/spinner.component';
import { LucideAngularModule, File, Home, Menu, UserCheck,LayoutDashboard,UserCog,CarTaxiFront,TicketCheck,Bus, MessageCircleHeart,BadgeIndianRupee } from 'lucide-angular';

@NgModule({
  declarations: [
    AppComponent,
    MainLayoutComponent,
    AuthLayoutComponent,
    NotFoundComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    DashboardModule,
    LucideAngularModule.pick({File, Home, Menu, UserCheck,LayoutDashboard,UserCog,CarTaxiFront,TicketCheck,Bus,MessageCircleHeart,BadgeIndianRupee })
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
