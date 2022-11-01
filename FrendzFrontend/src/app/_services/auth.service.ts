import {
  HttpClient,
  HttpErrorResponse,
  HttpResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, catchError, tap, throwError } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

const AUTH_API = 'http://localhost:9000/api/auth/';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private token: TokenStorageService, private router: Router) {}

  register(credentials: { username: string; email: string; password: string }) {
    return this.http.post(AUTH_API + 'register', credentials);
  }

  login(credentials: { username: string; password: string }) {
    return this.http
      .post(AUTH_API + 'login', credentials, {
        observe: 'response',
      })
      .pipe(
        catchError(this.handleError),
        tap((response: HttpResponse<any>) => {
          this.handleAuthentication(response.body.accessToken);
        })
      );
  }

  logout() {
    /// call backend 
    this.token.logout();
    this.router.navigate(['/login']);
  }

  private handleAuthentication(token: string) {
    this.token.saveToken(token);
  }

  private handleError(errorRes: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    return throwError(() => errorMessage);
  }
}
