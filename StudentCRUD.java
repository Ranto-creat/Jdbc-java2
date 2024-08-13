package Revision;

import java.sql.SQLException;
import java.util.List;

public interface StudentCRUD {
  void insert(Student student) throws SQLException;

  Student getOneStudent(Student student);

  List<Student> getAll(int id);

  void updateStudent(Student student);

  void deleteStudent(Student student);
}
