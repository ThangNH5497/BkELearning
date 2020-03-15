package bk.elearning.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import bk.elearning.entity.relationship.StudentCourse;

@Entity
public class Student extends User{

	@OneToMany(mappedBy="student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentCourse> studentCourses;
}
