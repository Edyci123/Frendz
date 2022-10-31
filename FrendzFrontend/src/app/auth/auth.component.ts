import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
})
export class AuthComponent implements OnInit {
  isLoginMode = true;
  form!: FormGroup;

  constructor(private authService: AuthService,
    private router: Router) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl(''),
      email: new FormControl(''),
      password: new FormControl(''),
    });
  }

  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
  }

  onSubmit() {
    if (this.isLoginMode) {
      const username = this.form.value.username;
      const password = this.form.value.password;

      this.authService.login({username, password})
      .subscribe(
        (response :HttpResponse<any>) => {
            console.log(response.body.accessToken);
            this.router.navigate(['/']);
        },
        (error) => {
          console.log(error);
        }
      );
    } else {
      this.authService.register(this.form.value).subscribe(
        () => {
            this.form.reset();
            this.isLoginMode = true;
          
        },
        (error) => {
          console.log(error);
        }
      );
    }

   
  }
}
