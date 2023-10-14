INSERT INTO tb_group (name, description) VALUES
  ('Casa Amarela', 'Compartilhamento de despesas na casa amarela'),
  ('Familia Silva', 'Despesas da familia Silva');

INSERT INTO tb_user (email, password, first_name, last_name) VALUES
  ('joao@example.com', 'senha123', 'Joao', 'Silva'),
  ('maria@example.com', 'senha456', 'Maria', 'Ferreira'),
  ('carlos@example.com', 'senha789', 'Carlos', 'Oliveira'),
  ('ana@example.com', 'senha101', 'Ana', 'Santos'),
  ('lucas@example.com', 'senha202', 'Lucas', 'Ribeiro'),
  ('rafaela@example.com', 'senha303', 'Rafaela', 'Gomes');

INSERT INTO tb_debt (description, amount, creditor_id, group_id, amount_per_user, status, date) VALUES
  ('Conta de luz', 150.00, 1, 1, 50.00, 'PENDING', '2023-10-20'),
  ('Aluguel', 2000.00, 1, 2, 1000.00, 'PENDING', '2023-10-15'),
  ('Supermercado', 300.00, 3, 1, 100.00, 'PENDING', '2023-10-18');

INSERT INTO debt_debtor (debt_id, debtor_id, status) VALUES
  (1, 2, 'PENDING'),
  (1, 3, 'PENDING'),
  (1, 5, 'PENDING'),
  (2, 4, 'PENDING'),
  (2, 5, 'PENDING'),
  (3, 2, 'PENDING'),
  (3, 6, 'PENDING');