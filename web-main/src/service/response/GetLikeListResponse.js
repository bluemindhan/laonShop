import BaseResponse from '@/service/response/BaseResponse';
export default class GetLikeListResponse extends BaseResponse {
  constructor() {
    super();
    this.wishList = null;
  }
}