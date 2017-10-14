export class Product {
  constructor(public name: string,
              public category: string,
              public description: string,
              public price: number,
              public id: number,
              public pictures: number[]) {

  }
}

export class PagedProducts {
  total: number;
  data: Product[];
}
