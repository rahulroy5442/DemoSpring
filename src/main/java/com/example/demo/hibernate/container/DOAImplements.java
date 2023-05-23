package com.example.demo.hibernate.container;

import com.example.demo.hibernate.child.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DOAImplements implements DOA {
    @Autowired
    @Qualifier("memberEntityManagerFactory")
    private EntityManager entityManager;
   // private EntityManagerFactory entityFactory;

    @Autowired
    DOAImplements(EntityManager entityManager) {
       // this.entityFactory=Persistence.createEntityManagerFactory("Po");
        this.entityManager = entityManager;
    }


    @Override
    public void save(Student students) {
         entityManager.persist(students);
    }

    @Override
    public Student findByID(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student update(Student theStudent)
    {
        return entityManager.merge(theStudent);
    }

    @Override
    public int DeleteByID(int id)
    {
        int numRowDeleted=0;
        try {
            //EntityManager em = entityManager.createEntityManager();
          // this.entityManager.getTransaction().begin();
            Query qu=this.entityManager.createQuery("DELETE from Student s where s.id = :id");
            numRowDeleted=qu.setParameter("id", id).executeUpdate();
        //    numRowDeleted = theQuery.executeUpdate();
           // this.entityManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println("Error :"+e);
        }
       return numRowDeleted;
    }


}
