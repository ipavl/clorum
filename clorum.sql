-- phpMyAdmin SQL Dump
-- version 4.3.0
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 14, 2014 at 05:18 PM
-- Server version: 10.0.15-MariaDB-log
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `clorum`
--
CREATE DATABASE IF NOT EXISTS `clorum` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `clorum`;

-- --------------------------------------------------------

--
-- Table structure for table `discussions`
--

CREATE TABLE IF NOT EXISTS `discussions` (
`id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(30) NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL,
  `category` varchar(20) NOT NULL,
  `content` text,
  `verified` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `replies`
--

CREATE TABLE IF NOT EXISTS `replies` (
`id` int(11) NOT NULL,
  `parent` int(11) NOT NULL,
  `author` varchar(30) NOT NULL,
  `created` datetime NOT NULL,
  `modified` datetime NOT NULL,
  `content` text NOT NULL,
  `verified` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(254) NOT NULL,
  `password` varchar(254) NOT NULL,
  `registered` datetime NOT NULL,
  `ipaddress` varchar(45) NOT NULL DEFAULT '0.0.0.0',
  `permissions` int(11) NOT NULL DEFAULT '0',
  `bio` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `discussions`
--
ALTER TABLE `discussions`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `replies`
--
ALTER TABLE `replies`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `discussions`
--
ALTER TABLE `discussions`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `replies`
--
ALTER TABLE `replies`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
