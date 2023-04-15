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
            if (teacherStudentDB.containsKey(teacher))
            {
                List<String> list = teacherStudentDB.get(teacher);
                list.add(student);
                teacherStudentDB.put(teacher, list);
            }
            else
            {
                List<String> list = new ArrayList<>();
                list.add(student);
                teacherStudentDB.put(teacher, list);
            }
            teacherDB.get(teacher).setNumberOfStudents(teacherStudentDB.get(teacher).size());
        }
    }

    public Student getStudentByName(String name)
    {
        return studentDB.getOrDefault(name, null);
    }

    public Teacher getTeacherByName(String name)
    {
        return teacherDB.getOrDefault(name, null);
    }

    public List<String> getStudentsByTeacherName(String teacher)
    {
        if(teacherStudentDB.containsKey(teacher))
        return teacherStudentDB.get(teacher);
        else return new ArrayList<>();
    }

    public List<String> getAllStudents()
    {
        return new ArrayList<>(studentDB.keySet());
    }

    public void deleteTeacherByName(String teacher)
    {
        if(teacherDB.containsKey(teacher))
            teacherDB.remove(teacher);
        if(teacherStudentDB.containsKey(teacher))
        {
            for(String student : teacherStudentDB.get(teacher))
            {
                if(studentDB.containsKey(student))
                {
                    studentDB.remove(student);
                }
            }
        }
        teacherStudentDB.remove(teacher);
    }

    public void deleteAllTeacher()
    {
        for(String teacher : teacherStudentDB.keySet())
        {
            if(teacherDB.containsKey(teacher)) teacherDB.remove(teacher);
            for(String student : teacherStudentDB.get(teacher))
            {
                if(studentDB.containsKey(student))
                {
                    studentDB.remove(student);
                }
            }
            teacherStudentDB.remove(teacher);
        }
    }
}
