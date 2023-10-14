-- Inserir grupos
INSERT INTO tb_group (name, description) VALUES
  ('Casa Amarela', 'Compartilhamento de despesas na casa amarela'),
  ('Familia Silva', 'Despesas da familia Silva');

-- Inserir usuários
INSERT INTO tb_user (email, password, first_name, last_name) VALUES
  ('joao@example.com', 'senha123', 'Joao', 'Silva'),
  ('maria@example.com', 'senha456', 'Maria', 'Ferreira'),
  ('carlos@example.com', 'senha789', 'Carlos', 'Oliveira'),
  ('ana@example.com', 'senha101', 'Ana', 'Santos'),
  ('lucas@example.com', 'senha202', 'Lucas', 'Ribeiro'),
  ('rafaela@example.com', 'senha303', 'Rafaela', 'Gomes');

-- Relacionar usuários aos grupos (user_group)
INSERT INTO user_group (user_id, group_id) VALUES
  (1, 1),  -- João na Casa Amarela
  (2, 1),  -- Maria na Casa Amarela
  (3, 1),  -- Carlos na Casa Amarela
  (3, 2),  -- Carlos na Casa Amarela
  (4, 2),  -- Ana na Família Silva
  (5, 2),  -- Lucas na Família Silva
  (6, 1);  -- Rafaela na Casa Amarela