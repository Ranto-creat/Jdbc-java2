package Revision;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCRUDImpl implements StudentCRUD {
  private final String url = "jdbc:postgresql://localhost:5432/student";
  private final String user = "postgres";
  private final String password = "dangerous";

  @Override
  public void insert(Student student) throws SQLException {
    String sqlInsert = "insert into student values(?,?,?,?)";
    try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
      pstmt.setInt(1, student.getId());
      pstmt.setString(2, student.getName());
      pstmt.setInt(3, student.getAge());
      pstmt.setString(4, student.getEmail());

      int rowsInserted = pstmt.executeUpdate();

      if (rowsInserted > 0) {
        System.out.println("Student inserted successfully");
      } else {
        System.out.println("Student insert failed");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Student getOneStudent(Student student) {
    String sqlSelect = "SELECT * FROM student WHERE id = ?";
    try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
      ps.setInt(1, student.getId());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new Student(
            rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null; // ou lever une exception
  }

  @Override
  public List<Student> getAll(int id) {
    List<Student> students = new ArrayList<>();
    String sqlSelect = "SELECT * FROM student";
    try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sqlSelect);
        ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        students.add(
            new Student(
                rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return students;
  }

  @Override
  public void updateStudent(Student student) {
    String sqlUpdate = "UPDATE student SET name = ?, age = ?, email = ? WHERE id = ?";
    try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sqlUpdate)) {
      ps.setString(2, student.getName());
      ps.setInt(3, student.getAge());
      ps.setString(4, student.getEmail());
      ps.setInt(1, student.getId());
      int rowsUpToDate = ps.executeUpdate();
      if (rowsUpToDate > 0) {
        System.out.println("Student updated successfully");
      }
      else {
        System.out.println("Student update failed");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteStudent(Student student) {
    String sqlDelete = "DELETE FROM student WHERE id = ?";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
      ps.setInt(1, student.getId());
      ps.executeUpdate();
      System.out.println("Student deleted successfully with ID: " + student);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
