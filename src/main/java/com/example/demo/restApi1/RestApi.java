package com.example.demo.restApi1;


import com.example.demo.DependencyInjection.Parent2;
import com.example.demo.hibernate.ServiceLayer.Servicelayer;
import com.example.demo.hibernate.child.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@RestController
public class RestApi {

    public String aw;
    public Parent2 aw2;
    public Servicelayer SerObj;

    @Autowired
    public RestApi(Servicelayer Service)
    {

        SerObj=Service;
       // aw2=ao2;
    }
    @GetMapping("/")
    public String helloworld(){
        return "Hello World";
    }
//jddr
    @DeleteMapping("/api/{id}")
    public int DeleteByID(@PathVariable int id){

        return SerObj.DeleteByID(id);
    }

    @GetMapping("/api/{id}")
    public Student findByID(@PathVariable int id){

        return SerObj.findById(id);
    }

    @PostMapping("/api")
    public ResponseEntity<Object> AddIt(@RequestBody Student stu){
        try{
            System.out.println(stu);
         SerObj.save(stu);
         return new ResponseEntity<>(stu, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api")
    public Student UpdateStudent(@RequestBody Student stu)
    {

         return SerObj.update(stu);
    }

}
