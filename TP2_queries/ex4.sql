
INSERT INTO COLONO (numero, nome, dtnascimento, contacto, escolaridade, ccidadao, cutente, eeducacao, equipa) VALUES (
    (SELECT MAX(numero)+1
     FROM COLONO),
     'Manuel Henrique',
     '2012-11-21',
     '+351943204674',
     '3',
     '36311510 ZZ4',
     '123456789',
     '10',
     '2');

