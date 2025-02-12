-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 12, 2025 at 04:15 PM
-- Server version: 5.7.24
-- PHP Version: 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `footmanager`
--

-- --------------------------------------------------------

--
-- Table structure for table `composition`
--

CREATE TABLE `composition` (
  `id` int(11) NOT NULL,
  `joueur_id` int(11) NOT NULL,
  `match_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `equipe`
--

CREATE TABLE `equipe` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `solde` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `equipe`
--

INSERT INTO `equipe` (`id`, `nom`, `solde`) VALUES
(1, 'Wydad.ac', 150),
(2, 'Wydad AC', 150);

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `id` int(11) NOT NULL,
  `oldEquipe_id` int(11) NOT NULL,
  `newEquipe_id` int(11) NOT NULL,
  `joueur_id` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `joueur`
--

CREATE TABLE `joueur` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `aVendre` tinyint(1) NOT NULL DEFAULT '0',
  `equipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `joueur`
--

INSERT INTO `joueur` (`id`, `nom`, `prenom`, `position`, `age`, `prix`, `aVendre`, `equipe_id`) VALUES
(1, 'TEST', '', 'Att', 25, 15, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `match`
--

CREATE TABLE `match` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `adversaire_id` int(11) NOT NULL,
  `equipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `resultat`
--

CREATE TABLE `resultat` (
  `id` int(11) NOT NULL,
  `score_equipe` int(11) NOT NULL,
  `score_adversaire` int(11) NOT NULL,
  `match_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `specialite` varchar(255) DEFAULT NULL,
  `equipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `nom`, `role`, `specialite`, `equipe_id`) VALUES
(1, 'TEST', 'Massage', 'Physic', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `composition`
--
ALTER TABLE `composition`
  ADD PRIMARY KEY (`id`),
  ADD KEY `joueur_id` (`joueur_id`),
  ADD KEY `match_id` (`match_id`);

--
-- Indexes for table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `joueur_id` (`joueur_id`),
  ADD KEY `newEquipe_id` (`newEquipe_id`),
  ADD KEY `oldEquipe_id` (`oldEquipe_id`);

--
-- Indexes for table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `equipe_id` (`equipe_id`);

--
-- Indexes for table `match`
--
ALTER TABLE `match`
  ADD PRIMARY KEY (`id`),
  ADD KEY `adversaire_id` (`adversaire_id`),
  ADD KEY `equipe_id` (`equipe_id`);

--
-- Indexes for table `resultat`
--
ALTER TABLE `resultat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `match_id` (`match_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`),
  ADD KEY `equipe_id` (`equipe_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `composition`
--
ALTER TABLE `composition`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `match`
--
ALTER TABLE `match`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `resultat`
--
ALTER TABLE `resultat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `composition`
--
ALTER TABLE `composition`
  ADD CONSTRAINT `composition_ibfk_1` FOREIGN KEY (`joueur_id`) REFERENCES `joueur` (`id`),
  ADD CONSTRAINT `composition_ibfk_2` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`);

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_ibfk_1` FOREIGN KEY (`joueur_id`) REFERENCES `joueur` (`id`),
  ADD CONSTRAINT `history_ibfk_2` FOREIGN KEY (`newEquipe_id`) REFERENCES `equipe` (`id`),
  ADD CONSTRAINT `history_ibfk_3` FOREIGN KEY (`oldEquipe_id`) REFERENCES `equipe` (`id`);

--
-- Constraints for table `joueur`
--
ALTER TABLE `joueur`
  ADD CONSTRAINT `joueur_ibfk_1` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`);

--
-- Constraints for table `match`
--
ALTER TABLE `match`
  ADD CONSTRAINT `match_ibfk_1` FOREIGN KEY (`adversaire_id`) REFERENCES `equipe` (`id`),
  ADD CONSTRAINT `match_ibfk_2` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`);

--
-- Constraints for table `resultat`
--
ALTER TABLE `resultat`
  ADD CONSTRAINT `resultat_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
