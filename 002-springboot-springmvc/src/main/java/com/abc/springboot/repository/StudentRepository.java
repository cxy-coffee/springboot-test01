package com.abc.springboot.repository;

import com.abc.springboot.entity.Student;
import com.abc.springboot.entity.StudentScore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByStudentId(int studentId);
    List<Student> findAllByStudentName(String studentName);
    List<Student> findAllByStudentDept(String studentDept);
    Page<Student> findAll(Pageable pageable);
    Page<Student> findAllByStudentDept(String studentDept,Pageable pageable);
    List<Student> findFirst2ByStudentDept(String studentDept);

    @Query("select s from Student s where s.studentId = ?1")
    Student myFindById(int studentId);

    @Query("select sc.score as score from Student s join Score sc on s.studentId = sc.studentId where s.studentId = ?1 and sc.courseId = ?2")
    int myFindScoreById(int studentId,int courseId);

    @Query("select s.studentId as studentId,s.studentDept as studentDept,s.studentName as studentName,sc.courseId as courseId,sc.score as score from Student s join Score sc on s.studentId = sc.studentId where s.studentId = ?1 and sc.courseId = ?2")
    StudentScore myFindScoreByIdResult(int studentId,int courseId);

}
