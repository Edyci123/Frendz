import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  register(credentials: { username: string; email: string; password: string }) {
    return this.http.post('http://localhost:9000/api/auth/register', credentials);
  }

  login(credentials: { username: string; password: string }) {
    return this.http.post('http://localhost:9000/api/auth/login', credentials, {observe: 'response'});
  }
}
