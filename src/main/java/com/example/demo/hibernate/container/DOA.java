package com.example.demo.hibernate.container;

import com.example.demo.hibernate.child.Student;

public interface DOA {
    public void save(Student students);
    public Student findByID(int id);

    public Student update(Student id);

    public int DeleteByID(int id);
}
