package com.example.demo.hibernate.ServiceLayer;

import com.example.demo.hibernate.child.Student;

public interface Serviceinterface {
    public void save(Student stu);
    public Student update(Student sty);

    public int DeleteByID(int id);
    public Student findById(int id);
}

