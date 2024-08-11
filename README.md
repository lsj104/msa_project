![header](https://capsule-render.vercel.app/api?type=waving&color=6495ED&fontAlign=50&fontAlignY=30&text=MSA%20PROJECT&descAlign=70&descAlignY=55&height=200&fontSize=40&fontColor=ffffff)
<h2> MSA architecture<h2>
<img width="407"src="https://github.com/user-attachments/assets/49129b4f-cbf2-41d8-8c45-b3c982c767c1"></img>
<h2>API</h2>
<h4>1. 상품 추가 POST /products </h4>
<img width="521" alt="스크린샷 2024-08-11 오후 9 48 52" src="https://github.com/user-attachments/assets/b211327d-dc52-40f6-9ced-bd6d6a32373d">
<h4>2. 상품 목록 조회 GET /products</h4>
<img width="521" alt="스크린샷 2024-08-11 오후 9 57 48" src="https://github.com/user-attachments/assets/d12f7ff9-a68d-4a31-9055-3e1405817665">
<h4>3. 주문 추가 POST /order</h4>
<img width="521" alt="스크린샷 2024-08-11 오후 9 59 06" src="https://github.com/user-attachments/assets/de848dc5-ad93-4c3b-9e5b-250a52ed8eb3">
<h4>4. 주문에 상품 추가 PUT /order/{orderId}</h4>
<img width="521" alt="스크린샷 2024-08-11 오후 10 00 53" src="https://github.com/user-attachments/assets/abbfa38e-8ffb-4019-85d9-94c2956cd677">
<h4>5. 주문 단건 조회 GET /order/{orderId}</h4>
<img width="521" alt="스크린샷 2024-08-11 오후 10 02 07" src="https://github.com/user-attachments/assets/aa057e93-36ec-46e0-b017-33f331b28666">
<h4>6. 로그인 GET /auth/signIn?user_id={string}</h4>
<img width="521" alt="스크린샷 2024-08-11 오후 10 03 10" src="https://github.com/user-attachments/assets/e7194b40-7bfc-4f84-8f67-9651336ada30">
<h4>7. 상품 단건 조회 GET /products/{productId}</h4>
<img width="521" alt="스크린샷 2024-08-11 오후 10 03 59" src="https://github.com/user-attachments/assets/f4277295-463a-449c-b926-c1c650464354">
<h2>분산 추적 Zipkin</h2>
<img width="1440" alt="스크린샷 2024-08-11 오후 10 06 32" src="https://github.com/user-attachments/assets/8afcccfd-cba8-4044-98aa-b76ac512b20c">
