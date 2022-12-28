import BaseResponse from '@/service/response/BaseResponse';
export default class SendItemsResponse extends BaseResponse {
  constructor() {
    super();
    this.items = null;
  }
}
