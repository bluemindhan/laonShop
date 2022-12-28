import BaseResponse from '@/service/response/BaseResponse';
export default class SignInResponse extends BaseResponse {
  constructor() {
    super();
    this.userInfo = null;
    this.accessToken = '';
  }
}
