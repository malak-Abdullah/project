-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 10 نوفمبر 2018 الساعة 02:11
-- إصدار الخادم: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `plantsdetail`
--

-- --------------------------------------------------------

--
-- بنية الجدول `tblplants`
--

CREATE TABLE `tblplants` (
  `intId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `Type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- إرجاع أو استيراد بيانات الجدول `tblplants`
--

INSERT INTO `tblplants` (`intId`, `Name`, `Description`, `Type`) VALUES
(1, 'Cupressus, aromatic plant of cypresses', 'Take care of it:\r\nhe likes bright light and not exposed to it affects the color of the leaves, because exposure to light gives its white leaves this beautiful lime green color. It is suitable for good soil drainage and tilts slightly to acidity. The plant accepts Pruning. It is preferred that the moisture be in the atmosphere between 30% to 50% and that the thermal range is between 18 to 20 ° C.', 'Plant'),
(2, 'Ficus pumila plant', 'Take care of it:\r\nhe likes The average brightness is necessary and it is well irrigated all the year except the winter period, it is to sleep, which reduces the irrigation. In this season, it is better to use lukewarm water and to preserve the moisture of the soil so as not to alter the leaves. The temperature should not be lower than 10 ° C in winter.', 'Plant'),
(3, 'plant Haworthia cymbiformis', 'Take care of it:\r\nThe abundance of water is the main danger to one\'s life, so should not be seen unless the soil is almost dry and moderately moderate.\r\nhe likes indirect light, exposed to direct light, causes yellowing of his leaves or makes it white. Wear at low temperatures up to 10 ° C', 'Plant'),
(4, 'Aspidistra elatior plants', 'He does not take much care but moderate irrigation in summer and a little in winter, and must also clean his papers by wiping dust from time to time. It supports a wide temperature range of at least 10 ° C (optimum between 10 and 15 ° C). High plant loving moisture.', 'Plant'),
(5, 'Orchid Phalaenopsis', 'Take care of it:\r\nit likes light without direct sunlight and in full activity between 20 ° C and 22 ° C, do not expose the plant to sudden changes in temperature or drafts. Be sure to ventilate well without drafts, irrigated when the soil is almost semi-dry. At a rate per week during flowering or every 10 days during normal growth without flowering.', 'Plant'),
(6, 'Echeveria plant', 'Take care of it:\r\nThe temperature is between 15 ° C and 25 ° C and can withstand a very cold cold in winter. The drought is excellent and should not be sprayed with water droplets. Moisture can cause rot because it affects the wax layer. he loves bright light and prefers to expose it to direct sunlight in winter.\r\nPlant the plant moderately throughout the year', 'Plant'),
(7, 'Mint', 'Care for it:\r\nIt should be placed at a temperature of 18-21 ° C in the daytime, and the temperature at night is 13-15 ° C. It needs direct sunlight, but it can only grow in areas that are partially exposed to the sun. It should be kept moist.', 'Plant');

-- --------------------------------------------------------

--
-- بنية الجدول `tblvegitables`
--

CREATE TABLE `tblvegitables` (
  `intId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `Type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- إرجاع أو استيراد بيانات الجدول `tblvegitables`
--

INSERT INTO `tblvegitables` (`intId`, `Name`, `Description`, `Type`) VALUES
(1, 'Guzmania lingulata minor', 'Care for it:\r\nA warm-loving plant. It is best not to be less than 16 ° C during winter, and likes moisture. The shade is mild in summer, and it is good to expose it to indirect bright light in the winter. It is abundantly sung throughout the year except for winter and reduces irrigation', 'Vegetables'),
(2, 'Lettuce', 'Care for it:\r\nLettuce from cold plants, it needs a cold and mild climate, not warm, spring or autumn season is best suited for planting with the availability of appropriate soil, and provides lighting and irrigation', 'Vegetables'),
(3, 'Watercress', 'Care for it:\r\nWatercress grows in a temperate environment that tends to cold, and is not suitable for high temperatures. Therefore, it is considered a winter plant. It lives in most soil types, but organic soil is rich in organic matter.', 'Vegetables'),
(4, 'tomatoes', 'Care for it:\r\nThe best production of tomatoes occurs at moderate temperatures between 21-23.9 ° C during the daytime and approximately 18.4 - 20 ° C at night, maintaining the soil moisture level to a reasonable extent in order to reduce the breakage and rot of the plant', 'Vegetables'),
(5, 'Garlic', 'Care for it:\r\n  The first half of autumn, or early spring, is the time to grow garlic, to plant plants almost always, preferably once a week, while the rest of the days prefer to sprinkle with a little water, and needs a sunny place, it should get six hours of light The sun on a daily basis', 'Vegetables'),
(6, 'Beans', 'Take care of it:\r\nBeans need a temperate climate, you can not live less than 12 degrees Celsius and you need a fertile and warm soil.\r\nBean plants need moisture in their soil at all times, so it is recommended to dry them once a week in general and to increase the number\r\nmany times in dry weather.', 'Vegetables'),
(7, 'Radish', 'Care for it:\r\nRadish needs a mild, cool atmosphere. It is preferred to grow in the winter and autumn seasons, the seeds grow at a temperature of 16-25 ° C, the irrigation should be regularized to provide moisture', 'Vegetables'),
(8, 'Spinach', 'Care for it:\r\nSpinach prefers to grow at a temperature of 1 - 23 ° C, to be exposed to the sun, to irrigate the soil in the form of a light spray. If the area is very hot, the soil should be irrigated twice a day.', 'Vegetables');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblplants`
--
ALTER TABLE `tblplants`
  ADD PRIMARY KEY (`intId`);

--
-- Indexes for table `tblvegitables`
--
ALTER TABLE `tblvegitables`
  ADD PRIMARY KEY (`intId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblplants`
--
ALTER TABLE `tblplants`
  MODIFY `intId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tblvegitables`
--
ALTER TABLE `tblvegitables`
  MODIFY `intId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
