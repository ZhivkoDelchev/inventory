import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import {Product} from './product';
import {HttpErrorHandler} from "../http.error.handler";

@Injectable()
export class ProductService {

  private productsUrl = 'api/products ';

  constructor(private http: Http, private httpErrorHandler: HttpErrorHandler) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get(this.productsUrl)
      .map(this.extractData)
      .catch(this.httpErrorHandler.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body || {};
  }
}
