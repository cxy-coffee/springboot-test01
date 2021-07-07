package com.abc.springboot.repository;

import com.abc.springboot.BaseTest;
import com.abc.springboot.entity.Student;
import com.abc.springboot.entity.StudentScore;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryTest extends BaseTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Transactional
    public void test() throws Exception{
        //studentRepository.deleteAll();
        studentRepository.save(new Student(21,"张三","计算机"));
        studentRepository.save(new Student(23,"李四","计算机"));
        studentRepository.save(new Student(26,"王五","测绘"));
        studentRepository.save(new Student(29,"赵六","遥感"));
        Assert.assertEquals(12,studentRepository.findAll().size());
    }

    @Test
    @Transactional
    public void testPage() throws Exception{

        //PageRequest.of方法返回Pageable对象。
        //参数page是页数，从0开始。size是每页数量。Sort类型对象是根据字段排序，先排序，再分页查询。
        //Sort.by方法返回Sort类型对象。
        Page<Student> page = studentRepository.findAll(PageRequest.of(1,3,Sort.by(Sort.Direction.DESC,"studentId")));

        //将分页查询得到的页转成List
        List<Student> queryStudents = page.getContent();

        List<Student> queryStudentsByDept = studentRepository.findAllByStudentDept("计算机",PageRequest.of(0,2,Sort.by(Sort.Direction.ASC,"studentId"))).getContent();

        List<Student> actualStudents = new ArrayList<>();
        actualStudents.add(new Student(5,"张四","经管"));
        actualStudents.add(new Student(4,"赵六","遥感"));
        actualStudents.add(new Student(3,"王五","测绘"));

        List<Student> actualStudentsByDept = new ArrayList<>();
        actualStudentsByDept.add(new Student(1,"张三","计算机"));
        actualStudentsByDept.add(new Student(2,"李四","计算机"));

        Assert.assertEquals(queryStudents,actualStudents);
        Assert.assertEquals(queryStudentsByDept,actualStudentsByDept);
    }

    @Test
    @Transactional
    public void testLimit() throws Exception{
        List<Student> students = studentRepository.findFirst2ByStudentDept("计算机");

        List<Student> actualStudentsByDept = new ArrayList<>();
        actualStudentsByDept.add(new Student(1,"张三","计算机"));
        actualStudentsByDept.add(new Student(2,"李四","计算机"));

        Assert.assertEquals(students,actualStudentsByDept);
    }

    @Test
    @Transactional
    public void testSQL() throws Exception{
        Student student = studentRepository.myFindById(1);

        Assert.assertEquals(student,new Student(1,"张三","计算机"));
    }

    @Test
    @Transactional
    public void testMutiSelect() throws Exception{
        int score = studentRepository.myFindScoreById(1,7);
        Assert.assertEquals(score,99);

        StudentScore studentScore = studentRepository.myFindScoreByIdResult(1,7);
        Assert.assertEquals(studentScore.getScore(),99);
        Assert.assertEquals(studentScore.getCourseId(),7);
        Assert.assertEquals(studentScore.getStudentId(),1);
        Assert.assertEquals(studentScore.getStudentDept(),"计算机");
        Assert.assertEquals(studentScore.getStudentName(),"张三");
    }
}