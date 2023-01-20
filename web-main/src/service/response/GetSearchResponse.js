import BaseResponse from '@/service/response/BaseResponse';
export default class GetSearchResponse extends BaseResponse {
  constructor() {
    super();
    this.categoryList = null;
    this.products = null;
  }
}
