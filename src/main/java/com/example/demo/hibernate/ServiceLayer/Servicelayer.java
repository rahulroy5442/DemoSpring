package com.example.demo.hibernate.ServiceLayer;

import com.example.demo.hibernate.child.Student;
import com.example.demo.hibernate.container.DOA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Servicelayer implements Serviceinterface {
    DOA obj;
    @Autowired
    public Servicelayer(DOA DoaObjecct)
    {
        this.obj=DoaObjecct;
    }

    @Transactional
    @Override
    public void save(Student stu)
    {
      //  stu.setId(0);
        obj.save(stu);
    }

    @Transactional
    @Override
    public Student update(Student stu)
    {

        return obj.update(stu);
    }

    @Transactional
    @Override
    public int DeleteByID(int id)
    {
       return obj.DeleteByID(id);
    }

    @Override
    public Student findById(int id)
    {
        return obj.findByID(id);
    }
}
