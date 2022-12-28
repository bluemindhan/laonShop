export default class ResultCode {
  static Success = 200; // 성공
  static Failed = -1; // 실패
  static InvalidParameter = 100; // 파라미터가 유효하지 않습니다.
  static Unauthorized = 401; // Unauthorized
  static InternalServerError = 500; // 서버 내부 오류
  static NotFoundUser = 1000; // 사용자를 찾을 수 없습니다.
  static NotFoundSeller = 1001; // 판매자를 찾을 수 없습니다.
  static DuplicateUser = 1002; // 이미 등록된 사용자입니다.
  static DuplicateSeller = 1003; // 이미 등록된 판매자입니다.
  static AlreadyExistEmail = 10010; // 이미 사용중인 이메일입니다.
  static BadParams = 10100; // 파라미터가 유효하지 않습니다.
  static InvalidPassword = 10020; // 비밀번호가 유효하지 않습니다.
  static DbFailed = 99999; // DB 실패
  static get(code /*: number */) {
    switch (code) {
      case 200:
        return '성공';
      case -1:
        return '실패';
      case 100:
        return '파라미터가 유효하지 않습니다.';
      case 401:
        return 'Unauthorized';
      case 500:
        return '서버 내부 오류';
      case 1000:
        return '사용자를 찾을 수 없습니다.';
      case 1001:
        return '판매자를 찾을 수 없습니다.';
      case 1002:
        return '이미 등록된 사용자입니다.';
      case 1003:
        return '이미 등록된 판매자입니다.';
      case 10010:
        return '이미 사용중인 이메일입니다.';
      case 10100:
        return '파라미터가 유효하지 않습니다.';
      case 10020:
        return '비밀번호가 유효하지 않습니다.';
      case 99999:
        return 'DB 실패';
    }
    return 'Uknown';
  }
}
