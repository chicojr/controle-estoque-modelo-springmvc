CREATE TABLE produto (

	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codigo varchar(5),
	descricao varchar(100),
	preco_custo float,
	preco_venda float,
	garantia date,
	quantidade int,
	imagem varchar(100)
);

CREATE TABLE categoria_produto (

	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	codigo varchar(5),
	descricao varchar(100)
);

CREATE TABLE usuario (

	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome varchar(100),
    email varchar(50),
    login varchar(50),
    senha varchar(50)
);

CREATE TABLE estado (

	id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	nome varchar(50),
	sigla varchar(2),
	habilitado boolean
);

CREATE TABLE cidade (

	id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	estado_id int,
	nome  varchar(50),
	habilitado boolean
);

CREATE TABLE empresa (

	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cidade_id int,
	razao_social varchar(100),
	nome_fantasia varchar(100),
	cnpj varchar(18),
	inscricao_estadual varchar(50),
	website varchar(50),
	rua varchar(100),
	bairro varchar(50),
	complemento varchar(100),
	numero varchar(20),
	cep varchar(10),
	email varchar(50),
	telefones varchar(100)
);


---------------------------------------------------------------------------------------


ALTER TABLE cidade ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES estado(id);
ALTER TABLE empresa ADD CONSTRAINT fk_empresa_cidade FOREIGN KEY (cidade_id) REFERENCES cidade(id);


---------------------------------------------------------------------------------------


INSERT INTO estado (nome, sigla, habilitado) VALUES('Pernambuco','PE',true);
INSERT INTO estado (nome, sigla, habilitado) VALUES('Rio de Janeiro','RJ',true);
INSERT INTO estado (nome, sigla, habilitado) VALUES('Paraiba','PB',true);
INSERT INTO estado (nome, sigla, habilitado) VALUES('Sao Paulo','SP',true);
INSERT INTO estado (nome, sigla, habilitado) VALUES('Bahia','BH',true);
INSERT INTO estado (nome, sigla, habilitado) VALUES('Para','PA',true);
INSERT INTO estado (nome, sigla, habilitado) VALUES('Minas Gerais','MG',true);

INSERT INTO cidade (estado_id, nome, habilitado) VALUES(1,'Jaboatao',true);
INSERT INTO cidade (estado_id, nome, habilitado) VALUES(2,'Angra dos Reis',true);
INSERT INTO cidade (estado_id, nome, habilitado) VALUES(3,'Joao Pessoa',true);
INSERT INTO cidade (estado_id, nome, habilitado) VALUES(4,'Ribeirao Preto',true);
INSERT INTO cidade (estado_id, nome, habilitado) VALUES(5,'Salvador',true);
INSERT INTO cidade (estado_id, nome, habilitado) VALUES(6,'Belem',true);
INSERT INTO cidade (estado_id, nome, habilitado) VALUES(7,'Belo Horizonte',true);

