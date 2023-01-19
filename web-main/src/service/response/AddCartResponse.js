import BaseResponse from '@/service/response/BaseResponse';
export default class AddCartResponse extends BaseResponse {
  constructor() {
    super();
    this.cartVo = null;
  }
}