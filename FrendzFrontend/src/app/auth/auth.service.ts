import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmailValidator } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private url = 'http://localhost:9000/api/auth';

  constructor(private httpClient: HttpClient) {}

  login(username: String, password: String) {
    let loginUrl = this.url + '/login';
    this.httpClient
      .post<any>(loginUrl, { username: username, password: password })
      .subscribe((data) => {
        localStorage.setItem('token', data);
      });
  }

  register(username: String, email: String, password: String) {
    let registerUrl = this.url + '/register';
    this.httpClient.post<any>(registerUrl, {
      username: 'username',
      email: email,
      password: password,
    }).subscribe(data => {
      console.log(data);
    });
  }
}
