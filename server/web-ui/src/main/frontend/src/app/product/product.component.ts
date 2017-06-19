import {Component} from '@angular/core';
import {ProductService} from "./product.service";
import {Product} from "./product";

@Component({
  selector: 'app-root',
  templateUrl: './product.component.html'
})

export class ProductComponent {
  products: Product[];
  total: number;
  errorMessage: any;

  constructor(private productService: ProductService) {
    this.getProducts(1);
  }

  getProducts(page: number) {
    this.productService.getProducts(page)
      .subscribe(response => {
          this.total = response.total;
          this.products = response.data;
        },
        error => this.errorMessage = <any>error
      );
  }
}
