
INSERT INTO TB_USER (FIRST_NAME,LAST_NAME,EMAIL,PASSWORD)
VALUES
('Izac', 'Santos', 'izacmail@mail.com', 'secret1'),
('Marcos', 'Silva', 'marcosmail@mail.com', 'secret2'),
('Tereza', 'Pereira', 'terezamail@mail.com', 'secret3'),
('Jessica', 'Anjos', 'jessicamail@mail.com', 'secret4'),
('Fabiola', 'Jonson', 'fabiolamail@mail.com', 'secret5'),
('Mariana', 'Cabral', 'marianamail@mail.com', 'secret6');


INSERT INTO TB_GROUP (DESCRIPTION,NAME)
VALUES
('The First Group', 'Group First'),
('The Second Group', 'Group Second');

INSERT INTO USER_GROUP (GROUP_ID, USER_ID)
VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(2,3),
(2,4),
(2,5),
(2,6);
