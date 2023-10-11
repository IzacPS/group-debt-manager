INSERT INTO TB_USER (FIRST_NAME,LAST_NAME,EMAIL,PASSWORD)
VALUES
('Lucas', 'Souza', 'lucasmail@mail.com', 'secret1'),
('Marcos', 'Silva', 'marcosmail@mail.com', 'secret2'),
('Tereza', 'Pereira', 'terezamail@mail.com', 'secret3'),
('Jessica', 'Anjos', 'jessicamail@mail.com', 'secret4'),
('Fabiola', 'Jonson', 'fabiolamail@mail.com', 'secret5'),
('Mariana', 'Cabral', 'marianamail@mail.com', 'secret6');


INSERT INTO TB_GROUP (DESCRIPTION,NAME)
VALUES
('The First Group', 'Group First'),
('The Second Group', 'Group Second');

INSERT INTO TB_DEBT (AMOUNT, AMOUNT_PER_USER, DATE, DEBTOR_ID, GROUP_ID, DESCRIPTION, STATUS)
VALUES
(00, 20, CURRENT_DATE, 1, 1, 'Pay your debts', 'PENDING'),
(00, 20, CURRENT_DATE, 2, 1, 'Pay your debts', 'PENDING'),
(00, 20, CURRENT_DATE, 3, 1, 'Pay your debts', 'PENDING'),
(00, 20, CURRENT_DATE, 4, 1, 'Pay your debts', 'PENDING'),
(00, 20, CURRENT_DATE, 5, 1, 'Pay your debts', 'PENDING'),

(0, 15, CURRENT_DATE, 6, 2, 'just another debts', 'PENDING'),
(0, 15, CURRENT_DATE, 4, 2, 'just another debts', 'PAID'),
(0, 15, CURRENT_DATE, 1, 2, 'just another debts', 'PENDING'),
(00, 15, CURRENT_DATE, 2, 2, 'just another debts', 'PENDING'),

(20, 10, CURRENT_DATE, 3, 1, 'debt, yes!', 'PAID'),
(20, 10, CURRENT_DATE, 4, 1, 'debt, yes!', 'PENDING');
