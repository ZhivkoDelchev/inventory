import { Component } from '@angular/core';
import {CategoryService} from "./category.service";

@Component({
  selector: 'app-root',
  templateUrl: './category.component.html'
})

export class CategoryComponent {
  categories = [];
  private errorMessage: any;

  constructor(categoryService: CategoryService) {
    categoryService.getCategories().subscribe(categories => this.categories = categories,
      error => this.errorMessage = <any>error)
  }

}
