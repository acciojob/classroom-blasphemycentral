package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository
{
    Map<String, Student> studentDB;
    Map<String, Teacher> teacherDB;
    Map<String, List<String>> teacherStudentDB;

    public StudentRepository()
    {
        this.studentDB = new HashMap<>();
        this.teacherDB = new HashMap<>();
        this.teacherStudentDB = new HashMap<>();
    }

    public void addStudent(Student student)
    {
        studentDB.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher)
    {
        teacherDB.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher)
    {
        if(studentDB.containsKey(student) && teacherDB.containsKey(teacher))
        {
            List<String> list = new ArrayList<>();
            if(teacherStudentDB.containsKey(teacher))
            {
                list = teacherStudentDB.get(teacher);
            }
            list.add(student);
            teacherStudentDB.put(teacher, list);
        }
    }

    public Student getStudentByName(String name)
    {
        return studentDB.get(name);
    }

    public Teacher getTeacherByName(String name)
    {
        return teacherDB.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher)
    {
        return teacherStudentDB.get(teacher);
    }

    public List<String> getAllStudents()
    {
        return new ArrayList<>(studentDB.keySet());
    }

    public void deleteTeacherByName(String teacher)
    {
        teacherDB.remove(teacher);
        teacherStudentDB.remove(teacher);
    }

    public void deleteAllTeacher()
    {
        teacherDB.clear();
        teacherStudentDB.clear();
    }
}
