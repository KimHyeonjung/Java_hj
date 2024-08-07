USE PRODUCT;

# 트랜젝션을 시작 : COMMIT할 때까지 최종 적용이 안됨.
START TRANSACTION;

UPDATE PRODUCT 
SET 
    PR_PRICE = 1000000
WHERE
	PR_CODE = 'AB1';

SAVEPOINT A1;

UPDATE PRODUCT 
SET 
    PR_PRICE = 2200000
WHERE
	PR_CODE = 'AB1';
    
SAVEPOINT A2;

UPDATE PRODUCT 
SET 
    PR_PRICE = 3200000
WHERE
	PR_CODE = 'AB1';

ROLLBACK;

SELECT * FROM PRODUCT;