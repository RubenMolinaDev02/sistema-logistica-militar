// location.service.ts

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable, catchError, from, switchMap, throwError } from "rxjs";
import { environment } from "../../../environments/environment";
import { LocationResponse } from "../../shared/models/models";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private base = environment.apis.location;

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private router: Router
  ) {}

  // ─────────────────────────────────────────
  // AUTO REFRESH
  // ─────────────────────────────────────────

  private requestWithAuth<T>(
    request: () => Observable<T>
  ): Observable<T> {

    return request().pipe(

      catchError(error => {

        if (error.status === 401) {

          return from(this.authService.refreshToken()).pipe(

            switchMap((newToken: string) => {

              localStorage.setItem('access_token', newToken);

              return request();
            }),

            catchError(refreshError => {

              localStorage.clear();

              this.router.navigate(['/login']);

              return throwError(() => refreshError);
            })
          );
        }

        return throwError(() => error);
      })
    );
  }

  private authHeaders(): HttpHeaders {

    const token = localStorage.getItem('access_token');

    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  // ─────────────────────────────────────────
  // LOCATIONS
  // ─────────────────────────────────────────

  getAll(): Observable<LocationResponse[]> {

    return this.requestWithAuth(() =>

      this.http.get<LocationResponse[]>(
        `${this.base}/organization/locations`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getById(id: string): Observable<LocationResponse> {

    return this.requestWithAuth(() =>

      this.http.get<LocationResponse>(
        `${this.base}/organization/locations/${id}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getByReference(ref: string): Observable<LocationResponse> {

    return this.requestWithAuth(() =>

      this.http.get<LocationResponse>(
        `${this.base}/organization/locations/reference/${ref}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  create(body: any): Observable<LocationResponse> {

    return this.requestWithAuth(() =>

      this.http.post<LocationResponse>(
        `${this.base}/organization/locations`,
        body,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  delete(id: string): Observable<void> {

    return this.requestWithAuth(() =>

      this.http.delete<void>(
        `${this.base}/organization/locations/${id}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }
}