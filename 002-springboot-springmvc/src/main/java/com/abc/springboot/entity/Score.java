package com.abc.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Score implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    private int studentId;
    @Column
    private int score;
    @Id
    private int courseId;

    public Score() {
    }

    public Score(int studentId, int score, int courseId) {
        this.studentId = studentId;
        this.score = score;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Score{" +
                "studentId=" + studentId +
                ", score=" + score +
                ", courseId=" + courseId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return studentId == score1.studentId &&
                score == score1.score &&
                courseId == score1.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, score, courseId);
    }
}
