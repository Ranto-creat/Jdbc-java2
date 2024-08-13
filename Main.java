package Revision;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Insert
        StudentCRUDImpl studentCRUD = new StudentCRUDImpl();
        Student Handraina = new Student(2, "Handraina", 19, "hei.handraina.2@gmail.com");
        studentCRUD.insert(Handraina);

        // get one student
        Student student = studentCRUD.getOneStudent(Handraina);
        System.out.println("Retrieved Student: " + student);

        // get all students
        List<Student> students = studentCRUD.getAll(1);
        System.out.println("All Students: " + students);


        // update student
//        if (student != null) {
//            student.setName("Rafalimanana Ranto Handraina");
//            studentCRUD.updateStudent(Student.getId(), Handraina);
//            System.out.println("Updated Student: " + studentCRUD.getOneStudent(student.getId()));
//        }

        // delete student
//        studentCRUD.deleteStudent(Handraina);
    }
}
