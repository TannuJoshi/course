package com.springrest.springrest.controller;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class MyController {

@Autowired
private CourseService courseService;
    @GetMapping("/courses/")

        public ResponseEntity <List <Course>> getCourses()
    {
     List<Course> list=courseService.getCourses();
     if(list.size()<=0){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
     }
        return ResponseEntity.of(Optional.of(list));


    }
    @GetMapping("/courses/{courseId}")

    public ResponseEntity<Course> getCourse(@PathVariable Long courseId)
    {
        Course course=courseService.getCourse(courseId);
        if (course==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(course));
    }
    @PostMapping("/courses")

    public ResponseEntity<Course> addCourse(@RequestBody Course course)
    {
        Course c=null;
        try{
            c=this.courseService.addCourse(course);
            System.out.println(course);
            return ResponseEntity.of(Optional.of(c));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
    @PutMapping("/courses/{courseId}")

      public ResponseEntity<Course> updateCourse(@RequestBody Course course,@PathVariable("courseId")int courseId)  {
        try{
            this.courseService.updateCourse(course, courseId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId){
        try{
            this.courseService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    }

