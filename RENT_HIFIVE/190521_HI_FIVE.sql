��������1)
2019�� 5�� 20�� DVD�� �뿩�� ������� ����� ����ϴ� SQL���� �ۼ��Ͻÿ�.

order_date(rent) name(member) pro_code(product) pho_no(mem) address(mem)

�뿩�� �̸� ��ǰ�ڵ� ��ȭ��ȣ �ּ�
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