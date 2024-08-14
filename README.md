![header](https://capsule-render.vercel.app/api?type=waving&color=6495ED&fontAlign=50&fontAlignY=30&text=MSA%20PROJECT&descAlign=70&descAlignY=55&height=200&fontSize=40&fontColor=ffffff)
<h2> MSA architecture<h2>
<img width="407"src="https://github.com/user-attachments/assets/8fbd9736-a4fa-4b60-9869-8e5d238dea43"></img>
<br><br>
<img width="233" alt="스크린샷 2024-08-13 오전 1 14 43" src="https://github.com/user-attachments/assets/2016d847-6429-4e96-9655-cdf575648b33">
<h2>API</h2>
- Spring Cloud Gateway를 사용하여 게이트 웨이(19091)에서 모든 서비스를 호출하도록 구현
<br>
<br>
- Oauth2 JWT 기반으로 인가없이 상품 서비스, 주문서비스를 호출할 때 401 HTTP Status Code를 응답하도록 설정
<img width="514" alt="스크린샷 2024-08-14 오후 3 35 12" src="https://github.com/user-attachments/assets/72be3d96-ff00-46a7-bfb8-665592f247f6">
<h4>1. 상품 추가 POST /products </h4>
<img width="520" alt="스크린샷 2024-08-14 오후 3 09 37" src="https://github.com/user-attachments/assets/26d6870a-31d8-4e39-a82e-f3a747e1b0cc">
<h4>2. 상품 목록 조회 GET /products</h4>
- 라운드로빈 형식으로 로드밸런싱을 구현해 상품 서비스가 호출될 때 마다 19093, 19094 포트가 반복하며 호출되도록 구현
<img width="520" alt="스크린샷 2024-08-14 오후 3 10 58" src="https://github.com/user-attachments/assets/fbd42c57-5fe2-4463-9d37-87908e7b2e44">
<h4>3. 주문 추가 POST /order</h4>
<img width="520" alt="스크린샷 2024-08-14 오후 3 12 22" src="https://github.com/user-attachments/assets/b8c48f42-377d-41ef-bad7-7350440587ad">
<h4>4. 주문에 상품 추가 PUT /order/{orderId}</h4>
<img width="520" alt="스크린샷 2024-08-14 오후 3 13 31" src="https://github.com/user-attachments/assets/305e4da1-c94f-4757-8fb4-acb3ebc44eaa">
<h4>5. 주문 단건 조회 GET /order/{orderId}</h4>
<img width="520" alt="스크린샷 2024-08-14 오후 3 14 29" src="https://github.com/user-attachments/assets/5b811220-57b6-4ea8-9281-4f79516eb30e">
<h4>6. 로그인 GET /auth/signIn?user_id={string}</h4>
<img width="520" alt="스크린샷 2024-08-14 오후 3 07 48" src="https://github.com/user-attachments/assets/2fdd9d2e-f806-4aa0-b3ee-2368ddcb25f0">
<h4>7. 상품 단건 조회 GET /products/{productId}</h4>
<img width="520" alt="스크린샷 2024-08-14 오후 3 16 02" src="https://github.com/user-attachments/assets/45d2cc88-9d2e-4073-a86e-0f2a177da5a7">
<h2>분산 추적 Zipkin</h2>
<img width="1440" alt="스크린샷 2024-08-11 오후 10 06 32" src="https://github.com/user-attachments/assets/8afcccfd-cba8-4044-98aa-b76ac512b20c">
<h2>Cashing</h2>
<h4>주문 조회 API의 결과를 캐싱 처리하여 60초 동안 메모리에 캐싱된 데이터가 보여지도록 설정</h4>
<img width="304" src="https://github.com/user-attachments/assets/a505db3c-9b2c-4ea0-b574-e53101cc2696">
<h4>상품추가 API를 호출 할 경우 상품 목록 조회 API의 응답 데이터 캐시가 갱신되도록 구현</h4>
<img width="304" alt="스크린샷 2024-08-13 오전 1 06 07" src="https://github.com/user-attachments/assets/a460f781-3987-4d8e-9923-dc72d0b5cf4e">
