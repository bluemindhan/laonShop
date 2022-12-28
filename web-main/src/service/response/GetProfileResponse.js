import BaseResponse from '@/service/response/BaseResponse';
export default class GetProfileResponse extends BaseResponse {
  constructor() {
    super();
    this.userInfo = null;
  }
}
