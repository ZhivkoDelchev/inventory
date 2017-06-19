import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import {Observable} from 'rxjs/Observable';

import {PagedProducts} from './product';
import {HttpErrorHandler} from "../http.error.handler";

@Injectable()
export class ProductService {

  private productsUrl = 'api/products/page/';

  constructor(private http: Http, private httpErrorHandler: HttpErrorHandler) {
  }

  getProducts(page: number): Observable<PagedProducts> {
    return this.http.get(this.productsUrl + page)
      .map(this.extractData)
      .catch(this.httpErrorHandler.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body || {};
  }
}
