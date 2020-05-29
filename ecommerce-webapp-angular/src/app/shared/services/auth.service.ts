import { Injectable, NgZone } from '@angular/core';

import { User } from '../services/user';
import { auth } from 'firebase/app';
import { AngularFireAuth } from '@angular/fire/auth';
import { AngularFirestore, AngularFirestoreDocument } from '@angular/fire/firestore';

import { AngularFireDatabase } from '@angular/fire/database';

import { Router } from '@angular/router';

import { Food, Meal, Units, Goals, DietDays, Persona } from '../../model/data-model';
import { Observable } from "rxjs";

import * as firebase from 'firebase/app';

@Injectable({
  providedIn: 'root'
})

/** 
 * Servei autentificació utilitzant Firebase 
 */
export class AuthService {
  /** 
   * <div class="descripcio">dades de l'usuari logged</div> 
  */
  userData: any; // Save logged in user data

  database: any;

    /** 
     * <div class="descripcio">usuari en l'entorn Firebase/Google</div> 
     */
  fireUser: firebase.User;

  constructor(
    public afs: AngularFirestore,   // Inject Firestore service
    public afAuth: AngularFireAuth, // Inject Firebase auth service
    public afDatabase: AngularFireDatabase, // Inject Firebase Database
    public router: Router,
    public ngZone: NgZone // NgZone service to remove outside scope warning
  ) {
      
      this.database = firebase.database();
      
      
    /**
     *  <div class="descripcio">Guarda les dades d'usuari en localstorage quan entra en 
     *  una sessió i posa null quan es desconnecta</div> 
     */
    this.afAuth.authState.subscribe(user => {
      if (user) {
        this.userData = user;
        localStorage.setItem('user', JSON.stringify(this.userData));
        JSON.parse(localStorage.getItem('user'));
      } else {
        localStorage.setItem('user', null);
        JSON.parse(localStorage.getItem('user'));
      }
    });
  }

  /** 
   * <div class="descripcio">Inicia sessió amb email/passw</div>
   * 
   */
  SignIn(email, password) {
     return firebase.auth().signInWithEmailAndPassword(email, password)    
      .then((result) => {
        this.ngZone.run(() => {
          this.router.navigate(['dashboard']); // si no hi ha hagut error, "navego" al "path" dashboard
        });
        this.SetUserData(result.user);
      }).catch((error) => {
        window.alert(error.message);
      });
  }

  /**
   * <div class="descripcio">Registra amb email/password</div>   
   */
  SignUp(email, password) {
    return firebase.auth().createUserWithEmailAndPassword(email, password)
      .then((result) => {
        /* Call the SendVerificaitonMail() function when new user sign
        up and returns promise */
        this.SendVerificationMail();
        this.SetUserData(result.user);
      }).catch((error) => {
        window.alert(error.message);
      });
  }

  /** 
   * <div class="descripcio">Envia un email de verificació quan un nou usuari entra a una sessió</div>
   * 
   */
  SendVerificationMail() {
    return firebase.auth().currentUser.sendEmailVerification()
    .then(() => {
      this.router.navigate(['verify-email-address']);
    });
  }

  /** 
   * <div class="descripcio">Reset Forggot passw</div>
   */
  ForgotPassword(passwordResetEmail) {
    return firebase.auth().sendPasswordResetEmail(passwordResetEmail)
    .then(() => {
      window.alert('Password reset email sent, check your inbox.');
    }).catch((error) => {
      window.alert(error);
    });
  }

  /** 
   * <div class="descripcio">Retorna true quan l'usuari ha entrat a la sessió  i el email és verificat</div> 
   */
  get isLoggedIn(): boolean {
    const user = JSON.parse(localStorage.getItem('user'));
    return (user !== null && user.emailVerified !== false) ? true : false;
  }

  /** 
   * <div class="descripcio">Inicia sessió amb Google</div>    
   */
  GoogleAuth() {
    return this.AuthLogin(new auth.GoogleAuthProvider());
  }

    /** 
     * <div class="descripcio">Incia sessió amb Facebook</div> 
     */
  FacebookAuth() {
    return this.AuthLogin(new auth.FacebookAuthProvider());
  }

      /** 
       * <div class="descripcio">Inicia sessió amb email</div>
       */
  EmailAuth() {
    return this.AuthLogin(new auth.EmailAuthProvider());
  }

  /** 
   * <div class="descripcio">Lògica de autentificació per executar proveidors d'autentificació</div>
  */
  AuthLogin(provider) {
    return firebase.auth().signInWithPopup(provider)
    .then((result) => {
       this.ngZone.run(() => {
          this.router.navigate(['dashboard']);
        });
      this.SetUserData(result.user);
    }).catch((error) => {
      window.alert(error);
    });
  }


  /** 
   * <div class="descripcio">Setting up user data when sign in with username/password,
  sign up with username/password and sign in with social auth
  provider in Firestore database using AngularFirestore + AngularFirestoreDocument service</div> 
  */
  SetUserData(user) {
    const userRef: AngularFirestoreDocument<any> = this.afs.doc(`users/${user.uid}`);
    const userData: User = {
      uid: user.uid,
      email: user.email,
      displayName: user.displayName,
      photoURL: user.photoURL,
      emailVerified: user.emailVerified
    };
    return userRef.set(userData, {
      merge: true
    });
  }

  /** 
   * <div class="descripcio">Tanca sessió</div>
   */
  SignOut() {
    return firebase.auth().signOut().then(() => {
      localStorage.removeItem('user');
      this.router.navigate(['sign-in']);
    });
  }
  
  // A PARTIR D'AQUI FOOD!!!!!
  /** 
   * <div class="descripcio">Retorn l'usuari</div>
   */
  getUser() {
      const database = firebase.database;
      return this.database.ref('/users/' + this.fireUser.uid).once('value');
    };
    getUserData() {
        const database = firebase.database();
        return this.database.ref('/profiles/' + this.fireUser.uid).once('value');
      }
      setUserData(fireUser: firebase.User, user: Persona) {
        this.database.ref('profiles/' + fireUser.uid).set({
          birthday: user.birthday,
          email: user.email,
          postalCode: user.postalCode,
          country: user.country,
          goals: user.goals,
          units: user.units,
          weight: user.weight,
          height: user.height,
          name: user.name

        });
      }
      createUser(fireUser: firebase.User) {
        return new Promise((resolve, reject) => {
          this.database.ref('users/' + fireUser.uid).set({
            email: fireUser.email,
            profile_picture: fireUser.photoURL
          });
          resolve(0);
        });
      }

      getFoods() {
        return this.database.ref('/foods').once('value');
      }

      getUserDietDays() {
        this.fireUser = JSON.parse(localStorage.getItem('fireUser'));
        return this.database.ref('/dietDays/' + this.fireUser.uid).once('value');
      }
      setUserDietDays(userId, dietDays: DietDays[]) {
        this.database.ref('dietDays/' + userId).set({
          dietDays: dietDays
        });
      }

      getFoodById(id): Promise<Food> {
        return new Promise((resolve, reject) => {
          this.getFoods().then(snapshot => {
            snapshot.forEach(child => {
              const food = child.val();
              if (food.id === id) {
                resolve(food);
              }
            });
          });
        });
      }
}
