-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : lun. 04 mai 2026 à 11:54
-- Version du serveur : 11.3.2-MariaDB
-- Version de PHP : 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `equida`
--

-- --------------------------------------------------------

--
-- Structure de la table `attacher`
--

DROP TABLE IF EXISTS `attacher`;
CREATE TABLE IF NOT EXISTS `attacher` (
  `idCourriel` int(11) NOT NULL,
  `idPieceJointe` int(11) NOT NULL,
  PRIMARY KEY (`idCourriel`,`idPieceJointe`),
  KEY `fk_attacher_piece` (`idPieceJointe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `avoir`
--

DROP TABLE IF EXISTS `avoir`;
CREATE TABLE IF NOT EXISTS `avoir` (
  `id` int(11) NOT NULL,
  `id_1` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_1`),
  KEY `id_1` (`id_1`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `categvente`
--

DROP TABLE IF EXISTS `categvente`;
CREATE TABLE IF NOT EXISTS `categvente` (
  `id` int(11) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `categvente`
--

INSERT INTO `categvente` (`id`, `libelle`) VALUES
(1, 'Vente de Pur-sang'),
(2, 'Vente de Quarter Horse'),
(3, 'Vente de Frison');

-- --------------------------------------------------------

--
-- Structure de la table `cheval`
--

DROP TABLE IF EXISTS `cheval`;
CREATE TABLE IF NOT EXISTS `cheval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) NOT NULL,
  `date_naissance` date NOT NULL,
  `race_id` int(11) DEFAULT NULL,
  `codeSire` varchar(50) NOT NULL,
  `taille` decimal(5,2) DEFAULT NULL,
  `poids` decimal(6,2) DEFAULT NULL,
  `typeRobe` varchar(50) DEFAULT NULL,
  `cheval_pere` int(11) DEFAULT NULL,
  `cheval_mere` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codeSire` (`codeSire`),
  KEY `fk_race` (`race_id`),
  KEY `fk_typeRobe` (`typeRobe`),
  KEY `fk_cheval_pere` (`cheval_pere`),
  KEY `fk_cheval_mere` (`cheval_mere`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `cheval`
--

INSERT INTO `cheval` (`id`, `nom`, `date_naissance`, `race_id`, `codeSire`, `taille`, `poids`, `typeRobe`, `cheval_pere`, `cheval_mere`) VALUES
(1, 'Eclipse', '2017-03-12', 4, 'SIRE001', 1.68, 510.00, 'Bai', NULL, NULL),
(2, 'Aztec', '2019-07-04', 4, 'SIRE002', 1.72, 550.00, 'Noir', 1, 13),
(3, 'orion', '2015-05-23', 5, 'SIRE003', 1.65, 505.00, 'Gris', NULL, NULL),
(4, 'Tempête de Feu', '2017-03-12', 1, 'SIRE004', 1.60, 480.00, 'Alezan', 1, 3),
(5, 'Éclair Noir', '2019-07-04', 2, 'SIRE005', 1.75, 600.00, 'Noir', 2, 6),
(6, 'Vent du Nord', '2015-05-23', 3, 'SIRE006', 1.55, 450.00, 'Pie', NULL, NULL),
(7, 'Comète', '2018-01-01', 4, 'SIRE007', 1.69, 520.00, 'Gris', 2, 6),
(8, 'Silver Snow', '2020-11-17', 5, 'SIRE008', 1.70, 530.00, 'Bai', 2, 6),
(9, 'Caramel', '2016-06-30', 6, 'SIRE009', 1.63, 495.00, 'Alezan', NULL, NULL),
(10, 'Storm', '2021-10-10', 1, 'SIRE010', 1.66, 515.00, 'Noir', 1, 3),
(11, 'Mustang', '2014-08-03', 2, 'SIRE011', 1.71, 540.00, 'Bai', NULL, NULL),
(12, 'Rising Sun', '2019-04-22', 3, 'SIRE012', 1.67, 500.00, 'Gris', NULL, NULL),
(13, 'Phantom', '2016-12-05', 4, 'SIRE013', 1.64, 485.00, 'Pie', NULL, NULL),
(14, 'Pompom', '2025-07-13', 2, 'SIRE014', 1.58, 460.00, 'Alezan', 11, 15),
(15, 'Fleur du désert', '2023-06-30', 6, 'SIRE015', 1.62, 490.00, 'Bai', NULL, NULL),
(17, 'haland', '2015-11-27', 4, 'SIRE1777294049330', 1.73, 560.00, 'Noir', 11, 15),
(18, 'gustav', '2020-11-27', 3, 'SIRE1777294097137', 1.69, 525.00, 'Gris', 11, 15),
(21, 'Galopin', '2021-02-15', 1, 'SIRE021', 1.62, 490.00, 'Bai', 1, 15),
(22, 'Zénith', '2020-05-20', 2, 'SIRE022', 1.70, 540.00, 'Noir', 11, 15),
(23, 'Héroïne', '2022-04-10', 3, 'SIRE023', 1.67, 510.00, 'Gris', 2, 6),
(24, 'Indiana', '2019-08-12', 4, 'SIRE024', 1.65, 500.00, 'Alezan', 17, 13),
(25, 'Jupiter', '2021-03-30', 5, 'SIRE025', 1.72, 560.00, 'Bai', 1, 3),
(26, 'Kalypsos', '2023-01-05', 6, 'SIRE026', 1.59, 470.00, 'Pie', 11, 15),
(27, 'Légende', '2018-09-25', 1, 'SIRE027', 1.68, 525.00, 'Bai', NULL, NULL),
(28, 'Mistral', '2020-06-14', 2, 'SIRE028', 1.74, 580.00, 'Noir', 5, 15),
(29, 'Nuage', '2022-07-22', 4, 'SIRE029', 1.66, 505.00, 'Gris', 17, 13),
(30, 'Olympe', '2021-11-30', 5, 'SIRE030', 1.71, 535.00, 'Isabelle', 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL,
  `titre_` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `adrPartie1` varchar(50) DEFAULT NULL,
  `adrPartie2` varchar(50) DEFAULT NULL,
  `Cpos` varchar(50) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `pays` varchar(50) DEFAULT NULL,
  `adresseMessagerie` varchar(50) DEFAULT NULL,
  `code` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `titre_`, `nom`, `prenom`, `adrPartie1`, `adrPartie2`, `Cpos`, `ville`, `pays`, `adresseMessagerie`, `code`) VALUES
(1, 'M.', 'Dupont', 'Jean', '12 rue des Lilas', '', '75012', 'Paris', 'France', 'jean.dupont@mail.com', 'FR'),
(2, 'Mme', 'Martin', 'Sophie', '45 avenue des Champs', '', '75008', 'Paris', 'France', 'sophie.martin@mail.com', 'FR'),
(3, 'M.', 'Schmidt', 'Karl', 'Bahnhofstr. 5', '', '10115', 'Berlin', 'Allemagne', 'karl.schmidt@mail.de', 'DE'),
(4, 'Mme', 'Lefebvre', 'Marie', '10 rue de la Paix', '', '14000', 'Caen', 'France', 'marie.lef@mail.com', 'FR'),
(5, 'M.', 'O\'Brien', 'Connor', '22 Dublin St', '', 'D01', 'Dublin', 'Irlande', 'connor.ob@mail.ie', 'IE'),
(6, 'M.', 'Dubois', 'Pierre', '5 rue Verte', '', '69002', 'Lyon', 'France', 'p.dubois@mail.com', 'FR'),
(7, 'Mme', 'García', 'Elena', 'Calle Mayor 12', '', '28001', 'Madrid', 'Espagne', 'elena.g@mail.es', 'ES'),
(8, 'M.', 'Smith', 'John', '15 Baker Street', '', 'NW1', 'Londres', 'Royaume-Uni', 'j.smith@mail.uk', 'GB'),
(9, 'M.', 'Van Damme', 'Jean-Claude', 'Rue du Musée', '', '1000', 'Bruxelles', 'Belgique', 'jcvd@mail.be', 'BE'),
(10, 'Mme', 'Ricci', 'Paola', 'Via Roma 45', '', '00100', 'Rome', 'Italie', 'paola.r@mail.it', 'IT'),
(11, 'M.', 'Moreau', 'Luc', '18 Quai de Seine', '', '75004', 'Paris', 'France', 'luc.moreau@mail.com', 'FR'),
(12, 'Mme', 'Jansen', 'Anika', 'Damrak 1', '', '1012', 'Amsterdam', 'Pays-Bas', 'anika@mail.nl', 'NL'),
(13, 'M.', 'Bauer', 'Hans', 'Kaiserstr. 10', '', '80331', 'Munich', 'Allemagne', 'hans.b@mail.de', 'DE'),
(14, 'Mme', 'Petit', 'Chloé', '2 Bis Rue des Arts', '', '31000', 'Toulouse', 'France', 'chloe.p@mail.fr', 'FR'),
(15, 'M.', 'Leroy', 'Thomas', '40 Avenue Foch', '', '06000', 'Nice', 'France', 't.leroy@mail.com', 'FR'),
(16, 'Mme', 'Miller', 'Sarah', '5th Avenue', '', '10001', 'New York', 'États-Unis', 'sarah.m@mail.us', 'US'),
(17, 'M.', 'Fontaine', 'Guillaume', 'Route de la Plage', '', '33000', 'Bordeaux', 'France', 'g.font@mail.fr', 'FR'),
(18, 'M.', 'Rossi', 'Marco', 'Piazza Duomo', '', '20121', 'Milan', 'Italie', 'm.rossi@mail.it', 'IT'),
(19, 'Mme', 'Dumont', 'Alice', '8 Impasse des Fleurs', '', '44000', 'Nantes', 'France', 'alice.d@mail.com', 'FR'),
(20, 'M.', 'Taylor', 'Robert', 'High Street 5', '', 'EH1', 'Edimbourg', 'Royaume-Uni', 'rob.t@mail.uk', 'GB'),
(21, 'Mme', 'Fernandez', 'Isabel', 'Avenida de la Paz', '', '41001', 'Séville', 'Espagne', 'isabel.f@mail.es', 'ES');

-- --------------------------------------------------------

--
-- Structure de la table `courriel`
--

DROP TABLE IF EXISTS `courriel`;
CREATE TABLE IF NOT EXISTS `courriel` (
  `id` int(11) NOT NULL,
  `date_` date DEFAULT NULL,
  `objet` varchar(50) DEFAULT NULL,
  `corps` varchar(50) DEFAULT NULL,
  `idVente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_courriel_vente` (`idVente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `lieu` varchar(50) DEFAULT NULL,
  `date_` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `course`
--

INSERT INTO `course` (`id`, `nom`, `lieu`, `date_`) VALUES
(1, 'Grand Prix Vincennes', 'Vincennes', '2025-09-18'),
(2, 'Trophée Deauville', 'Deauville', '2025-07-12'),
(3, 'Prix d\'Amérique', 'Vincennes', '2025-01-26'),
(4, 'Prix de Diane', 'Chantilly', '2025-06-15'),
(5, 'Arc de Triomphe', 'Longchamp', '2025-10-05'),
(6, 'Grand National du Trot', 'Caen', '2025-03-12'),
(7, 'Derby d\'Ete', 'Deauville', '2025-07-20'),
(8, 'Prix de la Marne', 'Vincennes', '2025-02-02'),
(9, 'Challenge des Dunes', 'Deauville', '2025-08-15'),
(10, 'Grand Prix d\'Automne', 'Caen', '2025-11-20'),
(11, 'Steeple-Chase d\'Hiver', 'Pau', '2025-12-10'),
(12, 'Critérium des Jeunes', 'Vincennes', '2026-02-15'),
(13, 'Prix du Jockey Club', 'Chantilly', '2025-06-01'),
(14, 'Nocturne de Printemps', 'Vincennes', '2026-04-10'),
(15, 'Trophée des Plages', 'Deauville', '2025-08-25'),
(16, 'Prix de Normandie', 'Caen', '2025-09-14'),
(17, 'Esterel Cup', 'Cannes', '2025-05-18'),
(18, 'Gold Cup Trial', 'Longchamp', '2025-04-22'),
(19, 'Prix d\'Excellence', 'Vincennes', '2025-12-25'),
(20, 'Summer Sprint', 'Deauville', '2025-07-05'),
(21, 'Prix des Éleveurs', 'Caen', '2026-03-05'),
(22, 'Mémorial de Mai', 'Vincennes', '2026-05-01');

-- --------------------------------------------------------

--
-- Structure de la table `enchere`
--

DROP TABLE IF EXISTS `enchere`;
CREATE TABLE IF NOT EXISTS `enchere` (
  `numero` int(11) NOT NULL,
  `montant` varchar(50) DEFAULT NULL,
  `idClient` int(11) NOT NULL,
  `idLot` int(11) NOT NULL,
  PRIMARY KEY (`numero`),
  KEY `fk_enchere_client` (`idClient`),
  KEY `fk_enchere_lot` (`idLot`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `enchere`
--

INSERT INTO `enchere` (`numero`, `montant`, `idClient`, `idLot`) VALUES
(1, '26000', 1, 1),
(2, '27000', 4, 1),
(3, '28500', 1, 1),
(4, '19000', 2, 2),
(5, '20000', 6, 2),
(6, '36000', 3, 3),
(7, '38000', 5, 3),
(8, '13000', 7, 4),
(9, '46000', 8, 5),
(10, '48000', 10, 5),
(11, '23000', 9, 6),
(12, '16000', 11, 7),
(13, '29000', 12, 8),
(14, '31000', 13, 9),
(15, '26000', 14, 10),
(16, '16000', 15, 11),
(17, '20000', 16, 12),
(18, '52000', 17, 13),
(19, '45000', 18, 14),
(20, '11000', 19, 15),
(21, '34000', 20, 16),
(22, '22000', 21, 17),
(23, '30000', 4, 18);

-- --------------------------------------------------------

--
-- Structure de la table `interesser`
--

DROP TABLE IF EXISTS `interesser`;
CREATE TABLE IF NOT EXISTS `interesser` (
  `idClient` int(11) NOT NULL,
  `idCategVente` int(11) NOT NULL,
  PRIMARY KEY (`idClient`,`idCategVente`),
  KEY `fk_interesser_categ` (`idCategVente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `interesser`
--

INSERT INTO `interesser` (`idClient`, `idCategVente`) VALUES
(1, 1),
(2, 1),
(4, 1),
(5, 1),
(7, 1),
(8, 1),
(10, 1),
(11, 1),
(13, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(20, 1),
(1, 2),
(6, 2),
(9, 2),
(14, 2),
(21, 2),
(3, 3),
(12, 3),
(19, 3);

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `id` int(11) NOT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `nbBoxes` varchar(50) DEFAULT NULL,
  `commentaires` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`id`, `ville`, `nbBoxes`, `commentaires`) VALUES
(1, 'Vincennes', '50', 'Hippodrome de Vincennes'),
(2, 'Deauville', '30', 'Hippodrome de Deauville'),
(3, 'Caen', '40', 'Hippodrome de la Prairie'),
(4, 'Longchamp', '60', 'Temple du Galop'),
(5, 'Chantilly', '45', 'Spécialiste du saut d\'obstacles');

-- --------------------------------------------------------

--
-- Structure de la table `lot`
--

DROP TABLE IF EXISTS `lot`;
CREATE TABLE IF NOT EXISTS `lot` (
  `id` int(11) NOT NULL,
  `prixDepart` varchar(50) DEFAULT NULL,
  `idVente` int(11) NOT NULL,
  `idCheval` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lot_vente` (`idVente`),
  KEY `fk_lot_cheval` (`idCheval`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `lot`
--

INSERT INTO `lot` (`id`, `prixDepart`, `idVente`, `idCheval`) VALUES
(1, '25000', 1, 1),
(2, '18000', 1, 4),
(3, '35000', 2, 2),
(4, '12000', 2, 5),
(5, '45000', 3, 7),
(6, '22000', 3, 8),
(7, '15000', 4, 10),
(8, '28000', 4, 11),
(9, '30000', 5, 21),
(10, '25000', 5, 22),
(11, '15000', 6, 23),
(12, '19000', 6, 24),
(13, '50000', 7, 25),
(14, '42000', 7, 27),
(15, '10000', 8, 26),
(16, '32000', 9, 28),
(17, '21000', 10, 29),
(18, '27000', 10, 30),
(19, '35000', 1, 17),
(20, '31000', 2, 18),
(21, '12000', 3, 9),
(22, '18000', 4, 12);

-- --------------------------------------------------------

--
-- Structure de la table `participer`
--

DROP TABLE IF EXISTS `participer`;
CREATE TABLE IF NOT EXISTS `participer` (
  `idCheval` int(11) NOT NULL,
  `idCourse` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCheval`,`idCourse`),
  KEY `fk_participer_course` (`idCourse`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `participer`
--

INSERT INTO `participer` (`idCheval`, `idCourse`, `position`) VALUES
(1, 1, 1),
(1, 3, 2),
(1, 8, 4),
(2, 2, 3),
(2, 7, 1),
(2, 15, 2),
(3, 1, 5),
(3, 6, 3),
(3, 16, 1),
(4, 4, 10),
(4, 13, 2),
(4, 18, 5),
(5, 5, 1),
(5, 18, 3),
(6, 6, 8),
(6, 10, 6),
(7, 7, 2),
(7, 9, 1),
(7, 20, 3),
(8, 3, 7),
(8, 12, 2),
(17, 14, 1),
(17, 22, 1),
(18, 14, 4),
(18, 22, 5),
(21, 12, 5),
(21, 21, 2),
(22, 21, 1),
(24, 17, 3),
(24, 20, 6);

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

DROP TABLE IF EXISTS `pays`;
CREATE TABLE IF NOT EXISTS `pays` (
  `code` varchar(50) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`code`, `nom`) VALUES
('BE', 'Belgique'),
('DE', 'Allemagne'),
('ES', 'Espagne'),
('FR', 'France'),
('GB', 'Royaume-Uni'),
('IE', 'Irlande'),
('IT', 'Italie'),
('NL', 'Pays-Bas'),
('US', 'États-Unis');

-- --------------------------------------------------------

--
-- Structure de la table `piecejointe`
--

DROP TABLE IF EXISTS `piecejointe`;
CREATE TABLE IF NOT EXISTS `piecejointe` (
  `id` int(11) NOT NULL,
  `chemin` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `piecejointe`
--

INSERT INTO `piecejointe` (`id`, `chemin`, `description`) VALUES
(1, 'images/eclipse.jpg', 'Photo du cheval Eclipse'),
(2, 'images/aztec.jpg', 'Photo du cheval Aztec');

-- --------------------------------------------------------

--
-- Structure de la table `race`
--

DROP TABLE IF EXISTS `race`;
CREATE TABLE IF NOT EXISTS `race` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `race`
--

INSERT INTO `race` (`id`, `libelle`) VALUES
(1, 'Pur-sang anglais'),
(2, 'Quarter Horse'),
(3, 'Frison'),
(4, 'Andalou'),
(5, 'Lipizzan'),
(6, 'Mustang');

-- --------------------------------------------------------

--
-- Structure de la table `reproducteur`
--

DROP TABLE IF EXISTS `reproducteur`;
CREATE TABLE IF NOT EXISTS `reproducteur` (
  `idCheval` int(11) NOT NULL,
  `idReproducteur` int(11) NOT NULL,
  PRIMARY KEY (`idCheval`,`idReproducteur`),
  KEY `fk_reproducteur_parent` (`idReproducteur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Structure de la table `robe`
--

DROP TABLE IF EXISTS `robe`;
CREATE TABLE IF NOT EXISTS `robe` (
  `typeRobe` varchar(50) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`typeRobe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `robe`
--

INSERT INTO `robe` (`typeRobe`, `libelle`) VALUES
('Alezan', 'poil roux, crins souvent de la même teinte.'),
('Bai', 'corps brun avec crins, extrémités et bout du nez noirs.'),
('Gris', 'mélange de poils blancs et foncés, s’éclaircissant avec l’âge.'),
('Isabelle', 'poil jaune/sable, crins noirs.'),
('Noir', 'entièrement noir.'),
('Pie', 'grandes taches blanches et colorées.');

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

DROP TABLE IF EXISTS `vente`;
CREATE TABLE IF NOT EXISTS `vente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `idCategVente` int(11) DEFAULT NULL,
  `idLieu` int(11) DEFAULT NULL,
  `dateDebutVente` date DEFAULT NULL,
  `dateFinVente` date DEFAULT NULL,
  `dateDebutInscription` varchar(50) DEFAULT NULL,
  `dateEnvoiMessage` varchar(50) DEFAULT NULL,
  `objetMessage` varchar(50) DEFAULT NULL,
  `corpsMessage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vente_categ` (`idCategVente`),
  KEY `fk_vente_lieu` (`idLieu`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`id`, `nom`, `idCategVente`, `idLieu`, `dateDebutVente`, `dateFinVente`, `dateDebutInscription`, `dateEnvoiMessage`, `objetMessage`, `corpsMessage`) VALUES
(1, 'Vente Élite de Printemps', 1, 1, '2026-05-10', NULL, NULL, NULL, NULL, NULL),
(2, 'Sélection Obstacle Deauville', 1, 2, '2026-05-25', NULL, NULL, NULL, NULL, NULL),
(3, 'Vente Mixte de Caen', 2, 1, '2026-06-05', NULL, NULL, NULL, NULL, NULL),
(4, 'Trophée des Yearlings', 3, 2, '2026-06-15', NULL, NULL, NULL, NULL, NULL),
(5, 'Vente d\'Été Internationale', 1, 1, '2026-07-02', NULL, NULL, NULL, NULL, NULL),
(6, 'Elevage et Sport Lamotte', 2, 2, '2026-07-20', NULL, NULL, NULL, NULL, NULL),
(7, 'Vente de Prestige Août', 1, 2, '2026-08-10', NULL, NULL, NULL, NULL, NULL),
(8, 'Foals d\'Excellence', 3, 1, '2026-08-28', NULL, NULL, NULL, NULL, NULL),
(9, 'Vente d\'Automne Parisienne', 1, 1, '2026-09-12', NULL, NULL, NULL, NULL, NULL),
(10, 'Hivernale des Champions', 2, 2, '2026-11-05', NULL, NULL, NULL, NULL, NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `attacher`
--
ALTER TABLE `attacher`
  ADD CONSTRAINT `fk_attacher_courriel` FOREIGN KEY (`idCourriel`) REFERENCES `courriel` (`id`),
  ADD CONSTRAINT `fk_attacher_piece` FOREIGN KEY (`idPieceJointe`) REFERENCES `piecejointe` (`id`);

--
-- Contraintes pour la table `avoir`
--
ALTER TABLE `avoir`
  ADD CONSTRAINT `avoir_ibfk_1` FOREIGN KEY (`id`) REFERENCES `vente` (`id`),
  ADD CONSTRAINT `avoir_ibfk_2` FOREIGN KEY (`id_1`) REFERENCES `race` (`id`);

--
-- Contraintes pour la table `cheval`
--
ALTER TABLE `cheval`
  ADD CONSTRAINT `fk_cheval_mere` FOREIGN KEY (`cheval_mere`) REFERENCES `cheval` (`id`),
  ADD CONSTRAINT `fk_cheval_pere` FOREIGN KEY (`cheval_pere`) REFERENCES `cheval` (`id`),
  ADD CONSTRAINT `fk_race` FOREIGN KEY (`race_id`) REFERENCES `race` (`id`),
  ADD CONSTRAINT `fk_typeRobe` FOREIGN KEY (`typeRobe`) REFERENCES `robe` (`typeRobe`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`code`) REFERENCES `pays` (`code`);

--
-- Contraintes pour la table `courriel`
--
ALTER TABLE `courriel`
  ADD CONSTRAINT `fk_courriel_vente` FOREIGN KEY (`idVente`) REFERENCES `vente` (`id`);

--
-- Contraintes pour la table `enchere`
--
ALTER TABLE `enchere`
  ADD CONSTRAINT `fk_enchere_client` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `fk_enchere_lot` FOREIGN KEY (`idLot`) REFERENCES `lot` (`id`);

--
-- Contraintes pour la table `interesser`
--
ALTER TABLE `interesser`
  ADD CONSTRAINT `fk_interesser_categ` FOREIGN KEY (`idCategVente`) REFERENCES `categvente` (`id`),
  ADD CONSTRAINT `fk_interesser_client` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `lot`
--
ALTER TABLE `lot`
  ADD CONSTRAINT `fk_lot_cheval` FOREIGN KEY (`idCheval`) REFERENCES `cheval` (`id`),
  ADD CONSTRAINT `fk_lot_vente` FOREIGN KEY (`idVente`) REFERENCES `vente` (`id`);

--
-- Contraintes pour la table `participer`
--
ALTER TABLE `participer`
  ADD CONSTRAINT `fk_participer_cheval` FOREIGN KEY (`idCheval`) REFERENCES `cheval` (`id`),
  ADD CONSTRAINT `fk_participer_course` FOREIGN KEY (`idCourse`) REFERENCES `course` (`id`);

--
-- Contraintes pour la table `reproducteur`
--
ALTER TABLE `reproducteur`
  ADD CONSTRAINT `fk_reproducteur_cheval` FOREIGN KEY (`idCheval`) REFERENCES `cheval` (`id`),
  ADD CONSTRAINT `fk_reproducteur_parent` FOREIGN KEY (`idReproducteur`) REFERENCES `cheval` (`id`);

--
-- Contraintes pour la table `vente`
--
ALTER TABLE `vente`
  ADD CONSTRAINT `fk_vente_categ` FOREIGN KEY (`idCategVente`) REFERENCES `categvente` (`id`),
  ADD CONSTRAINT `fk_vente_lieu` FOREIGN KEY (`idLieu`) REFERENCES `lieu` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
