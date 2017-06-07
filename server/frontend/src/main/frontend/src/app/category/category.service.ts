import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import {Category} from './category';
import {HttpErrorHandler} from "../http.error.handler";

@Injectable()
export class CategoryService {

  constructor(private http: Http, private httpErrorHandler: HttpErrorHandler) {
  }

  getCategories(): Observable<Category[]> {
    return this.http.get('api/categories ')
      .map(this.extractData)
      .catch(this.httpErrorHandler.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body || {};
  }
}
