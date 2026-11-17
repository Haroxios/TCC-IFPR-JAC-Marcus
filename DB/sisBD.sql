-- DROP DATABASE TCCRevisaoWeb;
CREATE DATABASE TCCRevisaoWeb;
USE TCCRevisaoWeb;

-- SELECT  * FROM usuarios;

CREATE TABLE usuarios (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    usuario VARCHAR(123) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE tags (
    idTag INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    tag VARCHAR(20) NOT NULL
);

CREATE TABLE cadernos (
    idCaderno INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    caderno VARCHAR(100) NOT NULL,
    idUsuarioFK INT NOT NULL,
    CONSTRAINT FOREIGN KEY (idUsuarioFK) REFERENCES usuarios(idUsuario)
);

CREATE TABLE documentos (
    idDocumento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    titulo VARCHAR(255),		-- documento
    previa VARCHAR(255),
    conteudo TEXT,
    dataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    dataUltimaEdicao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- vai atualizar sozinho quando salvar
	dataProximaRevisao DATE NOT NULL,
    nivelRevisao INT NOT NULL,
    statusRevisao ENUM('ANDAMENTO', 'PENDENTE', 'CONCLUIDO', 'DESATIVADO') NOT NULL DEFAULT 'ANDAMENTO',
    idCadernoFK INT NOT NULL,
    CONSTRAINT FOREIGN KEY (idCadernoFK) REFERENCES cadernos(idCaderno)
);


CREATE TABLE tag_doc (
	idTagDoc INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    idDocumentoFK INT NOT NULL,
    CONSTRAINT FOREIGN KEY (idDocumentoFK) REFERENCES documentos(idDocumento),
    idTagFK INT NOT NULL,
    CONSTRAINT FOREIGN KEY (idTagFK) REFERENCES tags(idTag)
);


-- ------------------------------  DADOS  ------------------------------------------
INSERT INTO usuarios (usuario, email, senha) VALUES
('Joao Silva', 'joao@email.com', '$argon2id$v=19$m=16384,t=2,p=1$+kh7HxIriI0NuuMeGLCCwA$A9U9gszFAJDnyp6gUAlsIkEE7VQFllxAbmcQVrZOYbQ'),
('Maria Souza', 'maria@email.com', '$argon2id$v=19$m=16384,t=2,p=1$+kh7HxIriI0NuuMeGLCCwA$A9U9gszFAJDnyp6gUAlsIkEE7VQFllxAbmcQVrZOYbQ');

INSERT INTO cadernos (caderno, idUsuarioFK) VALUES
('Estudos de Programação', 1),
('Projetos Pessoais', 1),
('Faculdade - Banco de Dados', 2),
('Receitas e Gastronomia', 2),
('Cursos Online', 1),
('Carreira e Profissão', 1),
('Viagens e Turismo', 2),
('Inglês e Idiomas', 2);

INSERT INTO documentos (titulo, previa, conteudo, dataProximaRevisao, nivelRevisao, statusRevisao, idCadernoFK) VALUES
('Anotações de SQL', 'Comandos DDL e DML principais', 'Conteúdo detalhado sobre CREATE TABLE, INSERT, etc.', '2026-10-15', 1, 'ANDAMENTO', 1),
('Conceitos de Node.js', 'Event Loop e Express', 'Resumo sobre criação de APIs REST com Express.', '2026-10-20', 2, 'PENDENTE', 1),
('Modelagem de Dados', 'Relacionamentos 1:N e N:N', 'Análise de entidades e chaves estrangeiras.', '2026-10-25', 1, 'CONCLUIDO', 1),
('Planejamento de Viagem', 'Roteiro de Férias', 'Lista de locais para visitar e hospedagens.', '2026-11-01', 0, 'DESATIVADO', 2),
('Ideias de Aplicativos', 'Apps mobile para produtividade', 'Lista de recursos e telas para prototipar.', '2026-10-12', 3, 'ANDAMENTO', 2),
('Normalização de Dados', '1FN, 2FN e 3FN explicadas', 'Regras formais de normalização de tabelas.', '2026-10-18', 2, 'ANDAMENTO', 3),
('Transações e ACID', 'Commit, Rollback e Propriedades', 'Explicação sobre atomicidade, consistência, isolamento e durabilidade.', '2026-10-22', 1, 'PENDENTE', 3),
('Bolo de Cenoura', 'Receita tradicional com cobertura de chocolate', 'Ingredientes: 3 cenouras, 3 ovos, 1 xícara de óleo...', '2026-12-01', 0, 'CONCLUIDO', 4),
('Massa de Pizza Caseira', 'Fermentação lenta 24h', 'Farinha 00, água gelada, fermento biológico e sal.', '2026-11-10', 1, 'CONCLUIDO', 4),
('Molho Pesto Tradicional', 'Pesto alla Genovese', 'Manjericão fresco, pinoli, queijo parmesão e azeite.', '2026-11-05', 1, 'ANDAMENTO', 4),
('Git e GitHub', 'Branch, merge e pull request', 'Fluxo de trabalho com Git: commits, branches, merge, rebase e resolução de conflitos.', '2026-10-28', 2, 'PENDENTE', 1),
('Docker Básico', 'Containers e imagens', 'Conceitos de containers, Dockerfile, imagens, volumes e redes.', '2026-11-03', 1, 'ANDAMENTO', 1),
('App de Finanças Pessoais', 'Controle de gastos', 'Funcionalidades: lançamento de despesas, gráficos mensais e metas de economia.', '2026-11-15', 2, 'PENDENTE', 2),
('Site de Portfólio', 'Página pessoal', 'Seções: sobre, projetos, habilidades e contato.', '2026-11-20', 1, 'ANDAMENTO', 2),
('Curso de Java', 'POO em Java', 'Classes, herança, polimorfismo e interfaces.', '2026-10-30', 2, 'ANDAMENTO', 5),
('Curso de Python', 'Python do zero', 'Sintaxe, estruturas de dados, funções e módulos.', '2026-11-08', 1, 'PENDENTE', 5),
('Currículo Atualizado', 'Experiência e projetos', 'Versão 2026 do currículo com os projetos mais recentes.', '2026-12-10', 0, 'PENDENTE', 6),
('Preparação para Entrevistas', 'Perguntas técnicas', 'Perguntas comuns em entrevistas de desenvolvedor e como respondê-las.', '2026-11-25', 2, 'ANDAMENTO', 6),
('Índices e Performance', 'B-tree e consultas', 'Como índices aceleram SELECTs e quando evitá-los.', '2026-10-27', 2, 'ANDAMENTO', 3),
('Stored Procedures', 'Procedures e triggers', 'Criação de procedures, functions e triggers no MySQL.', '2026-11-02', 1, 'PENDENTE', 3),
('Lasanha à Bolonhesa', 'Clássica italiana', 'Camadas de massa, ragu de carne e molho bechamel.', '2026-12-05', 1, 'ANDAMENTO', 4),
('Pão de Queijo', 'Receita mineira', 'Polvilho, queijo minas e ovos.', '2026-12-08', 0, 'CONCLUIDO', 4),
('Roteiro de Praia', 'Litoral no verão', 'Praias, hospedagem e passeios de barco.', '2027-01-10', 0, 'PENDENTE', 7),
('Viagem para o Chile', 'Cordilheira e vinícolas', 'Santiago, Valle de Colchagua e estação de esqui.', '2027-02-15', 1, 'PENDENTE', 7),
('Phrasal Verbs', '100 phrasal verbs', 'Lista dos phrasal verbs mais usados com exemplos em frases.', '2026-11-12', 3, 'ANDAMENTO', 8),
('Vocabulário de Viagem', 'Inglês para viajar', 'Frases úteis em aeroporto, hotel e restaurante.', '2026-11-18', 2, 'PENDENTE', 8);

INSERT INTO tags (tag) VALUES
('Estudo'),
('Programação'),
('Receitas'),
('Importante'),
('Trabalho'),
('Faculdade'),
('Carreira'),
('Viagens'),
('Idiomas'),
('Pessoal');

INSERT INTO tag_doc (idDocumentoFK, idTagFK) VALUES
(1, 1),
(1, 2),
(3, 4),
(6, 1),
(8, 3),
(2, 1),
(2, 2),
(3, 1),
(4, 8),
(4, 10),
(5, 2),
(5, 10),
(6, 6),
(7, 1),
(7, 6),
(7, 4),
(8, 10),
(9, 3),
(10, 3),
(10, 10),
(11, 1),
(11, 2),
(12, 2),
(12, 4),
(13, 2),
(13, 10),
(14, 2),
(14, 7),
(15, 1),
(15, 2),
(16, 1),
(16, 2),
(17, 7),
(17, 5),
(18, 7),
(18, 4),
(18, 5),
(19, 1),
(19, 6),
(20, 2),
(20, 6),
(21, 3),
(22, 3),
(22, 10),
(23, 8),
(23, 10),
(24, 4),
(24, 8),
(25, 1),
(25, 9),
(26, 8),
(26, 9);

