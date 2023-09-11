
package com.example.crud.controller;
import com.example.crud.model.Student;
import java.util.List;

public interface StudentDAO {
    
    public void save(Student students);
    public void update(Student students);
    public void delete(Student students);
    public Student get(int rollno);
    public List<Student>list();
          
}
