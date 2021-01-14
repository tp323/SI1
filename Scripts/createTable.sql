
CREATE TABLE ACTIVIDADE(
referencia INT NOT NULL,
designacao NVARCHAR(150) NOT NULL,
descricao NVARCHAR(150),
duracao INT NOT NULL,
participacao  NVARCHAR(15) NOT NULL,

CONSTRAINT chk_design CHECK (designacao IN ('desportiva', 'recreativa', 'aventura', 'desportos radicais', 'ambientais', 'artes', 'educaçao')),
CONSTRAINT chk_duracao CHECK (duracao BETWEEN 1 AND 240),
CONSTRAINT chk_particip CHECK (participacao IN ('opcional', 'obrigatório')),

PRIMARY KEY (referencia)
);

CREATE TABLE CAMPOFERIAS(
codigo NVARCHAR(25) NOT NULL,
nome VARCHAR(125) NOT NULL,
endereco VARCHAR(125) NOT NULL,
localidade VARCHAR(100) NOT NULL,
codpostal VARCHAR(50) NOT NULL,
enderecoweb VARCHAR(150) NOT NULL,
latitude DECIMAL(7,5) NOT NULL,
longitude DECIMAL(7,5) NOT NULL,

CONSTRAINT chk_codigocp CHECK (codigo LIKE ('CF[0-9][0-9][0-9][0-9]')),
CONSTRAINT chk_codpostal CHECK (codpostal LIKE ('[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9]')),

PRIMARY KEY (codigo),
UNIQUE (enderecoweb)
);

CREATE TABLE GRUPO (
nome NVARCHAR(25) NOT NULL,
idademinima INT NOT NULL CHECK (idademinima > 5),
idademaxima INT NOT NULL,

CONSTRAINT chk_nome CHECK (nome IN ('iniciados', 'juniores', 'seniores')),
CONSTRAINT chk_idademax CHECK (idademaxima > idademinima AND idademaxima < 18),

PRIMARY KEY (nome)
);

CREATE TABLE PESSOA (
numero INT NOT NULL,
nome NVARCHAR(150) NOT NULL,
endereco NVARCHAR(250) NOT NULL,
ntelefone NVARCHAR(50) NOT NULL,
email CHAR(150) NOT NULL,

CONSTRAINT chk_contactopessoa CHECK (ntelefone LIKE ('+351%')),
CONSTRAINT chk_email CHECK (email LIKE ('%@%')),

PRIMARY KEY (numero),
UNIQUE (ntelefone),
UNIQUE (email)
);



CREATE TABLE CONTACTO (
identificador NVARCHAR(25) NOT NULL,
contacto NVARCHAR(50) NOT NULL,
descricao CHAR(50) NOT NULL,

CONSTRAINT chk_contacto CHECK (contacto LIKE ('+351%') OR contacto LIKE ('%@%')),
CONSTRAINT chk_descricontacto CHECK (descricao IN ('fixo', 'móvel', 'email')),

--usamos contacto como PK em vez de identificador de forma a permitir que um campo de ferias tenha mais do que um contacto
PRIMARY KEY (contacto),
FOREIGN KEY (identificador) REFERENCES CAMPOFERIAS(codigo)
);

CREATE TABLE ACTIVIDADE_DESPORTIVA(
referencia INT NOT NULL,
participantes INT NOT NULL CHECK (participantes > 0),

PRIMARY KEY (referencia),
FOREIGN KEY (referencia) REFERENCES ACTIVIDADE(referencia)
);

CREATE TABLE MONITOR (
numero INT NOT NULL,
idade INT NOT NULL,
escolaridade NVARCHAR(20) NOT NULL,
comonitor INT,
cpferias NVARCHAR(25) NOT NULL,

CONSTRAINT chk_escolaridade CHECK (escolaridade IN ('secundário', 'superior')),

PRIMARY KEY (numero),
FOREIGN KEY (numero) REFERENCES PESSOA(numero),
FOREIGN KEY (comonitor) REFERENCES MONITOR(numero),
FOREIGN KEY (cpferias) REFERENCES CAMPOFERIAS(codigo)
);



CREATE TABLE EQUIPA (
numero INT NOT NULL,
grupo NVARCHAR(25) NOT NULL,
designacao NVARCHAR(150) NOT NULL,
monitor INT NOT NULL,

PRIMARY KEY (numero),
FOREIGN KEY (grupo) REFERENCES GRUPO(nome),
FOREIGN KEY (monitor) REFERENCES MONITOR(numero)
);



CREATE TABLE ACTIVIDADE_EQUIPA(
referencia INT NOT NULL,
equipa INT NOT NULL,
horainicial TIME NOT NULL,
horafinal TIME NOT NULL,

-- ao verificarmos se a horafinal é maior do que a horaincial e que o intervalo entre estas é inferior ou igual a 240m estamos a garantir 
-- que uma atividade terá uma duração superior a 0m menor ou igual que 240m, garantimos assim que a atividade se inicia e finaliza no mesmo dia,
-- pois se se iniciasse em dias diferentes a horafinal seria inferior à horainicial de forma a q a atividade demorasse no máximo 240 minutos
CONSTRAINT ck_duracao CHECK (horafinal > horainicial AND horafinal <= DATEADD(MINUTE,240,horainicial)),

PRIMARY KEY (referencia),
FOREIGN KEY(referencia) REFERENCES ACTIVIDADE(referencia),
FOREIGN KEY(equipa) REFERENCES EQUIPA(numero)
);

CREATE TABLE ACTIVIDADE_MONITOR(
referencia INT NOT NULL,
monitor INT NOT NULL,
equipa INT NOT NULL,

PRIMARY KEY (referencia),
FOREIGN KEY(referencia) REFERENCES ACTIVIDADE(referencia),
FOREIGN KEY(monitor) REFERENCES MONITOR(numero),
FOREIGN KEY(equipa) REFERENCES EQUIPA(numero)
);
	
CREATE TABLE COLONO(
numero INT NOT NULL,
nome NVARCHAR(150) NOT NULL,
dtnascimento DATE NOT NULL,
contacto NVARCHAR(50),
escolaridade INT NOT NULL,
ccidadao VARCHAR(15),
cutente DECIMAL(10,0),
eeducacao INT NOT NULL,equipa INT NOT NULL,

CONSTRAINT ck_dtnascimento CHECK (DATEDIFF(YY,dtnascimento,GETDATE())> 5 AND DATEDIFF(YY,dtnascimento,GETDATE())< 18),
CONSTRAINT ck_contactocolono CHECK (contacto LIKE '+351%'),
CONSTRAINT ck_escolaridade CHECK (escolaridade BETWEEN 1 AND 12),

PRIMARY KEY (numero),
UNIQUE (contacto),
UNIQUE (ccidadao),
UNIQUE (cutente),
FOREIGN KEY(eeducacao) REFERENCES PESSOA(numero),
FOREIGN KEY(equipa) REFERENCES EQUIPA(numero)
);


CREATE TABLE REPRESENTANTE (
colono INT NOT NULL,
equipa INT NOT NULL,

PRIMARY KEY (equipa),
UNIQUE (colono),
FOREIGN KEY(colono) REFERENCES COLONO(numero),
FOREIGN KEY(equipa) REFERENCES EQUIPA(numero)
);

