import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../shared/services/auth.service';

/**
<div class="descripcio">Pàgina per si ens hem oblidat el password</div>
*/
@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})

export class ForgotPasswordComponent implements OnInit {

  constructor(
    public authService: AuthService
  ) { }

  ngOnInit() {
  }

}