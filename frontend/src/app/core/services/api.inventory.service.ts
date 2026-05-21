// inventory.service.ts

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable, catchError, from, switchMap, throwError } from "rxjs";
import { environment } from "../../../environments/environment";
import { BatchResponse, SerializedUnitResponse, ShipmentResponse, SkuResponse, StockResponse } from "../../shared/models/models";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  private base = environment.apis.inventory;

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
  // SKU
  // ─────────────────────────────────────────

  getAllSkus(): Observable<SkuResponse[]> {

    return this.requestWithAuth(() =>

      this.http.get<SkuResponse[]>(
        `${this.base}/skus`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getSkuById(id: string): Observable<SkuResponse> {

    return this.requestWithAuth(() =>

      this.http.get<SkuResponse>(
        `${this.base}/skus/${id}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  createSku(body: any): Observable<SkuResponse> {

    return this.requestWithAuth(() =>

      this.http.post<SkuResponse>(
        `${this.base}/skus`,
        body,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  deleteSku(id: string): Observable<void> {

    return this.requestWithAuth(() =>

      this.http.delete<void>(
        `${this.base}/skus/${id}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  // ─────────────────────────────────────────
  // STOCK
  // ─────────────────────────────────────────

  getAllStock(): Observable<StockResponse[]> {

    return this.requestWithAuth(() =>

      this.http.get<StockResponse[]>(
        `${this.base}/stock`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getStockBySkuId(
    skuId: string
  ): Observable<StockResponse[]> {

    return this.requestWithAuth(() =>

      this.http.get<StockResponse[]>(
        `${this.base}/stock/sku/${skuId}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  createStock(
    body: any
  ): Observable<StockResponse> {

    return this.requestWithAuth(() =>

      this.http.post<StockResponse>(
        `${this.base}/stock`,
        body,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  increaseStock(
    id: string,
    amount: number
  ): Observable<StockResponse> {

    return this.requestWithAuth(() =>

      this.http.put<StockResponse>(
        `${this.base}/stock/${id}/increase`,
        { amount },
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  decreaseStock(
    id: string,
    amount: number
  ): Observable<StockResponse> {

    return this.requestWithAuth(() =>

      this.http.put<StockResponse>(
        `${this.base}/stock/${id}/decrease`,
        { amount },
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  // ─────────────────────────────────────────
  // SHIPMENTS
  // ─────────────────────────────────────────

  getAllShipments(): Observable<ShipmentResponse[]> {

    return this.requestWithAuth(() =>

      this.http.get<ShipmentResponse[]>(
        `${this.base}/shipments`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getShipmentById(
    id: string
  ): Observable<ShipmentResponse> {

    return this.requestWithAuth(() =>

      this.http.get<ShipmentResponse>(
        `${this.base}/shipments/${id}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  createShipment(
    body: any
  ): Observable<ShipmentResponse> {

    return this.requestWithAuth(() =>

      this.http.post<ShipmentResponse>(
        `${this.base}/shipments`,
        body,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  completeShipment(
    id: string
  ): Observable<void> {

    return this.requestWithAuth(() =>

      this.http.put<void>(
        `${this.base}/shipments/${id}/complete`,
        {},
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  deleteShipment(
    id: string
  ): Observable<void> {

    return this.requestWithAuth(() =>

      this.http.delete<void>(
        `${this.base}/shipments/${id}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getShipmentBatches(
    shipmentId: string
  ): Observable<BatchResponse[]> {

    return this.requestWithAuth(() =>

      this.http.get<BatchResponse[]>(
        `${this.base}/shipments/${shipmentId}/batches`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  // ─────────────────────────────────────────
  // SERIALIZED UNITS
  // ─────────────────────────────────────────

  getUnitsBySkuId(
    skuId: string
  ): Observable<SerializedUnitResponse[]> {

    return this.requestWithAuth(() =>

      this.http.get<SerializedUnitResponse[]>(
        `${this.base}/units/sku/${skuId}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }

  getUnitBySerial(
    serial: string
  ): Observable<SerializedUnitResponse> {

    return this.requestWithAuth(() =>

      this.http.get<SerializedUnitResponse>(
        `${this.base}/units/serial/${serial}`,
        {
          headers: this.authHeaders()
        }
      )
    );
  }
}