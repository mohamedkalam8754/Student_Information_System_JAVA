package com.example.crud.controller;

import com.example.crud.db.StudentDb;
import com.example.crud.model.Student;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAOImp implements StudentDAO {

    @Override
    public void save(Student student) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = StudentDb.getConnnection();
            String sql = "INSERT INTO students(name, email, phonenumber) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getPhonenumber());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            Logger.getLogger(StudentDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update(Student students) {
        Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        con = StudentDb.getConnnection();
        String sql = "UPDATE students SET name=?,email=?,phonenumber=? WHERE rollno=?";
        ps = con.prepareStatement(sql);
            ps.setString(1,students.getName());
            ps.setString(2,students.getEmail());
            ps.setInt(3,students.getPhonenumber());
            ps.setInt(4,students.getRollno());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Updated");
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error");
        }
         
    }
    

    @Override
    public void delete(Student student) {
         Connection con = null;
        PreparedStatement ps = null;
         ResultSet rs = null;
        try{
            con = StudentDb.getConnnection();
            String sql = "DELETE FROM students WHERE rollno=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,student.getRollno());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted");
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    
     public Student get(int rollno) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Student st = new Student();
    try {
        con = StudentDb.getConnnection(); // Corrected method name
        String sql = "SELECT * FROM students WHERE rollno=?";
        ps = con.prepareStatement(sql);
        ps.setInt(1,rollno); // Set the rollno parameter
        rs = ps.executeQuery();

        if (rs.next()) {
            st.setRollno(rs.getInt("rollno"));
            st.setName(rs.getString("name"));
            st.setEmail(rs.getString("email"));
            st.setPhonenumber(rs.getInt("phonenumber"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }   catch (Exception ex) {   
            Logger.getLogger(StudentDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    return st;
}


    @Override
    public List<Student> list() {
        List<Student> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = StudentDb.getConnnection();
            String sql = "SELECT * FROM students";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student st = new Student();
                st.setRollno(rs.getInt("rollno"));
                st.setName(rs.getString("name"));
                st.setEmail(rs.getString("email"));
                st.setPhonenumber(rs.getInt("phonenumber"));
                list.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            Logger.getLogger(StudentDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return list;
    }
}
