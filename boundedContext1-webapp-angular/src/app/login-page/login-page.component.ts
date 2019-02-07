
import { Component, OnInit } from '@angular/core';

import {FireserviceService} from './../fireservice.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

    isLoggedIn: boolean;

  constructor(public fire: FireserviceService, private rout: Router) { }

  login() {
      this.fire.loginWithGoogle().then((data) => {
          this.rout.navigate(['']);
      });
  }

  ngOnInit() {
  }

}
