import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { NgxPaginationModule } from 'ngx-pagination';

import { AppComponent } from './app.component';
import { HomeComponent } from './home.component';
import { PageNotFoundComponent } from './not-found.component';
import { ProductComponent } from './product/product.component';
import { ProductService } from './product/product.service';
import { CategoryComponent } from './category/category.component';
import { CategoryService } from './category/category.service';
import { HttpErrorHandler } from './http.error.handler';

const appRoutes: Routes = [
  {
    path: 'products',
    component: ProductComponent,
    data: { title: 'Products List' }
  },
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
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
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
    NgxPaginationModule
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
