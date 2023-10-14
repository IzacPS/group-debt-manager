INSERT INTO tb_group (name, description) VALUES
  ('Grupo A', 'Descrição do Grupo A'),
  ('Grupo B', 'Descrição do Grupo B'),
  ('Grupo C', 'Descrição do Grupo C'),
  ('Grupo D', 'Descrição do Grupo D'),
  ('Grupo E', 'Descrição do Grupo E');

INSERT INTO tb_user (first_name, last_name, email, password) VALUES
  ('Alice', 'Silva', 'alice@example.com', 'senhaAlice'),
  ('Bob', 'Santos', 'bob@example.com', 'senhaBob'),
  ('Carol', 'Oliveira', 'carol@example.com', 'senhaCarol'),
  ('David', 'Souza', 'david@example.com', 'senhaDavid'),
  ('Eve', 'Ferreira', 'eve@example.com', 'senhaEve'),
  ('Frank', 'Rocha', 'frank@example.com', 'senhaFrank'),
  ('Grace', 'Lima', 'grace@example.com', 'senhaGrace'),
  ('Hank', 'Costa', 'hank@example.com', 'senhaHank'),
  ('Ivy', 'Gonçalves', 'ivy@example.com', 'senhaIvy'),
  ('Jack', 'Pereira', 'jack@example.com', 'senhaJack');

INSERT INTO user_group (user_id, group_id) VALUES
  (1, 1), (2, 1), (3, 1), (4, 2), (5, 2),
  (6, 2), (7, 3), (8, 3), (9, 4), (10, 4),
  (1, 5), (2, 5), (3, 5), (4, 5), (5, 5);

INSERT INTO tb_debt (description, amount, date, amount_per_user, creditor_id, group_id, status) VALUES
  ('Dívida 1', 100.00, '2023-10-01', 20.00, 1, 1, 'PENDING'),
  ('Dívida 2', 150.00, '2023-10-02', 30.00, 2, 1, 'PENDING');

INSERT INTO debt_debtor (debt_id, debtor_id, status) VALUES
  (1, 2, 'PENDING'),
  (1, 4, 'PENDING'),
  (1, 6, 'PENDING'),
  (1, 8, 'PENDING'),
  (1, 10, 'PENDING'),
  (2, 1, 'PENDING'),
  (2, 3, 'PENDING'),
  (2, 5, 'PENDING'),
  (2, 7, 'PENDING'),
  (2, 9, 'PENDING');
