# 🛍 Laon Shop(라온샵)

</br>


## 📅개발일정
2022.12.21~2023.01.25

## 목차
1. [프로젝트 소개](#프로젝트-소개)
2. [팀원 소개](#팀원-소개)
3. [개발환경 및 언어](#개발환경-및-언어)
4. [개발 목표](#개발-목표)
5. [데이터베이스](#데이터베이스)
6. [프로젝트 제공기능](#프로젝트-제공기능)
7. [프로젝트 화면](#프로젝트-화면)

</br>

## 프로젝트 소개
>기존의 MPA(Many Page Application) SSR(Server Side Rendering) 방식은  
새로운 페이지로 이동할 때마다 페이지가 깜빡거리는 현상 발생한다.  
이를 보완하기 위해 CSR(Client Side Rendering) 방식을 활용하는
SPA(Single Page Application)를 적용한 쇼핑몰 사이트 개발

</br>

## 팀원 소개
🟢 BACKEND
- `김지현` : 판매자 상품 목록 조회 / 판매자 상품 등록 / 판매자 상품 수정 및 삭제
- `최지웅` : 회원 로그인 / 회원 정보 수정, 회원 탈퇴 /  사용자 상품 목록 조회 및 구매 이력 조회 / 사용자 찜 목록 조회 및 등록/삭제 
- `한창훈` : 회원가입, 회원 로그인 / 메인페이지 상품 조회 / 사용자 장바구니 등록, 수정, 삭제 / 사용자 구매 이력 조회 / 사용자 상품 검색 및 필터링

🔵 FRONTEND
- `선예림` : 사용자 장바구니 목록,장바구니 등록, 수정, 삭제 / 사용자 구매 이력 조회 / 회원탈퇴
- `조민지` : 판매자 상품 목록 조회 / 판매자 상품 등록 / 판매자 상품 수정 및 삭제
- `최혜선` : 회원가입 및 회원 정보 수정 / 사용자 메인 / 상품 목록 및 상세페이지 / 찜하기

</br>

## 개발환경 및 언어

<div>
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white">
<img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=for-the-badge&logo=Visual Studio Code&logoColor=white">
</br>
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
<img src="https://img.shields.io/badge/Tailwind CSS-06B6D4?style=for-the-badge&logo=Tailwind CSS&logoColor=white"> 
<br>
<img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> 
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white"> 
<img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black"> 
<br>  
<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"> 
<img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white">
<br> 
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white"> 
<img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=spring security&logoColor=white"> 
<br>
<img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
<br>  
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/gitlab-F05032?style=for-the-badge&logo=gitlab&logoColor=white">
<br>
</div>

</br>

## 개발 목표
- SPA 방식을 이용한 UI 개선(restAPI , API 문서화를 하기위해 swagger를 사용)
![](https://velog.velcdn.com/images/ahdtnpf96/post/4c1789d5-27ee-4ecb-bc52-022d4b76ae21/image.png)

- 보안 취약점을 개선한 인증 / 인가 시스템 구현
![image](https://user-images.githubusercontent.com/113097373/215973122-e3d197b7-359b-49e8-b476-73e843c2f728.png)

- 이미지 관리를 위한 AWS S3 클라우드를 사용해 이미지 업로드
![image](https://user-images.githubusercontent.com/113097373/215973433-70a321af-391d-4f46-96e2-fa8c781a103a.png)

- 오류 핸들링, 테스트코드 작성
![image](https://user-images.githubusercontent.com/113097373/215973536-cedc4a5e-ff71-45f8-89d8-6cee5a39fccf.png)

- Request DTO, Response DTO 분리하여 사용
</br>

![image](https://user-images.githubusercontent.com/113097373/215973577-e59bb850-7c86-41e1-a923-68ea87459de5.png)

- 유효성 검사
![image](https://user-images.githubusercontent.com/113097373/215973633-209f6624-12ae-455c-a222-f4463393f9d3.png)
![image](https://user-images.githubusercontent.com/113097373/215973662-73fc288d-2377-4ddf-91fe-bf861d967d75.png)

- HTTP 통신 라이브러리 AXIOS 사용
![image](https://user-images.githubusercontent.com/113097373/215974286-93c7ec0a-662d-4e0b-905a-a274a81744f2.png)

- 비동기 처리 방식 async & await 사용
![image](https://user-images.githubusercontent.com/113097373/215974346-35d2229e-6415-43b5-bd95-1a2d31667023.png)

</br>

## 데이터베이스

![](https://velog.velcdn.com/images/ahdtnpf96/post/46fb3c84-e276-4fb6-8d41-67560ae48319/image.png)

</br>

## 프로젝트 제공기능
![image](https://user-images.githubusercontent.com/113097373/215974461-bd0a945b-eb51-4e42-b67b-8864545fc1ef.png)
![image](https://user-images.githubusercontent.com/113097373/215974507-86306b04-bc02-4007-abb2-81a13c8a185d.png)


</br>

## 🖥프로젝트 화면
### 회원가입페이지
>이메일, 비밀번호 유효성 검사 및 비밀번호 일치 여부 확인 / 전화번호 '-' 자동입력
![image](https://user-images.githubusercontent.com/113097373/215975575-45f4d849-e991-4c56-8004-c613fa0da75d.png)

</br>

### 카테고리별 상품 불러오기
>카테고리 클릭 시 카테고리에 해당한 상품 전체 불러오기
![chrome_i1VKynaIHQ](https://user-images.githubusercontent.com/113097373/215975948-5354ebae-9f0e-485e-bad9-e0922436cc91.gif)

</br>

### 상품 전체보기 및 페이징
![image](https://user-images.githubusercontent.com/113097373/215976136-05266e44-293c-4ccb-ba01-3a64d6e105a1.png)

</br>

### 상품명 검색 및 필터링
![image](https://user-images.githubusercontent.com/113097373/215976290-bc4b1c55-0144-44c4-a206-04fe6e888961.png)

</br>

### 상품 상세페이지
![image](https://user-images.githubusercontent.com/113097373/215976506-d9ea1c41-93c3-44b2-b1c0-cb2e8379c8f7.png)

</br>

### 상품 찜하기
>빈하트 클릭 시 상품 찜하기 후 빨간 하트로 변경 / 빨간 하트 클릭 시 상품 찜하기 취소 후 빈 하트로 변경
![chrome_qhD6aDdsrU](https://user-images.githubusercontent.com/113097373/215976665-7f8d3ef2-510e-4709-a4ae-2a314058609e.gif)

</br>

### 내가 찜한 목록 확인
>찜한 목록에서 상품명 클릭 시 상품 상세페이지로 이동 / 개별 
![chrome_khXvviZnuo](https://user-images.githubusercontent.com/113097373/215976849-3258a961-976b-49a9-a4fe-0d290a1a660e.gif)

</br>

### 회원정보 수정
![chrome_UM2OWrKKDA](https://user-images.githubusercontent.com/113097373/215977144-12789e8d-171e-43bd-866e-81d1d7e0b333.gif)
