package com.faculty.dao;

import com.faculty.model.Lecturer;
import database.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LecturerDAO {

    // Email එක භාවිතා කර Lecturer details database එකෙන් ගන්නවා
    public Lecturer getLecturerByEmail(String email) {

        String sql =
                "SELECT full_name, department, courses_teaching, " +
                        "email, mobile_number " +
                        "FROM lecturers " +
                        "WHERE email = ?";

        try {

            Connection connection =
                    DBconnection.getConnection();

            if (connection == null) {
                return null;
            }

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, email);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                Lecturer lecturer = new Lecturer(
                        resultSet.getString("full_name"),
                        resultSet.getString("department"),
                        resultSet.getString("courses_teaching"),
                        resultSet.getString("email"),
                        resultSet.getString("mobile_number")
                );

                resultSet.close();
                statement.close();
                connection.close();

                return lecturer;
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Lecturer profile details update කරනවා
    public boolean updateLecturer(
            Lecturer lecturer,
            String originalEmail
    ) {

        String sql =
                "UPDATE lecturers SET " +
                        "full_name = ?, " +
                        "department = ?, " +
                        "courses_teaching = ?, " +
                        "email = ?, " +
                        "mobile_number = ? " +
                        "WHERE email = ?";

        try {

            Connection connection =
                    DBconnection.getConnection();

            if (connection == null) {
                return false;
            }

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(
                    1,
                    lecturer.getFullName()
            );

            statement.setString(
                    2,
                    lecturer.getDepartment()
            );

            statement.setString(
                    3,
                    lecturer.getCoursesTeaching()
            );

            statement.setString(
                    4,
                    lecturer.getEmail()
            );

            statement.setString(
                    5,
                    lecturer.getMobileNumber()
            );

            statement.setString(
                    6,
                    originalEmail
            );

            int rowsUpdated =
                    statement.executeUpdate();

            statement.close();
            connection.close();

            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}