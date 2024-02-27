package model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class StudentManager implements Serializable {
	private ArrayList<Student> studentList;
	private ArrayList<Student> fillStudentsList;
	private File file;
	public StudentManager() {
		this.studentList = new ArrayList<Student>();
		 this.fillStudentsList = new ArrayList<Student>();
		 this.file = new File("resources/data/studentlist.list");
	}
	public StudentManager(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	
	public ArrayList<Student> getFillStudentsList() {
		return fillStudentsList;
	}
	public void setFillStudentsList(ArrayList<Student> fillStudentsList) {
		this.fillStudentsList = fillStudentsList;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "StudentManager [studentList=" + studentList + "]";
	}
	
	public boolean hasStudent(Student new_student) {
		for (Student student : studentList) {
			if (student.getStudentId() == new_student.getStudentId()) {
				return true;
			}
		}
		return false;
	}
	
	public void insert(Student student) {
		this.studentList.add(student);
	}
	public void delete(Student student) {
		this.studentList.remove(student);
	}
	public void update(Student new_student) {
		for (Student student : this.studentList) {
			if (student.getStudentId() == new_student.getStudentId()&&this.hasStudent(new_student)) {
				this.studentList.remove(student);
				this.studentList.add(new_student);
			}
		}
	}
	public void sortById() {
		Collections.sort(this.getStudentList());
	}
	public ArrayList<Student> createFillterList(String fill_StudentId, Province placeFill) {
	    if (fill_StudentId.equals("")) {
			for (Student student : this.getStudentList()) {
				if (student.getProvince().getProvinceName().equals(placeFill.getProvinceName())) {
					fillStudentsList.add(student);
				}
			}
		} else if (placeFill == null) {
			for (Student student : this.getStudentList()) {
				if ((student.getStudentId()+"").equals(fill_StudentId)) {
					fillStudentsList.add(student);
				}
			}
		} else {
			for (Student student : this.getStudentList()) {
				if (student.getProvince().getProvinceName().equals(placeFill.getProvinceName())
						&& (student.getStudentId()+"").equals(fill_StudentId)) {
					fillStudentsList.add(student);
				}
			}
		}
	    return fillStudentsList;
	}
	public void clearFillterList() {
		this.fillStudentsList.clear();
	}
}
