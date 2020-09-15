import { Component, OnInit } from '@angular/core';
import { Hotel } from './model/hotel';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";

/**
// * <div class="descripcio">Pagina principal</div>
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['app.component.scss'],
  providers: []
})
export class AppComponent implements OnInit {
  title = 'Casino';
  angForm: FormGroup;
  
  
  constructor(private fb: FormBuilder) {
      this.createForm();
    }

    createForm() {
      this.angForm = this.fb.group({
        name: ["", Validators.required]
      });
    }
  
  public ngOnInit() {
      
    }
  


}