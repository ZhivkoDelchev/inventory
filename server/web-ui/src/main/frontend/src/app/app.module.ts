import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { HomeComponent } from './home.component';
import { PageNotFoundComponent } from './not-found.component';
import { ProductsComponent } from './product/products.component';
import { ProductDetailsComponent } from './product/product.details.component';
import { ProductService } from './product/product.service';
import { CategoryComponent } from './category/category.component';
import { CategoryService } from './category/category.service';
import { HttpErrorHandler } from './http.error.handler';
import { ImageUploadModule } from 'angular2-image-upload/lib/image-upload.module';

const appRoutes: Routes = [
  {
    path: 'products',
    component: ProductsComponent,
    data: { title: 'Products List' }
  },
  {
    path: 'product-details/:id',
    component: ProductDetailsComponent,
    data: {
      title: 'Product'
    }},
  {
    path: 'categories',
    component: CategoryComponent,
    data: { title: 'Products List' }
  },
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    ProductDetailsComponent,
    PageNotFoundComponent,
    HomeComponent,
    CategoryComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    ImageUploadModule.forRoot(),
  ],
  providers: [
    ProductService,
    CategoryService,
    HttpErrorHandler
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
