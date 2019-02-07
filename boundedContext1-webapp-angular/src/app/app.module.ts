
import { ChartsModule } from 'ng2-charts';
import { LongPressDirective } from './directives/long-press.directive';
import { MatDialogModule, MatDialogRef, MatSidenav } from '@angular/material';
import { EventService } from './providers/event.service';
import { ActiveStateService } from './providers/active-state.service';
import { FireService } from './providers/fire.service';
import { DialogComponent } from './dialog/dialog.component';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { FoodListComponent } from './food-list/food-list.component';
import { FoodDetailComponent } from './food-detail/food-detail.component';
import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { HttpModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatButtonModule, MatCheckboxModule, MatListModule, MatCardModule,
  MatToolbarModule, MatListItem, MatIconModule, MatMenuModule, MatTabsModule, MatDialogContainer, MatSelectModule,
  MatProgressSpinnerModule, MatSliderModule, MatRadioModule, MatDatepickerModule, MatNativeDateModule, DateAdapter,
  MAT_DATE_FORMATS, MatProgressBarModule, MatSidenavModule
} from '@angular/material';
import { LoginPageComponent } from './login-page/login-page.component';
import { AngularFireAuthModule, AngularFireAuth } from 'angularfire2/auth';
import { AngularFireDatabaseModule, AngularFireDatabase, AngularFireList } from 'angularfire2/database';
import { AngularFireModule } from 'angularfire2';
import { environment } from '../environments/environment';
import * as firebase from 'firebase/app';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { ProgressPageComponent } from './progress-page/progress-page.component';
import { ReportComponent } from './report/report.component';


// Initialize Firebase

export const firebaseConfig = {
  apiKey: 'AIzaSyDuTo74D7zmb6vO6bOV5Z1h7IABIEBM0hM',
  authDomain: 'wwwdegloba-1350.firebaseapp.com',
  databaseURL: 'https://wwwdegloba-1350.firebaseio.com',
  projectId: 'wwwdegloba-1350',
  storageBucket: 'wwwdegloba-1350.appspot.com',
  messagingSenderId: '246205539021'
};
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

const routes: Routes = [
  { path: '', component: HomePageComponent, data: { stateName: 'home' } },
  { path: 'food-list', component: FoodListComponent, data: { stateName: 'food-list' } },
  { path: 'food-detail/:id', component: FoodDetailComponent, data: { stateName: 'food-detail' } },
  { path: 'profile', component: ProfilePageComponent, data: { stateName: 'profile' } },
  { path: 'login', component: LoginPageComponent, data: { stateName: 'login' } },
  { path: 'progress', component: ProgressPageComponent, data: { stateName: 'progress' } }
];
@NgModule({
  imports: [
    BrowserModule,
    AngularFireModule.initializeApp(firebaseConfig),
    AngularFireDatabaseModule,
    AngularFireAuthModule,
    BrowserAnimationsModule,
    MatButtonModule, MatCheckboxModule, MatListModule, MatCardModule, MatSliderModule,
    MatToolbarModule, MatIconModule, MatMenuModule, MatTabsModule, MatRadioModule,
    MatSelectModule, MatDialogModule, MatProgressSpinnerModule, MatDatepickerModule,
    ReactiveFormsModule, FormsModule, MatNativeDateModule, ChartsModule, MatProgressBarModule,
    MatSidenavModule,
    RouterModule.forRoot(routes)
  ],
  entryComponents: [
    DialogComponent
  ],
  declarations: [
    AppComponent,
    HomePageComponent,
    FoodDetailComponent,
    FoodListComponent,
    LoginPageComponent,
    FoodDetailComponent,
    LoginPageComponent,
    ProfilePageComponent,
    DialogComponent,
    LongPressDirective,
    ProgressPageComponent,
    ReportComponent
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
  providers: [FireService, ActiveStateService, EventService,
    { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS }],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
