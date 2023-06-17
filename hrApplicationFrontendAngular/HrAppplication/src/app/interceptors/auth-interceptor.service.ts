import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler
} from '@angular/common/http';

export class AuthInterceptorService implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const baseUrl = 'http://localhost:8080/api';
    const excludedUrls = baseUrl+'/auth/signin';
    if (excludedUrls === req.url) {
      return next.handle(req);
    }
    const modifiedRequest = req.clone({
      url: baseUrl +'/v1'+ req.url,
      headers: req.headers.append('Authorization', 'Bearer ' + sessionStorage.getItem("token"))
    });
    return next.handle(modifiedRequest);
  }
}
