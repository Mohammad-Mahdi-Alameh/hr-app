import { Injectable } from '@angular/core';
import { Login } from '../../models/Login.model';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  error = null;
  private role: string;
  isLoggedIn = false;

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }

  loginSuccess(response): string {
    sessionStorage.setItem("token", response.accessToken);
    this.role = response.roles[0];
    sessionStorage.setItem("role", this.role);
    this.isLoggedIn = true;
    return this.role;
  }

  setRole(role: string) {
    this.role = role;
  }

  getRole(): string {
    return this.role;
  }

  login(login: Login) {
    return this.http
      .post<Login>(
        'http://localhost:8080/api/auth/signin',
        login
      )
  }

  logout() {
    this.isLoggedIn = false;
    sessionStorage.clear();
    this.router.navigateByUrl('/login');

  }
}
