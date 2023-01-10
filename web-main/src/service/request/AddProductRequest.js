export default class AddProductRequest {
  constructor() {
    this.productName = '';
    this.productDesc = '';
    this.productPrice = 0;
    this.productCount = 0;  // 재고수량 추가
    this.imageList = null;
  }
}
