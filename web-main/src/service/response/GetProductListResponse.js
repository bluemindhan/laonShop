import BaseResponse from '@/service/response/BaseResponse';
export default class GetProductListResponse extends BaseResponse {
  constructor() {
    super();
    this.totalCount = 0;
    this.pageNo = 0;
    this.pageSize = 0;
    this.productList = null;
  }
}
