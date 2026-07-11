-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2026 at 09:59 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `faculty_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
                           `course_code` varchar(20) NOT NULL,
                           `course_name` varchar(100) NOT NULL,
                           `credits` int(11) NOT NULL,
                           `lecturer` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_code`, `course_name`, `credits`, `lecturer`) VALUES
                                                                                ('ETEC21013', 'Operating Systems', 3, 'Dilani Perera'),
                                                                                ('ETEC21023', 'Web Development', 2, 'Chathura Wijesinghe'),
                                                                                ('ETEC21033', 'Computer Networks', 2, 'Sanduni Fernando'),
                                                                                ('ETEC21043', 'Software Engineering', 2, 'Amal Silva'),
                                                                                ('ETEC21053', 'Database Management Systems', 3, 'Nimal Perera'),
                                                                                ('ETEC21063', 'OOP', 2, 'Kumar Sanga');

-- --------------------------------------------------------

--
-- Table structure for table `course_enrolled`
--

CREATE TABLE `course_enrolled` (
                                   `course_code` varchar(20) NOT NULL,
                                   `course_name` varchar(100) DEFAULT NULL,
                                   `credits` int(11) DEFAULT NULL,
                                   `grade` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `course_enrolled`
--

INSERT INTO `course_enrolled` (`course_code`, `course_name`, `credits`, `grade`) VALUES
                                                                                     ('ETEC21012', 'OOP', 2, 'B'),
                                                                                     ('ETEC21022', 'OOP', 2, 'C'),
                                                                                     ('ETEC21032', 'OOP', 2, 'D'),
                                                                                     ('ETEC21042', 'OOP', 2, 'A'),
                                                                                     ('ETEC21052', 'OOP', 2, 'B'),
                                                                                     ('ETEC21062', 'OOP', 2, 'A+');

-- --------------------------------------------------------

--
-- Table structure for table `degrees`
--

CREATE TABLE `degrees` (
                           `degree` varchar(100) NOT NULL,
                           `department` varchar(100) DEFAULT NULL,
                           `no_of_students` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `degrees`
--

INSERT INTO `degrees` (`degree`, `department`, `no_of_students`) VALUES
                                                                     ('Bio System Technology', 'Applied Computing', 75),
                                                                     ('Computer Science', 'Computer Systems Engineering', 325),
                                                                     ('Engineering Technology', 'Applied Computing', 375),
                                                                     ('Information Technology', 'Software Engineering', 375);

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
                               `name` varchar(100) NOT NULL,
                               `hod` varchar(100) DEFAULT NULL,
                               `degree` varchar(100) DEFAULT NULL,
                               `no_of_staff` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`name`, `hod`, `degree`, `no_of_staff`) VALUES
                                                                       ('Applied Computing', 'Kumar Sanga', 'Engineering Technology', 15),
                                                                       ('Computer Systems Engineering', 'Kumar Sanga', 'Computer Science', 12),
                                                                       ('Software Engineering', 'Kumar Sanga', 'Information Technology', 17);

-- --------------------------------------------------------

--
-- Table structure for table `lecturers`
--

CREATE TABLE `lecturers` (
                             `full_name` varchar(100) NOT NULL,
                             `department` varchar(100) NOT NULL,
                             `courses_teaching` varchar(100) NOT NULL,
                             `email` varchar(100) NOT NULL,
                             `mobile_number` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lecturers`
--

INSERT INTO `lecturers` (`full_name`, `department`, `courses_teaching`, `email`, `mobile_number`) VALUES
                                                                                                      ('Amal Silva', 'Software Engineering', 'Software Engineering', 'amal@kln.ac.lk', '0722222222'),
                                                                                                      ('Chathura Wijesinghe', 'Applied Computing', 'Web Development', 'chathura@kln.ac.lk', '0744444444'),
                                                                                                      ('Dilani Perera', 'Software Engineering', 'Operating Systems', 'dilani@kln.ac.lk', '0755555555'),
                                                                                                      ('Kumar Sanga', 'Applied Computing', 'OOP', 'kumar@kln.ac.lk', '0771234567'),
                                                                                                      ('Nimal Perera', 'Applied Computing', 'Database Management Systems', 'nimal@kln.ac.lk', '0711111111'),
                                                                                                      ('Sanduni Fernando', 'Computer Systems Engineering', 'Computer Networks', 'sanduni@kln.ac.lk', '0733333333');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
                            `student_id` varchar(20) NOT NULL,
                            `name` varchar(100) NOT NULL,
                            `degree` varchar(100) NOT NULL,
                            `email` varchar(100) NOT NULL,
                            `mobile_number` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `name`, `degree`, `email`, `mobile_number`) VALUES
                                                                                      ('ET/2022/007', 'Kumar Sangakkara', 'Engineering Technology', 'kumars@kln.ac.lk', '0123456789'),
                                                                                      ('ET/2022/008', 'Mahela Jayawardene', 'Engineering Technology', 'mahela@kln.ac.lk', '0712345678'),
                                                                                      ('ET/2022/009', 'Lasith Malinga', 'Engineering Technology', 'lasith@kln.ac.lk', '0723456789'),
                                                                                      ('ET/2022/010', 'Angelo Mathews', 'Engineering Technology', 'angelo@kln.ac.lk', '0734567890'),
                                                                                      ('ET/2022/011', 'Dinesh Chandimal', 'Engineering Technology', 'dinesh@kln.ac.lk', '0745678901'),
                                                                                      ('ET/2022/012', 'Charith Asalanka', 'Engineering Technology', 'charith@kln.ac.lk', '0756789012');

-- --------------------------------------------------------

--
-- Table structure for table `timetable`
--

CREATE TABLE `timetable` (
                             `time_slot` varchar(10) NOT NULL,
                             `monday` varchar(30) DEFAULT NULL,
                             `tuesday` varchar(30) DEFAULT NULL,
                             `wednesday` varchar(30) DEFAULT NULL,
                             `thursday` varchar(30) DEFAULT NULL,
                             `friday` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `timetable`
--

INSERT INTO `timetable` (`time_slot`, `monday`, `tuesday`, `wednesday`, `thursday`, `friday`) VALUES
                                                                                                  ('01.00', 'SE', 'OOP', 'SE', 'SE', 'SE'),
                                                                                                  ('03.00', 'SE', 'OOP', 'SE', 'SE', 'SE'),
                                                                                                  ('08.00', 'OOP', 'OOP', 'OOP', 'OOP', 'OOP'),
                                                                                                  ('10.00', 'OOP', 'OOP', 'OOP', 'OOP', 'OOP');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
                         `username` varchar(50) NOT NULL,
                         `password` varchar(100) NOT NULL,
                         `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `role`) VALUES
                                                         ('admin', 'admin123', 'ADMIN'),
                                                         ('student', 'student123', 'STUDENT');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
    ADD PRIMARY KEY (`course_code`);

--
-- Indexes for table `course_enrolled`
--
ALTER TABLE `course_enrolled`
    ADD PRIMARY KEY (`course_code`);

--
-- Indexes for table `degrees`
--
ALTER TABLE `degrees`
    ADD PRIMARY KEY (`degree`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
    ADD PRIMARY KEY (`name`);

--
-- Indexes for table `lecturers`
--
ALTER TABLE `lecturers`
    ADD PRIMARY KEY (`full_name`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
    ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `timetable`
--
ALTER TABLE `timetable`
    ADD PRIMARY KEY (`time_slot`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;