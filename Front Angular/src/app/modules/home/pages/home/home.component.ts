import { Component } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  constructor(private login: LoginService) {}

  public submitForm(formulario: NgForm){
    this.login.login(formulario.value['user'], formulario.value['pswd']);
    formulario.reset();
    alert("Token registrado, você pode acessar as funcionalidades!");
  }
}
