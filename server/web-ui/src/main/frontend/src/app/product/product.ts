export class Product {
  constructor(public name: string, public category: string, public description: string, public price: number) {

  }
}

export class PagedProducts {
  total: number;
  data: Product[];
}
