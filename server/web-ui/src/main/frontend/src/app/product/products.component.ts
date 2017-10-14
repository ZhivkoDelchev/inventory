import { Component } from '@angular/core';
import { ProductService } from "./product.service";

@Component({
  selector: 'app-root',
  templateUrl: './products.component.html'
})

export class ProductsComponent {
  products = [];
  errorMessage: any;

  constructor (productService: ProductService) {
    productService.getProducts()
      .subscribe(products => this.products = products,
        error =>  this.errorMessage = <any>error);
  }

}
