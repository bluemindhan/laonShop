import BaseResponse from '@/service/response/BaseResponse';
export default class UpdateProfileResponse extends BaseResponse {
  constructor() {
    super();
    this.userInfo = null;
  }
}
