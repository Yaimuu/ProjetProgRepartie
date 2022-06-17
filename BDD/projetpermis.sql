-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 21 juin 2021 à 13:38
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projetpermis`
--
drop database if  EXISTS `projetpermis1`;
CREATE DATABASE IF NOT EXISTS `projetpermis1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `projetpermis1`;
-- --------------------------------------------------------

--
-- Structure de la table `action`
--

DROP TABLE IF EXISTS `action`;
CREATE TABLE IF NOT EXISTS `action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_action` int(11) DEFAULT NULL,
  `wording` char(25) DEFAULT NULL,
  `scoreMinimum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Act_KEY_FK_ACTION_PREDECESSOR` (`fk_action`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `action`
--

INSERT INTO `action` (`id`, `fk_action`, `wording`, `scoreMinimum`) VALUES
(1, NULL, 'Se mettre en tenue', 4),
(2, 1, 'Préparation véhicule', 2),
(3, 2, 'Effectuer manoeuvre', 8),
(4, NULL, 'Analyser panne(s)', 2),
(5, 4, 'Résoudre panne(s)', 5);

-- --------------------------------------------------------

--
-- Structure de la table `action__mission`
--

DROP TABLE IF EXISTS `action__mission`;
CREATE TABLE IF NOT EXISTS `action__mission` (
  `fk_action` int(11) NOT NULL,
  `fk_mission` int(11) NOT NULL,
  PRIMARY KEY (`fk_action`,`fk_mission`),
  KEY `MisGoa_KEY_FK_MISSION` (`fk_mission`),
  KEY `ActGoa_KEY_FK_ACTION` (`fk_action`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `action__mission`
--

INSERT INTO `action__mission` (`fk_action`, `fk_mission`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(4, 2),
(5, 2);

-- --------------------------------------------------------

--
-- Structure de la table `indicator`
--

DROP TABLE IF EXISTS `indicator`;
CREATE TABLE IF NOT EXISTS `indicator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_action` int(11) NOT NULL,
  `wording` char(50) DEFAULT NULL,
  `valueIfCheck` int(11) DEFAULT NULL,
  `valueIfUnCheck` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Ind_KEY_FK_ACTION` (`fk_action`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `indicator`
--

INSERT INTO `indicator` (`id`, `fk_action`, `wording`, `valueIfCheck`, `valueIfUnCheck`) VALUES
(1, 1, 'Prendre ses outils', 1, -1),
(2, 1, 'Mettre sa tenue correctement', 3, -1),
(3, 1, 'Vérifier ses outils', 4, -2),
(4, 1, 'Prendre des explosifs', -1, 1),
(5, 2, 'Vérifier l\'état des pneus', 3, -2),
(6, 2, 'Vérifier le niveau d\'huile moteur', 1, 0),
(7, 2, 'Vérifier le niveau d\'essence', 3, -1),
(8, 3, 'Conduire à 110 km/h', -2, 2),
(9, 3, 'Conduire les yeux ouverts', 3, -6),
(10, 3, 'Avoir les deux mains sur le volant', 1, 0),
(11, 3, 'Réussir la manoeuvre', 6, -2),
(12, 3, 'Toucher le décor', -3, 3),
(13, 4, 'S\'informer auprès des techniciens', 2, 0),
(14, 4, 'Consulter le manuel', 1, 0),
(15, 4, 'Respecter la procédure', 2, -2),
(16, 4, 'Demander de l\'aide à la tour de contrôle', -1, 1),
(17, 5, 'Trouver la cause de la panne', 6, -3),
(18, 5, 'Utiliser les bons outils', 4, -1);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_user` int(11) NOT NULL,
  `fk_mission` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Ins_KEY_FK_USER` (`fk_user`),
  KEY `Ins_KEY_FK_GAME` (`fk_mission`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`id`, `fk_user`, `fk_mission`, `date`) VALUES
(11, 2, 1, '2021-06-04'),
(12, 2, 2, '2021-06-04'),
(14, 17, 1, '2021-06-07');

-- --------------------------------------------------------

--
-- Structure de la table `inscription__action`
--

DROP TABLE IF EXISTS `inscription__action`;
CREATE TABLE IF NOT EXISTS `inscription__action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_inscription` int(11) NOT NULL,
  `fk_action` int(11) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `LeaAct_KEY_FK_INSCRIPTION` (`fk_inscription`),
  KEY `LeaAct_KEY_FK_ACTION` (`fk_action`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `inscription__action`
--

INSERT INTO `inscription__action` (`id`, `fk_inscription`, `fk_action`, `sort`, `score`) VALUES
(1, 11, 1, NULL, 9),
(2, 11, 2, NULL, 7),
(3, 11, 3, NULL, 15),
(4, 11, 4, NULL, 2),
(5, 11, 5, NULL, 10),
(6, 12, 4, NULL, NULL),
(7, 12, 5, NULL, NULL),
(13, 14, 1, NULL, 7),
(14, 14, 2, NULL, NULL),
(15, 14, 3, NULL, NULL),
(16, 14, 4, NULL, 6),
(17, 14, 5, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `mission`
--

DROP TABLE IF EXISTS `mission`;
CREATE TABLE IF NOT EXISTS `mission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wording` char(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `mission`
--

INSERT INTO `mission` (`id`, `wording`) VALUES
(1, 'Mission A'),
(2, 'Mission B');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `NumUtil` int(11) NOT NULL AUTO_INCREMENT,
  `NomUtil` varchar(100) COLLATE utf8_bin NOT NULL,
  `MotPasse` varchar(100) COLLATE utf8_bin NOT NULL,
  `salt` varchar(100) COLLATE utf8_bin NOT NULL,
  `role` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `surname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `forename` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`NumUtil`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`NumUtil`, `NomUtil`, `MotPasse`, `salt`, `role`, `email`, `surname`, `forename`) VALUES
(1, 'Merlot', 'WFGexk0cocZHcs7qJuPawA==', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'admin', 'john.merlot@gmail.com', 'John', 'Merlot'),
(2, 'Lalande', 'WFGexk0cocZHcs7qJuPawA==', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'learner', 'pierre.lalande@gmail.com', 'Lalande', 'Pierre'),
(3, 'Pinot', 'WFGexk0cocZHcs7qJuPawA==', 'nC+LkxSBRR5r/uSLrtB7MB7laCpEWRs5uUpLm3N2JqA=', 'learner', 'thibault.pinot@gmail.com', 'Pinot', 'Thibault'),
(17, 'Henry', 'Q2q13t2kx1KjPxNt0zMQjA==', 'TN79XKh8WviYWIN1yYgEFDhC+5TjdFtTk4kufYUumb0=', 'learner', 'jean.henry@gmail.com', 'Henry', 'Jean');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `action`
--
ALTER TABLE `action`
  ADD CONSTRAINT `Act_FK_ACTION` FOREIGN KEY (`fk_action`) REFERENCES `action` (`id`);

--
-- Contraintes pour la table `action__mission`
--
ALTER TABLE `action__mission`
  ADD CONSTRAINT `ActGoa_FK_ACTION` FOREIGN KEY (`fk_action`) REFERENCES `action` (`id`),
  ADD CONSTRAINT `ActGoa_FK_MISSION` FOREIGN KEY (`fk_mission`) REFERENCES `mission` (`id`);

--
-- Contraintes pour la table `indicator`
--
ALTER TABLE `indicator`
  ADD CONSTRAINT `Ind_FK_ACTION` FOREIGN KEY (`fk_action`) REFERENCES `action` (`id`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `Ins_FK_MISSION` FOREIGN KEY (`fk_mission`) REFERENCES `mission` (`id`),
  ADD CONSTRAINT `Ins_FK_USER` FOREIGN KEY (`fk_user`) REFERENCES `utilisateur` (`NumUtil`);

--
-- Contraintes pour la table `inscription__action`
--
ALTER TABLE `inscription__action`
  ADD CONSTRAINT `LeaAct_FK_ACTION` FOREIGN KEY (`fk_action`) REFERENCES `action` (`id`),
  ADD CONSTRAINT `LeaAct_FK_INSCRIPTION` FOREIGN KEY (`fk_inscription`) REFERENCES `inscription` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
