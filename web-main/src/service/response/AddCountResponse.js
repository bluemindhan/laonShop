import BaseResponse from '@/service/response/BaseResponse';
export default class AddCountResponse extends BaseResponse {
  constructor() {
    super();
    this.count = 0;
  }
}
