import BaseResponse from '@/service/response/BaseResponse';
export default class GetSearchResponse extends BaseResponse {
  constructor() {
    super();
    this.categoryList = null;
    this.totalCount = 0;
    this.pageNo = 0;
    this.pageSize = 0;
    this.products = null;
  }
}
