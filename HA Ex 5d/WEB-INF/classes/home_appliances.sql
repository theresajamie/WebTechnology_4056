-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 20, 2023 at 06:01 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `home_appliances`
--

-- --------------------------------------------------------

--
-- Table structure for table `appliances`
--

CREATE TABLE `appliances` (
  `ApplianceID` varchar(6) NOT NULL,
  `ApplianceName` varchar(50) NOT NULL,
  `PowerConsumption` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appliances`
--

INSERT INTO `appliances` (`ApplianceID`, `ApplianceName`, `PowerConsumption`) VALUES
('001', 'Refrigerator', 200),
('002', 'Washing Machine', 500),
('003', 'Microwave', 1000),
('004', 'Air Conditioner', 1500),
('005', 'Vacuum Cleaner', 600),
('006', 'Dishwasher', 1200),
('007', 'Ceiling Fan', 75);

-- --------------------------------------------------------

--
-- Table structure for table `technicians`
--

CREATE TABLE `technicians` (
  `TechnicianID` varchar(13) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `ApplianceID` varchar(5) NOT NULL,
  `Experience` float NOT NULL,
  `Certified` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `technicians`
--

INSERT INTO `technicians` (`TechnicianID`, `Name`, `ApplianceID`, `Experience`, `Certified`) VALUES
('311113104025', 'Alex', '002', 10.2, 'YES'),
('311113104039', 'Maria', '004', 5.7, 'NO');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appliances`
--
ALTER TABLE `appliances`
  ADD PRIMARY KEY (`ApplianceID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
