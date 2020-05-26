import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { HttpClientModule } from '@angular/common/http';

// Reactive Form
import { ReactiveFormsModule } from '@angular/forms';

// App routing modules
import { AppRoutingModule } from './shared/routing/app-routing.module';

// Components del modul App (modul root)
import { AppComponent } from './app.component';

// Autentificacio components
import { SignInComponent } from './autenticacio/sign-in/sign-in.component';
import { SignUpComponent } from './autenticacio/sign-up/sign-up.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ForgotPasswordComponent } from './autenticacio/forgot-password/forgot-password.component';
import { VerifyEmailComponent } from './autenticacio/verify-email/verify-email.component';

// Firebase services + environment module
import { AngularFireModule } from '@angular/fire';
import { AngularFireAuthModule } from '@angular/fire/auth';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import { AngularFireDatabaseModule } from '@angular/fire/database';

import { environment } from '../environments/environment';

// Auth service
import { AuthService } from './shared/services/auth.service';

// BigFood
import { ProfilePageComponent } from './food/profile-page/profile-page.component';
import { LoginPageComponent } from './autenticacio/login-page/login-page.component';

import { MAT_LABEL_GLOBAL_OPTIONS, MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatIconRegistry } from '@angular/material/icon';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatStepperModule } from '@angular/material/stepper';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio';
import { MatRippleModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTreeModule } from '@angular/material/tree';
import {MaterialModule} from '../material-module';

import { ChartsModule } from 'ng2-charts';
import { LongPressDirective } from './directives/long-press.directive';
import { HomePageComponent } from './food/home-page/home-page.component';
import { FoodListComponent } from './food/food-list/food-list.component';
import { FoodDetailComponent } from './food/food-detail/food-detail.component';

import { DialogComponent } from './food/dialog/dialog.component';
import { ProgressPageComponent } from './food/progress-page/progress-page.component';
import { ReportComponent } from './report/report.component';

import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ActiveStateService } from './providers/active-state.service';
import { EventService } from './providers/event.service';

import { PolicyListComponent } from './policy-list/policy-list.component';
import { LayoutModule } from '@angular/cdk/layout';

// API Rest
import { TodoListComponent } from './todo/todo-list/todo-list.component';
import { TodoListFooterComponent } from './todo/todo-list-footer/todo-list-footer.component';
import { TodoListHeaderComponent } from './todo/todo-list-header/todo-list-header.component';
//////////import { TodoDataService } from './providers/todo-data.service';
import { TodoListItemComponent } from './todo/todo-list-item/todo-list-item.component';
import { ApiService } from './providers/api.service';
import { HotelsComponent } from './hotels/hotels.component';
import { HotelsListHeaderComponent } from './hotels/hotels-list-header/hotels-list-header.component';
import { HotelsListComponent } from './hotels/hotels-list/hotels-list.component';
import { HotelsListFooterComponent } from './hotels/hotels-list-footer/hotels-list-footer.component';
import { HotelsListItemComponent } from './hotels/hotels-list-item/hotels-list-item.component';


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
    TodoListComponent,
    TodoListFooterComponent,
    TodoListHeaderComponent,
    TodoListItemComponent,
    HotelsComponent,
    HotelsListHeaderComponent,
    HotelsListComponent,
    HotelsListFooterComponent,
    HotelsListItemComponent
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
    LayoutModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthService, ActiveStateService, EventService, ApiService  //TodoDataService, 
              ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class AppModule { }












