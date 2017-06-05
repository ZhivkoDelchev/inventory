import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: 'product.component.html'
})
export class ProductComponent {
  products = [{name: 'foo'}, {name: 'bar'}]
}
