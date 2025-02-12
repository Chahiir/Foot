-- Insertion de données pour la table equipe
INSERT INTO equipe (id, nom, solde) VALUES
(1, 'Paris Saint-Germain', 500),
(2, 'Real Madrid', 600),
(3, 'Manchester United', 450),
(4, 'FC Barcelone', 550),
(5, 'Bayern Munich', 480);

-- Insertion de données pour la table joueur
INSERT INTO joueur (id, nom, prenom, position, age, prix, aVendre, equipe_id) VALUES
(1, 'Mbappé', 'Kylian', 'Attaquant', 25, 180, 0, 1),
(2, 'Vinicius', 'Junior', 'Attaquant', 23, 150, 0, 2),
(3, 'Fernandes', 'Bruno', 'Milieu', 29, 120, 1, 3),  -- À vendre
(4, 'Pedri', '', 'Milieu', 21, 110, 0, 4),
(5, 'Kimmich', 'Joshua', 'Milieu', 28, 130, 0, 5),
(6, 'Vlahovic', 'Dusan', 'Attaquant', 24, 100, 1, 3), -- À vendre
(7, 'Salah', 'Mohamed', 'Attaquant', 32, 90, 0, 2),
(8, 'Sterling', 'Raheem', 'Ailier', 29, 85, 1, 1),  -- À vendre
(9, 'Haaland', 'Erling', 'Attaquant', 24, 200, 0, 4),
(10, 'Leao', 'Rafael', 'Ailier', 25, 95, 0, 5),
(11, 'De Bruyne', 'Kevin', 'Milieu', 33, 70, 1, 1), -- À vendre
(12, 'Kane', 'Harry', 'Attaquant', 31, 85, 0, 3),
(13, 'Rodri', '', 'Milieu', 27, 100, 0, 2),
(14, 'Modric', 'Luka', 'Milieu', 38, 30, 1, 5), -- À vendre
(15, 'Gavi', '', 'Milieu', 20, 80, 0, 4);

