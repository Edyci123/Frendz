import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form!: FormGroup;
  error: string = '';
  loading: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
    });
  }

  onSubmit() {
    this.loading = true;
    this.authService.login(this.form.value).subscribe({
      next: (response: HttpResponse<any>) => {
        if (response.status == 200) {
          this.router.navigate(['/']);
        }
      },
      error: (error) => {
        this.error = error;
        this.loading = false;
      }
    });
  }

  onAccountAbsence() {
    this.router.navigate(['/register']);
  }
}
