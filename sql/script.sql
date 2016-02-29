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