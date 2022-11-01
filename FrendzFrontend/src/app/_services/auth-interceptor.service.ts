import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { TokenStorageService } from './token-storage.service';
import { of } from 'rxjs';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
  providedIn: 'root',
})
export class AuthInterceptorService implements HttpInterceptor {
  constructor(private token: TokenStorageService, private router: Router) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.token.getToken();

    if (token != null) {
      authReq = req.clone({
        headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token),
      });
    }

    if (this.router.url != '/login' && this.router.url != '/register') {
      return next.handle(authReq);
    }

    return next.handle(authReq).pipe(
      catchError((response: HttpErrorResponse) => {
        if (response.status === 401 && this.router.url != '/login' && this.router.url != '/register') {
          this.handleError(response);
        }

        return throwError(() => response);
      }
      ));
  }

  private handleError(error: HttpErrorResponse): Observable<any> {
    
    if (error.status === 401 || error.status === 403) {
      this.token.logout();
      this.router.navigate(['/login']);
      return of(error.message);
    }

    return throwError(() => error);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true },
];
