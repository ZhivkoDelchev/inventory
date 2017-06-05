import { Component } from '@angular/core';
import {ProductService} from "./product.service";

@Component({
  selector: 'app-root',
  templateUrl: 'product.component.html'
})
export class ProductComponent {
  products = [];
  errorMessage: any;

  constructor (private productService: ProductService) {
    this.getProducts();
  }

  getProducts() {
    this.productService.getProducts()
      .subscribe(products => this.products = products,
        error =>  this.errorMessage = <any>error);
  }
}
