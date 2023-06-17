import { Injectable } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, CanActivate, CanActivateChild, CanActivateChildFn, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate, CanActivateChild {

  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) { }

  canActivate(route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.checkUser();
  }
  checkUser(): boolean {
    if (sessionStorage.getItem("token") != "undefined") {
      if (sessionStorage.getItem("role") == "ROLE_ADMIN") {
        this.router.navigateByUrl('/admin');
      } else {
        if (sessionStorage.getItem("role") == "ROLE_EMPLOYEE") {
          this.router.navigateByUrl('/employee');
        }
      }
    }
    return true;
  }
  canActivateChild(route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.canActivate(route, state);
  }
}
