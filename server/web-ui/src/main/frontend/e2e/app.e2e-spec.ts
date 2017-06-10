import { InvetoryPage } from './app.po';

describe('invetory App', () => {
  let page: InvetoryPage;

  beforeEach(() => {
    page = new InvetoryPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
