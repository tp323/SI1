--3.
--a-
     --a. 
	SELECT equipa, monitor FROM ACTIVIDADE_MONITOR;

     --b. 
     (SELECT COLONO.nome FROM ((ACTIVIDADE JOIN ACTIVIDADE_MONITOR ON ACTIVIDADE.referencia = ACTIVIDADE_MONITOR.referencia) JOIN COLONO ON ACTIVIDADE_MONITOR.equipa = COLONO.equipa)
     WHERE ACTIVIDADE.designacao = 'ambientais')
     UNION
     (SELECT PESSOA.nome FROM ((ACTIVIDADE JOIN ACTIVIDADE_MONITOR ON ACTIVIDADE.referencia = ACTIVIDADE_MONITOR.referencia) JOIN PESSOA ON ACTIVIDADE_MONITOR.monitor = PESSOA.numero)
     WHERE ACTIVIDADE.designacao = 'ambientais');

    
     --c. 
	SELECT designacao FROM (ACTIVIDADE JOIN ACTIVIDADE_DESPORTIVA ON ACTIVIDADE.referencia = ACTIVIDADE_DESPORTIVA.referencia)
	WHERE participacao = 'obrigatório' AND participantes >= 5;

     --d. 
	SELECT equipa FROM ACTIVIDADE_EQUIPA
    SELECT equipa FROM (ACTIVIDADE JOIN ACTIVIDADE_EQUIPA ON ACTIVIDADE.referencia = ACTIVIDADE_EQUIPA.referencia)
           WHERE ACTIVIDADE.designacao = 'desportos radicais'; 

     --e. 
	SELECT MONITOR.numero, nome, idade FROM ((MONITOR JOIN PESSOA ON MONITOR.numero = PESSOA.numero) JOIN ACTIVIDADE_MONITOR ON MONITOR.numero = ACTIVIDADE_MONITOR.monitor) 
	WHERE ( ((MONITOR.numero - ACTIVIDADE_MONITOR.monitor) <> 0)); 

     --f. 
	SELECT descricao FROM ((ACTIVIDADE_EQUIPA JOIN EQUIPA ON ACTIVIDADE_EQUIPA.equipa = EQUIPA.numero) JOIN ACTIVIDADE ON ACTIVIDADE_EQUIPA.referencia = ACTIVIDADE.referencia)
    WHERE grupo = 'iniciados' ;
    
     --g. 
     --need fix
    SELECT Nomes.nomeRep, COUNT(COLONO.nome) AS nColonos
	FROM (( SELECT COLONO.nome AS nomeRep, REPRESENTANTE.equipa AS equipa
    		FROM ((REPRESENTANTE JOIN COLONO ON REPRESENTANTE.colono = COLONO.numero) AS Nomes) JOIN COLONO ON Nomes.equipa = COLONO.equipa)
        	WHERE Nomes.nomeRep <> COLONO.nome)
	WHERE nColonos > 4
	GROUP BY Nomes.nomeRep;

     --h. 
    SELECT AVG(Datediff(YEAR,dtnascimento,GETDATE())) AS AVGage
    FROM (COLONO JOIN EQUIPA ON COLONO.equipa = EQUIPA.numero) 
   	GROUP BY EQUIPA;

--b-
   SELECT nome FROM COLONO 
   ORDER BY COLONO.dtnascimento DESC;

--c-
    --need fix
   SELECT nome, endereco FROM PESSOA AS P
   WHERE numero IN (SELECT eeducacao FROM COLONO D WHERE (COLONO. = D.eeducacao);

--d- 
   SELECT designacao FROM ACTIVIDADE JOIN ACTIVIDADE_EQUIPA ON ACTIVIDADE.referencia = ACTIVIDADE_EQUIPA.referencia
   ORDER BY duracao DESC;

--e- 
   SELECT designacao FROM ACTIVIDADE JOIN ACTIVIDADE_EQUIPA ON ACTIVIDADE.referencia = ACTIVIDADE_EQUIPA.referencia
   WHERE (horainicial < '11:00:00' AND horafinal < '11:00:00') OR (horainicial > '12:00:00' AND horafinal > '12:00:00');
 
--f- 
   SELECT TOP 1 PESSOA.nome, COUNT(PESSOA.nome) AS 'ntimes' FROM MONITOR JOIN PESSOA ON MONITOR.numero = PESSOA.numero
   GROUP BY PESSOA.nome
   ORDER BY ntimes DESC;

--g- 
   CREATE VIEW InfoActividade
   AS SELECT ACTIVIDADE.designacao, ACTIVIDADE.descricao, ACTIVIDADE.duracao, ACTIVIDADE_EQUIPA.horainicial, EQUIPA.designacao, COUNT(*)
   FROM (((ACTIVIDADE JOIN ACTIVIDADE_EQUIPA ON ACTIVIDADE.referencia = ACTIVIDADE_EQUIPA.referencia) JOIN EQUIPA ON ACTIVIDADE_EQUIPA.equipa = EQUIPA.numero) JOIN COLONO ON EQUIPA.numero = COLONO.equipa)
   WHERE (DATEPART(HOUR, horainicial) < (DATEPART ( HOUR , GETDATE()))) AND (DATEPART(HOUR, horafinal) > (DATEPART ( HOUR , GETDATE())))
   GROUP BY (designacao, descricao, duracao, horainicial, designacao, numero);
