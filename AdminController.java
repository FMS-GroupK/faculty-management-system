package com.faculty.controller;

import com.faculty.model.Course;
import com.faculty.model.Degree;
import com.faculty.model.Department;
import com.faculty.model.Lecturer;
import com.faculty.model.Student;
import database.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AdminController {

    //Students

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT s.id, s.name, s.student_id, s.degree_id, dg.degree AS degree_name, " +
                "s.email, s.mobile_number " +
                "FROM students s LEFT JOIN degrees dg ON s.degree_id = dg.id " +
                "ORDER BY s.name";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("student_id"),
                        (Integer) rs.getObject("degree_id"),
                        rs.getString("email"),
                        rs.getString("mobile_number")
                );
                s.setDegreeName(rs.getString("degree_name"));
                list.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void addStudent(Student s) {
        String degreeName = getDegreeNameById(s.getDegreeId());
        String sql = "INSERT INTO students (student_id, name, degree, degree_id, email, mobile_number) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getStudentNumber());
            ps.setString(2, s.getFullName());
            ps.setString(3, degreeName == null ? "" : degreeName);
            setNullableInt(ps, 4, s.getDegreeId());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getMobileNumber());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateStudent(Student s) {
        String degreeName = getDegreeNameById(s.getDegreeId());
        String sql = "UPDATE students SET name = ?, student_id = ?, degree = ?, degree_id = ?, " +
                "email = ?, mobile_number = ? WHERE id = ?";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getFullName());
            ps.setString(2, s.getStudentNumber());
            ps.setString(3, degreeName == null ? "" : degreeName);
            setNullableInt(ps, 4, s.getDegreeId());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getMobileNumber());
            ps.setInt(7, s.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        runDelete("DELETE FROM students WHERE id = ?", id);
    }

    //Lecturers

    public List<Lecturer> getAllLecturers() {
        List<Lecturer> list = new ArrayList<>();
        String sql = "SELECT l.id, l.full_name, l.department_id, d.name AS department_name, " +
                "l.courses_teaching, l.email, l.mobile_number " +
                "FROM lecturers l LEFT JOIN departments d ON l.department_id = d.id " +
                "ORDER BY l.full_name";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Lecturer l = new Lecturer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        (Integer) rs.getObject("department_id"),
                        rs.getString("email"),
                        rs.getString("mobile_number")
                );
                l.setDepartmentName(rs.getString("department_name"));
                l.setCoursesTeaching(rs.getString("courses_teaching"));
                list.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void addLecturer(Lecturer l) {
        String deptName = getDepartmentNameById(l.getDepartmentId());
        String sql = "INSERT INTO lecturers (full_name, department, department_id, courses_teaching, email, mobile_number) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, l.getFullName());
            ps.setString(2, deptName == null ? "" : deptName);
            setNullableInt(ps, 3, l.getDepartmentId());
            ps.setString(4, l.getCoursesTeaching() == null ? "" : l.getCoursesTeaching());
            ps.setString(5, l.getEmail());
            ps.setString(6, l.getMobileNumber());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateLecturer(Lecturer l) {
        String deptName = getDepartmentNameById(l.getDepartmentId());
        String sql = "UPDATE lecturers SET full_name = ?, department = ?, department_id = ?, " +
                "courses_teaching = ?, email = ?, mobile_number = ? WHERE id = ?";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, l.getFullName());
            ps.setString(2, deptName == null ? "" : deptName);
            setNullableInt(ps, 3, l.getDepartmentId());
            ps.setString(4, l.getCoursesTeaching() == null ? "" : l.getCoursesTeaching());
            ps.setString(5, l.getEmail());
            ps.setString(6, l.getMobileNumber());
            ps.setInt(7, l.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteLecturer(int id) {
        runDelete("DELETE FROM lecturers WHERE id = ?", id);
    }

    //Courses

    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT c.id, c.course_code, c.course_name, c.credits, c.lecturer_id, " +
                "l.full_name AS lecturer_name " +
                "FROM courses c LEFT JOIN lecturers l ON c.lecturer_id = l.id " +
                "ORDER BY c.course_code";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Course c = new Course(
                        rs.getInt("id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getInt("credits"),
                        (Integer) rs.getObject("lecturer_id")
                );
                c.setLecturerName(rs.getString("lecturer_name"));
                list.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void addCourse(Course c) {
        String lecturerName = getLecturerNameById(c.getLecturerId());
        String sql = "INSERT INTO courses (course_code, course_name, credits, lecturer_id, lecturer) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getCourseCode());
            ps.setString(2, c.getCourseName());
            ps.setInt(3, c.getCredits());
            setNullableInt(ps, 4, c.getLecturerId());
            ps.setString(5, lecturerName == null ? "" : lecturerName);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateCourse(Course c) {
        String lecturerName = getLecturerNameById(c.getLecturerId());
        String sql = "UPDATE courses SET course_code = ?, course_name = ?, credits = ?, " +
                "lecturer_id = ?, lecturer = ? WHERE id = ?";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getCourseCode());
            ps.setString(2, c.getCourseName());
            ps.setInt(3, c.getCredits());
            setNullableInt(ps, 4, c.getLecturerId());
            ps.setString(5, lecturerName == null ? "" : lecturerName);
            ps.setInt(6, c.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteCourse(int id) {
        runDelete("DELETE FROM courses WHERE id = ?", id);
    }

    //Departments

    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT d.id, d.name, d.hod, " +
                "(SELECT COUNT(*) FROM lecturers l WHERE l.department_id = d.id) AS staff_count " +
                "FROM departments d ORDER BY d.name";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Department d = new Department(rs.getInt("id"), rs.getString("name"), rs.getString("hod"));
                d.setStaffCount(rs.getInt("staff_count"));
                list.add(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void addDepartment(Department d) {
        String sql = "INSERT INTO departments (name, hod) VALUES (?, ?)";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setString(2, d.getHodName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateDepartment(Department d) {
        String sql = "UPDATE departments SET name = ?, hod = ? WHERE id = ?";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setString(2, d.getHodName());
            ps.setInt(3, d.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDepartment(int id) {
        runDelete("DELETE FROM departments WHERE id = ?", id);
    }

    //Degrees

    public List<Degree> getAllDegrees() {
        List<Degree> list = new ArrayList<>();
        String sql = "SELECT dg.id, dg.degree AS name, dg.department_id, d.name AS department_name, " +
                "(SELECT COUNT(*) FROM students s WHERE s.degree_id = dg.id) AS student_count " +
                "FROM degrees dg LEFT JOIN departments d ON dg.department_id = d.id " +
                "ORDER BY dg.degree";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Degree d = new Degree(rs.getInt("id"), rs.getString("name"), (Integer) rs.getObject("department_id"));
                d.setDepartmentName(rs.getString("department_name"));
                d.setStudentCount(rs.getInt("student_count"));
                list.add(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void addDegree(Degree d) {
        String deptName = getDepartmentNameById(d.getDepartmentId());
        String sql = "INSERT INTO degrees (degree, department, department_id) VALUES (?, ?, ?)";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setString(2, deptName == null ? "" : deptName);
            setNullableInt(ps, 3, d.getDepartmentId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateDegree(Degree d) {
        String deptName = getDepartmentNameById(d.getDepartmentId());
        String sql = "UPDATE degrees SET degree = ?, department = ?, department_id = ? WHERE id = ?";
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setString(2, deptName == null ? "" : deptName);
            setNullableInt(ps, 3, d.getDepartmentId());
            ps.setInt(4, d.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDegree(int id) {
        runDelete("DELETE FROM degrees WHERE id = ?", id);
    }


    private void runDelete(String sql, int id) {
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setNullableInt(PreparedStatement ps, int index, Integer value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.INTEGER);
        } else {
            ps.setInt(index, value);
        }
    }

    private String getDepartmentNameById(Integer id) {
        if (id == null) return null;
        return lookupName("SELECT name FROM departments WHERE id = ?", id);
    }

    private String getDegreeNameById(Integer id) {
        if (id == null) return null;
        return lookupName("SELECT degree FROM degrees WHERE id = ?", id);
    }

    private String getLecturerNameById(Integer id) {
        if (id == null) return null;
        return lookupName("SELECT full_name FROM lecturers WHERE id = ?", id);
    }

    private String lookupName(String sql, int id) {
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}