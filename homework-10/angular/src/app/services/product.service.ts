import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Page} from "../model/page";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  public findAll() {
    return this.http.get<Page>("rest/v1/product/all")
  }

  public delete(id: number | string) {
    return this.http.delete(`rest/v1/product`, {
      params: new HttpParams().set(`id`, id)
    });
  }
}
