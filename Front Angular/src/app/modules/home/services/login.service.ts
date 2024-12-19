import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { error } from 'console';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private url: string = "http://localhost:8080/api/spring/"

  constructor(private http : HttpClient) {  }

  public login(user: string,  pswd: string){
    return this.http.post(`${this.url}user/auth`, {
      user: user,
      pswd: pswd
    }).subscribe(data => {
      var Token = JSON.parse(JSON.stringify(data)).token;
      localStorage.setItem("Token", Token);
    },
    error => (console.error("Erro ao fazer login!"))
  )

  }
}
