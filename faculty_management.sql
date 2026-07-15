-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 13, 2026 at 03:04 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

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
  `id` int(11) NOT NULL,
  `course_code` varchar(20) NOT NULL,
  `course_name` varchar(100) NOT NULL,
  `credits` int(11) NOT NULL,
  `lecturer` varchar(100) NOT NULL,
  `lecturer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`id`, `course_code`, `course_name`, `credits`, `lecturer`, `lecturer_id`) VALUES
(1, 'ETEC21013', 'Operating Systems', 3, 'Dilani Perera', 3),
(2, 'ETEC21023', 'Web Development', 2, 'Chathura Wijesinghe', 2),
(3, 'ETEC21033', 'Computer Networks', 2, 'Sanduni Fernando', 6),
(4, 'ETEC21043', 'Software Engineering', 2, 'Amal Silva', 1),
(5, 'ETEC21053', 'Database Management Systems', 3, 'Nimal Perera', 5),
(6, 'ETEC21063', 'OOP', 2, 'Kumar Sanga', 4);

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
  `id` int(11) NOT NULL,
  `degree` varchar(100) NOT NULL,
  `department` varchar(100) DEFAULT NULL,
  `no_of_students` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `degrees`
--

INSERT INTO `degrees` (`id`, `degree`, `department`, `no_of_students`, `department_id`) VALUES
(1, 'Bio System Technology', 'Applied Computing', 75, 1),
(2, 'Computer Science', 'Computer Systems Engineering', 325, 2),
(3, 'Engineering Technology', 'Applied Computing', 375, 1),
(4, 'Information Technology', 'Software Engineering', 375, 3);

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `hod` varchar(100) DEFAULT NULL,
  `degree` varchar(100) DEFAULT NULL,
  `no_of_staff` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`id`, `name`, `hod`, `degree`, `no_of_staff`) VALUES
(1, 'Applied Computing', 'Kumar Sanga', 'Engineering Technology', 15),
(2, 'Computer Systems Engineering', 'Kumar Sanga', 'Computer Science', 12),
(3, 'Software Engineering', 'Kumar Sanga', 'Information Technology', 17);

-- --------------------------------------------------------

--
-- Table structure for table `lecturers`
--

CREATE TABLE `lecturers` (
  `id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `department` varchar(100) NOT NULL,
  `courses_teaching` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(15) NOT NULL,
  `department_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lecturers`
--

INSERT INTO `lecturers` (`id`, `full_name`, `department`, `courses_teaching`, `email`, `mobile_number`, `department_id`) VALUES
(1, 'Amal Silva', 'Software Engineering', 'Software Engineering', 'amal@kln.ac.lk', '0722222222', 3),
(2, 'Chathura Wijesinghe', 'Applied Computing', 'Web Development', 'chathura@kln.ac.lk', '0744444444', 1),
(3, 'Dilani Perera', 'Software Engineering', 'Operating Systems', 'dilani@kln.ac.lk', '0755555555', 3),
(4, 'Kumar Sanga', 'Applied Computing', 'OOP', 'kumar@kln.ac.lk', '0771234567', 1),
(5, 'Nimal Perera', 'Applied Computing', 'Database Management Systems', 'nimal@kln.ac.lk', '0711111111', 1),
(6, 'Sanduni Fernando', 'Computer Systems Engineering', 'Computer Networks', 'sanduni@kln.ac.lk', '0733333333', 2);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `degree` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(15) NOT NULL,
  `degree_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `student_id`, `name`, `degree`, `email`, `mobile_number`, `degree_id`) VALUES
(1, 'ET/2022/007', 'Kumar S.', 'Engineering Technology', 'kumars@kln.ac.lk', '0123456789', 3),
(2, 'ET/2022/008', 'Mahela Jayawardene', 'Engineering Technology', 'mahela@kln.ac.lk', '0712345678', 3),
(3, 'ET/2022/009', 'Lasith Malinga', 'Engineering Technology', 'lasith@kln.ac.lk', '0723456789', 3),
(4, 'ET/2022/010', 'Angelo Mathews', 'Engineering Technology', 'angelo@kln.ac.lk', '0734567890', 3),
(5, 'ET/2022/011', 'Dinesh Chandimal', 'Engineering Technology', 'dinesh@kln.ac.lk', '0745678901', 3),
(6, 'ET/2022/012', 'Charith Asalanka', 'Engineering Technology', 'charith@kln.ac.lk', '0756789012', 3);

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
('01.00', 'OOP', 'OOP', 'OOP', 'OOP', 'OOP'),
('03.00', 'OOP', 'OOP', 'OOP', 'OOP', 'OOP'),
('08.00', 'SE', 'OOP', 'SE', 'SE', 'SE'),
('10.00', 'SE', 'OOP', 'SE', 'SE', 'SE');

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
('Dilani Perera', '222222', 'LECTURER'),
('Kesavan Selvarajah', '111111', 'ADMIN'),
('Lasith Malinga', '000000', 'STUDENT');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_courses_code` (`course_code`),
  ADD KEY `fk_courses_lecturer` (`lecturer_id`);

--
-- Indexes for table `course_enrolled`
--
ALTER TABLE `course_enrolled`
  ADD PRIMARY KEY (`course_code`);

--
-- Indexes for table `degrees`
--
ALTER TABLE `degrees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_degrees_name` (`degree`),
  ADD KEY `fk_degrees_department` (`department_id`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_departments_name` (`name`);

--
-- Indexes for table `lecturers`
--
ALTER TABLE `lecturers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_lecturers_email` (`email`),
  ADD KEY `fk_lecturers_department` (`department_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_students_student_id` (`student_id`),
  ADD KEY `fk_students_degree` (`degree_id`);

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

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `degrees`
--
ALTER TABLE `degrees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `lecturers`
--
ALTER TABLE `lecturers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `fk_courses_lecturer` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturers` (`id`);

--
-- Constraints for table `degrees`
--
ALTER TABLE `degrees`
  ADD CONSTRAINT `fk_degrees_department` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);

--
-- Constraints for table `lecturers`
--
ALTER TABLE `lecturers`
  ADD CONSTRAINT `fk_lecturers_department` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `fk_students_degree` FOREIGN KEY (`degree_id`) REFERENCES `degrees` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
