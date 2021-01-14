
BEGIN TRY
	BEGIN TRANSACTION
		INSERT INTO ACTIVIDADE VALUES
		('01','recreativa','cenas','30','opcional'),
		('02','desportiva','futebol','90','opcional'),
		('03','desportiva','futesal','90','obrigatório'),
		('04','desportiva','ping pong','30','opcional'),
		('05','educaçao','atelier','60','obrigatório'),
		('06','artes','pintura','60','obrigatório'),
		('07','desportos radicais','futesal','90','opcional'),
		('08','ambientais','Hike','90','obrigatório'),
		('09','aventura','escalada','30','opcional'),
		('10','desportos radicais','rapel','30','opcional')
		
		INSERT INTO CAMPOFERIAS VALUES
		('CF0001','campo Aventura de Portalegre','Av. Dr. Luís Bacharel Nº11','Portalegre','7300-130','www.campoaventuraportalegre.pt','39.308760','-7.438877'),
		('CF0002','campo Aventura de Évora','Rua de Santo André 13','Évora','7005-206','www.campoaventuraevora.pt','38.554535','-7.917240')
		
		INSERT INTO GRUPO VALUES
		('iniciados','6','10'),--6 colonos
		('juniores','11','14'),--8 colonos
		('seniores','15','17')--10 colonos
		
		INSERT INTO PESSOA VALUES
		('01','Luis Miguel','Rua Dom João V 16B 2ºDIR 1250-096 Lisboa','+351917020488','luis.miguel@hotmail.com'),
		('02','Gertrudes Santos','Rua das Trinas 16 1ºDIR 1200-656 Lisboa','+351931143792','gertrudes@gmail.com'),
		('03','Anabela de Malhadas','Rua Augusto José Vieira 27 1170-176 Lisboa','+351912838542','aianabela@hotmail.com'),
		('04','Zé Tochas','Rua da Palma 258A 1100-087 Lisboa','+351930514324','zetochas@gmail.com'),
		('05','Ezequiel Valadas','Rua Lot Brunheiras 148Lt, 7645-023 Vila Nova de Milfontes','+351934701878','ezequiel.presidentedajunta@netcabo.pt'),
		('06','David Bruno','Av. da República 2223, 4430-207 Vila Nova de Gaia','+351933551895','david.conjuntocorona@gmail.com'),
		('07','Francisco Ferro ','Cais do Gás, R. da Cintura do Porto de Lisboa, 1200-109 Lisboa','+351932013272','francisco.enapa@hotmail.com'),
		('08','Manuel João Vieira','Cais do Gás, R. da Cintura do Porto de Lisboa, 1200-109 Lisboa','+351916060411','manuelvieira.enapa@hotmail.com'),
		('09','Luís Desirat','Cais do Gás, R. da Cintura do Porto de Lisboa, 1200-109 Lisboa','+351938419667','luis.enapa@hotmail.com'),
		('10','Pedro Tijo','Cais do Gás, R. da Cintura do Porto de Lisboa, 1200-109 Lisboa','+351911810505','pedro.enapa@hotmail.com'),
		('11','Manuel Duarte','Cais do Gás, R. da Cintura do Porto de Lisboa, 1200-109 Lisboa','+351938518454','manuel.miguel@hotmail.com'),
		('12','João Santos','Cais do Gás, R. da Cintura do Porto de Lisboa, 1200-109 Lisboa','+351932431156','joao.enapa@hotmail.com'),
		('13','Américo Ribeiro','Rua José Relvas, 2775-271 Parede','+351917333034','apontamentos.sramerico@hotmail.com'),
		('14','Jel','Rua C 10 1700-110 Lisboa','+351913826067','jelhomensdaluta@gmail.com'),
		('15','Vasco Falancio','Rua C 10 1700-110 Lisboa','+351935828007','falanciohomensdaluta@gmail.com')
		
		INSERT INTO CONTACTO VALUES
		('CF0001','+351932445239','móvel'),
		('CF0002','+351214784693','fixo'),
		('CF0001','campoferiasportalegre@hotmail.pt','email'),
		('CF0002','campoferiasevora@hotmail.pt','email')
		
		INSERT INTO ACTIVIDADE_DESPORTIVA  VALUES
		('01','22'),
		('02','20')
		
		INSERT INTO MONITOR VALUES
		('01','19','secundário','01','CF0001'),
		('02','23','superior','01','CF0001'),
		('03','25','secundário','02','CF0001'),
		('04','20','superior','03','CF0001'),
		('05','25','secundário','04','CF0001'),
		('06','23','superior','05','CF0001'),
		('07','26','secundário','06','CF0001'),
		('08','24','superior','07','CF0001'),
		('09','21','secundário','08','CF0002'),
		('10','24','secundário','09','CF0002')
		
		INSERT INTO EQUIPA VALUES
		('01','iniciados','linces','01'),
		('02','juniores','lobos','02'),
		('03','seniores','leões','03')
		
		INSERT INTO ACTIVIDADE_EQUIPA VALUES
		('01','01','15:00:00.0000','19:00:00.0000'),
		('02','02','15:00:00.0000','16:30:00.0000'),
		('03','03','15:00:00.0000','16:30:00.0000'),
		('04','01','15:00:00.0000','15:30:00.0000'),
		('05','02','15:00:00.0000','16:00:00.0000'),
		('06','03','15:00:00.0000','16:00:00.0000')
		
		INSERT INTO ACTIVIDADE_MONITOR VALUES
		('01','04','01'),
		('02','05','02'),
		('03','06','03'),
		('04','07','01'),
		('05','08','02'),
		('06','09','03')
		
		INSERT INTO COLONO VALUES
		('01','João Oliveira','2003-02-03','+351915673467','11','33145713 ZZ4','112346789','11','3'),
		('02','Pedro Pereira','2005-07-11','+351912756452','9','53465132 ZZ7','676348234','12','2'),
		('03','Pedro Custódio','2010-10-25','+351936748243','4','63456345 ZZ1','142142489','13','1'),
		('04','António Rodrigues','2003-02-21','+351931256323','4','45673409 ZZ8','243523534','13','1'),
		('05','Guilherme Duarte','2003-01-23','+351936858658','11','43567534 ZZ4','523452345','10','3'),
		('06','Rui Sinel','2005-03-01','+351938976785','9','23453545 ZZ1','235345334','14','2'),
		('07','Bruno Nogueira','2010-02-05','+351917698455','4','54357543 ZZ1','253434532','14','1'),
		('08','João Quadros','2003-01-11','+351931233245','4','13445323 ZZ8','123412389','15','1')
		
		INSERT INTO REPRESENTANTE VALUES
		('01','01'),
		('02','02'),
		('03','03')
		
		COMMIT TRANSACTION
END TRY

BEGIN CATCH 
	SELECT 'Entrei no catch'  
	ROLLBACK
	
END CATCH
