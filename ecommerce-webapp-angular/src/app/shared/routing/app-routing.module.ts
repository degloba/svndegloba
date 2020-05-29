import { NgModule } from '@angular/core';

// Required services for navigation
import { Routes, RouterModule } from '@angular/router';

// Required components for which route services to be activated
import { SignInComponent } from '../../autenticacio/sign-in/sign-in.component';
import { SignUpComponent } from '../../autenticacio/sign-up/sign-up.component';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { ForgotPasswordComponent } from '../../autenticacio/forgot-password/forgot-password.component';
import { VerifyEmailComponent } from '../../autenticacio/verify-email/verify-email.component';
import { LoginPageComponent } from '../../autenticacio/login-page/login-page.component';

import {HomePageComponent } from '../../food/home-page/home-page.component'

// Import canActivate guard services
import { AuthGuard } from '../../shared/guard/auth.guard';
import { SecureInnerPagesGuard } from '../../shared/guard/secure-inner-pages.guard';

// Include route guard in routes array
const APP_ROUTES: Routes = [
    { path: 'login', component: LoginPageComponent },
    { path: '', redirectTo: '/sign-in', pathMatch: 'full'},
    { path: 'sign-in', component: SignInComponent, canActivate: [SecureInnerPagesGuard]},
    { path: 'register-user', component: SignUpComponent, canActivate: [SecureInnerPagesGuard]},
    { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
    { path: 'forgot-password', component: ForgotPasswordComponent, canActivate: [SecureInnerPagesGuard] },
    { path: 'verify-email-address', component: VerifyEmailComponent, canActivate: [SecureInnerPagesGuard] },
    { path: 'food', component: HomePageComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(APP_ROUTES)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
