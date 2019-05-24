연습문제1)
2019년 5월 20일 DVD를 대여한 사람들의 명단을 출력하는 SQL문을 작성하시오.

order_date(rent) name(member) pro_code(product) pho_no(mem) address(mem)

대여일 이름 상품코드 전화번호 주소
--------------------------

SELECT order_date,name,pro_code,pho_no,address
 FROM rent , member mem
  WHERE MEM.MEM_ID = RENT.MEM_ID
SELECT order_date,name,pro_code
      ,pho_no,address
 FROM rent , member mem
  WHERE MEM.MEM_ID = RENT.MEM_ID
 
SELECT *
 FROM rent natural join member natural join product