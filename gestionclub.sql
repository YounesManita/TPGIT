-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 16 mai 2023 à 06:38
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestionclub`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `CIN` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `daten` date DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mdp_user` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`CIN`, `nom`, `prenom`, `daten`, `tel`, `email`, `mdp_user`) VALUES
(12343455, 'Manita', 'younes', '0000-00-00', '96289993', 'younesmanita975@gmail.com', '123456789');

-- --------------------------------------------------------

--
-- Structure de la table `club`
--

CREATE TABLE `club` (
  `idclub` int(11) NOT NULL,
  `nomc` varchar(255) DEFAULT NULL,
  `catc` varchar(255) DEFAULT NULL,
  `nbrc` varchar(255) DEFAULT NULL,
  `Cotisation` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `club`
--

INSERT INTO `club` (`idclub`, `nomc`, `catc`, `nbrc`, `Cotisation`) VALUES
(123, './TKYMTLRKYMRKY', 'IURYTURYRTR', 'LFDKJGKLF', 'null'),
(456789, './F.J/GKJ', '§LKFJFJG', '§MKGJMLGH', 'null');

-- --------------------------------------------------------

--
-- Structure de la table `membreclub`
--

CREATE TABLE `membreclub` (
  `CIN` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `daten` date DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `Image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `membreclub`
--

INSERT INTO `membreclub` (`CIN`, `nom`, `prenom`, `daten`, `tel`, `email`, `Image`) VALUES
(12, 'HJKJHGFD', 'JHGFDS', '1996-04-12', '123456', '.?JHNHGGG', '');

-- --------------------------------------------------------

--
-- Structure de la table `representantclub`
--

CREATE TABLE `representantclub` (
  `CIN` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `daten` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mdp_user` varchar(255) DEFAULT NULL,
  `etatCotisation` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `representantclub`
--

INSERT INTO `representantclub` (`CIN`, `nom`, `prenom`, `daten`, `tel`, `email`, `mdp_user`, `etatCotisation`) VALUES
(12345, 'KSFJDFJD', 'LKSJFHLKF', '0000-00-00', '96289993', 'younesmanita975@gmail.com', '1234567', 'LSKFJHLKJFG');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `CIN` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `daten` date DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`CIN`);

--
-- Index pour la table `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`idclub`);

--
-- Index pour la table `membreclub`
--
ALTER TABLE `membreclub`
  ADD PRIMARY KEY (`CIN`);

--
-- Index pour la table `representantclub`
--
ALTER TABLE `representantclub`
  ADD PRIMARY KEY (`CIN`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`CIN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
