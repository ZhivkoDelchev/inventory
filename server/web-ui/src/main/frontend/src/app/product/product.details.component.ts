import { Component, OnInit, OnDestroy  } from '@angular/core';
import { ProductService } from "./product.service";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './product.details.component.html'
})

export class ProductDetailsComponent implements OnInit, OnDestroy {
  product = {};
  errorMessage: any;
  id: number;
  private sub: any;

  constructor (private productService: ProductService, private route: ActivatedRoute) {  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
    });
    this.productService.getProduct(this.id)
      .subscribe(product => this.product = product,
        error =>  this.errorMessage = <any>error);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
