# Spring MVC - JPA
> Spring MVC 와 JPA 를 활용한 간단 상품 조회 서비스 입니다
 
![doydoit](./img/DOY_BMO.png)
## 프로젝트 목표
* Spring MVC 와 JPA 활용 능력을 높입니다.
---
## 개발 환경 설정
* [스프링 이니셜라이저](https://start.spring.io/) 를 통해 필요 개발 환경을 구성합니다. 
    * Spring Web
    * Spring Data JPA
    * H2 Database
    * Lombok
    * Thymeleaf
    * Validation   

---
## 요구사항 정의
### 사용자는 서비스 홈 (Welcome Page)페이지에 접속을 합니다

#### 로그인 버튼으로 로그인 페이지로 이동합니다
* 기존 회원이면 아이디와 비밀번호를 입력하여 로그인
* 회원이 아니면 회원가입 버튼으로 회원가입 페이지로 이동
	* 로그인 아이디
	* 비밀 번호
	* 이름
	* 주소
	     
	  위 값들을 입력하여 회원가입 합니다
   

* 회원가입 후 로그인 페이지로 이동

* 로그인 후 이전 화면으로 이동합니다   
		ex) home - login - home

### 일반 회원 )
* 상품 목록 페이지
	* 상품 목록 페이지
	* 상품 사진 클릭 ) 상품 상세 페이지 이동
		* 상품 상세 페이지
			* 상품 주문
				* 수량 선택 후 주문 버튼 클릭 시 상품 목록 페이지 이동
* 개인 정보 페이지
	* 개인 상세 정보 페이지
		* 주소 정보 수정
	* 주문 목록 페이지
		* 주문한 상품 리스트
			* 상품 명 : 클릭 ) 상품 상세 페이지 이동
			* 수량
			* 가격
			* 주문 등록 일
			* 주문 상태 [ORDER , COMPLETE, CANCEL]

### 관리자 )
#### 관리자 페이지
* 주문 리스트 페이지 (기본 정렬 : 최신 주문 등록일 기준) ( 관리자 기본 페이지 )
	* 주문 회원
	* 상품 명
	* 총 금액
	* 수량
	* 주소
	* 주문 상태 ( CANCEL 버튼 클릭 시 상태 변경 ) [ ORDER , CANCEL ]
	* 주문 등록일
	

* 상품 관리 페이지
  * 리스트
  	* 사진
  	* 상품 명
  	* 수량
  * 등록, 수정  
  	* 사진
  	* 상품 명
	* 상품 가격
	* 상품 설명
	* 수량
---
## 엔티티 분석
### Member
	* id : Long 
	* login_id : String
	* login_pw : String
	* name : String
	* role : AccessRole [ ADMIN, USER ]
	* address : Address

### Order
	* id : Long
	* member : Member
	* orderItems : List<OrderItem>
	* delivery : Delivery
	* order_date : Date

### Delivery
	* id : Long
	* status : DeliveryStatus [ ORDER, CANCEL ]
	* order : Order
	* address : Address

### OrderItem
	* id : Long
	* order : Order
	* item : Item
	* count : int
	* order_price : int

### Item
	* id : Long
	* name : String
	* price : int
	* stock_quantity : int

### Picture
	* id : Long
	* item : Item
	* orgn_name : String
	* uuid_name : String
	* file_path : String
---
## 테이블 분석

### MEMBER
	* memer_id : bigint [pk] 
	* login_id : varchar [uk]
	* login_pw : varchar [nn]
	* name : varchar [nn]
	* role : varchar
	* city : varchar
	* street : varchar
	* zipcode : int  

### ORDERS
	* order_id : bigint [pk]
	* member_id : Long [fk]
	* delivery_id : Long [fk]
	* order_date : date
	* status : varchar

### DELIVERY
	* delivery_id : bigint [pk]
	* status : varchar
	* city : varchar
	* street : varchar
	* zipcode : varchar

### ORDER_ITEM
	* order_item_id : bigint [pk]
	* order_id : bigint [fk]
	* item_id : bigint [fk]
	* count : int
	* order_price : int

### ITEM
	* item_id : bigint [pk]
	* name : varchar
	* price : int
	* stock_quantity : int

### PICTURE
	* picture_id : bigint [pk]
	* item_id : bigint [fk]
	* origin_name : varchar
	* uuid : varchar
	* path : varchar

---
## 개발자 정보

이름 : 김도윤  
이메일 : doydoit@gmail.com  
깃 허브 : [https://github.com/planetdoy](https://github.com/planetdoy/)
