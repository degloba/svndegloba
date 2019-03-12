import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 

// Reactive Form
import { ReactiveFormsModule } from '@angular/forms';

// App routing modules
import { AppRoutingModule } from './shared/routing/app-routing.module';

// App components
import { AppComponent } from './app.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { VerifyEmailComponent } from './components/verify-email/verify-email.component';

// Firebase services + enviorment module
import { AngularFireModule } from '@angular/fire';
import { AngularFireAuthModule } from '@angular/fire/auth';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import { AngularFireDatabaseModule } from '@angular/fire/database';
import { environment } from '../environments/environment';

// Auth service
import { AuthService } from './shared/services/auth.service';

// BigFood
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { MatDialogModule, MatDialogRef, MatSidenav } from '@angular/material';
import {
  MatButtonModule, MatCheckboxModule, MatListModule, MatCardModule,
  MatToolbarModule, MatListItem, MatIconModule, MatMenuModule, MatTabsModule, MatDialogContainer, MatSelectModule,
  MatProgressSpinnerModule, MatSliderModule, MatRadioModule, MatDatepickerModule, MatNativeDateModule, DateAdapter,
  MAT_DATE_FORMATS, MatProgressBarModule, MatSidenavModule
} from '@angular/material';
import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { LongPressDirective } from './directives/long-press.directive';
import { HomePageComponent } from './home-page/home-page.component';
import { FoodListComponent } from './food-list/food-list.component';
import { FoodDetailComponent } from './food-detail/food-detail.component';
import { DialogComponent } from './dialog/dialog.component';
import { ProgressPageComponent } from './progress-page/progress-page.component';
import { ReportComponent } from './report/report.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ActiveStateService } from './providers/active-state.service';
import { EventService } from './providers/event.service';
import { PolicyListComponent } from './policy-list/policy-list.component';
import { MyNavComponent } from './my-nav/my-nav.component';
import { LayoutModule } from '@angular/cdk/layout';

const MY_DATE_FORMATS = {
  parse: {
    dateInput: { month: 'short', year: 'numeric', day: 'numeric' }
  },
  display: {
    // dateInput: { month: 'short', year: 'numeric', day: 'numeric' },
    dateInput: 'input',
    monthYearLabel: { year: 'numeric', month: 'short' },
    dateA11yLabel: { year: 'numeric', month: 'long', day: 'numeric' },
    monthYearA11yLabel: { year: 'numeric', month: 'long' },
  }
};

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    DashboardComponent,
    ForgotPasswordComponent,
    VerifyEmailComponent,
    LoginPageComponent,
    HomePageComponent,
    FoodDetailComponent,
    FoodListComponent,
    DialogComponent,
    LongPressDirective,
    ProgressPageComponent,
    ReportComponent,
    ProfilePageComponent,
    PolicyListComponent,
    MyNavComponent    
  ],
  exports: [
    AppComponent,
    HomePageComponent,
    FoodDetailComponent,
    FoodListComponent,
    DialogComponent,
    FormsModule,
    MatButtonModule, MatCheckboxModule, MatListModule, MatCardModule, MatMenuModule, MatSliderModule,
    MatToolbarModule, MatIconModule, MatTabsModule, MatDialogModule, MatSelectModule, MatRadioModule,
    MatProgressSpinnerModule, LongPressDirective, MatDatepickerModule, MatNativeDateModule, ChartsModule,
    MatProgressBarModule, MatSidenavModule
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireAuthModule,
    AngularFirestoreModule,
    AngularFireDatabaseModule,
    ReactiveFormsModule,
    MatButtonModule, MatCheckboxModule, MatListModule, MatCardModule, MatSliderModule,
    MatToolbarModule, MatIconModule, MatMenuModule, MatTabsModule, MatRadioModule,
    MatSelectModule, MatDialogModule, MatProgressSpinnerModule, MatDatepickerModule,
    ReactiveFormsModule, FormsModule, MatNativeDateModule, ChartsModule, MatProgressBarModule,
    MatSidenavModule,
    BrowserAnimationsModule,
    LayoutModule
  ],
  providers: [AuthService, ActiveStateService, EventService,
    { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class AppModule { }












