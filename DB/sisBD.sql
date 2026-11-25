-- DROP DATABASE TCCRevisaoWeb;
CREATE DATABASE TCCRevisaoWeb;
USE TCCRevisaoWeb;

-- SELECT * FROM documentos;

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
('Marcus', 'marcus@email.com', '$argon2id$v=19$m=16384,t=2,p=1$+kh7HxIriI0NuuMeGLCCwA$A9U9gszFAJDnyp6gUAlsIkEE7VQFllxAbmcQVrZOYbQ'),
('Maria Souza', 'maria@email.com', '$argon2id$v=19$m=16384,t=2,p=1$+kh7HxIriI0NuuMeGLCCwA$A9U9gszFAJDnyp6gUAlsIkEE7VQFllxAbmcQVrZOYbQ');

INSERT INTO cadernos (caderno, idUsuarioFK) VALUES
('Geral', 1),
('Geral', 2);

INSERT INTO documentos (titulo, previa, conteudo, dataProximaRevisao, nivelRevisao, statusRevisao, idCadernoFK) VALUES
-- Caderno 1 (novos registros)
('1 - Andamento no prazo (nível 1)', 'Revisar leva ao nível 2 (7 dias)', 'Documento em ANDAMENTO, nível 1, ainda no prazo.', '2026-12-01', 1, 'ANDAMENTO', 1),
('2 - Andamento no prazo (nível 2)', 'Revisar leva ao nível 3 (30 dias)', 'Documento em ANDAMENTO, nível 2, ainda no prazo.', '2026-12-03', 2, 'ANDAMENTO', 1),
('3 - Andamento no prazo (nível 3)', 'Já está no nível máximo', 'Documento em ANDAMENTO, nível 3. Ao revisar, continua no nível 3 com 30 dias.', '2026-12-10', 3, 'ANDAMENTO', 1),
('4 - Vence hoje (apresentação)', 'Vira PENDENTE ao abrir o menu', 'Data da próxima revisão igual ao dia da apresentação.', '2026-11-26', 1, 'ANDAMENTO', 1),
('5 - Em atraso', 'Vira PENDENTE ao abrir o menu', 'Data da próxima revisão já passou, mas o status ainda está ANDAMENTO.', '2026-11-20', 2, 'ANDAMENTO', 1),
('6 - Pendente', 'Revisar ou adiar volta para ANDAMENTO', 'Documento já gravado como PENDENTE.', '2026-11-18', 2, 'PENDENTE', 1),
('7 - Concluído', 'Reativar volta ao nível 1', 'Documento CONCLUIDO, pode ser reativado.', '2026-12-15', 2, 'CONCLUIDO', 1),
('8 - Desativado', 'Reativar volta ao nível 1', 'Documento DESATIVADO, pode ser reativado.', '2026-12-15', 2, 'DESATIVADO', 1),


-- Caderno 2 (originais)
('Planejamento de Viagem', 'Roteiro de Férias', 'Lista de locais para visitar e hospedagens.', '2026-11-01', 1, 'DESATIVADO', 2),
('Ideias de Aplicativos', 'Apps mobile para produtividade', 'Lista de recursos e telas para prototipar.', '2026-10-12', 3, 'ANDAMENTO', 2),
('App de Finanças Pessoais', 'Controle de gastos', 'Funcionalidades: lançamento de despesas, gráficos mensais e metas de economia.', '2026-11-15', 2, 'PENDENTE', 2),
('Site de Portfólio', 'Página pessoal', 'Seções: sobre, projetos, habilidades e contato.', '2026-11-20', 1, 'ANDAMENTO', 2);

INSERT INTO tags (tag) VALUES
('1'),
('2');

INSERT INTO tag_doc (idDocumentoFK, idTagFK) VALUES
(1, 1),
(1,2);

