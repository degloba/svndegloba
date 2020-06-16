
import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../shared/services/auth.service';
import {Router} from '@angular/router';

/**
<div class="descripcio">Pàgina principal d'autentificació</div>
*/
@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

    isLoggedIn: boolean;

  constructor(public fire: AuthService, private rout: Router) { }


  ngOnInit() {
  }

}
