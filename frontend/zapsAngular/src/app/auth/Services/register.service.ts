import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject, catchError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { RegisterRequest } from '../interface/registerRequest';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  register(userData: RegisterRequest): Observable<any> {
    return this.http.post<any>(environment.urlHost + "auth/register", userData).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('Se ha producido un error ', error.error);
    } else {
      console.error('Backend retornó el código de estado ', error);
    }
    return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'));
  }
}
