package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Student implements Comparable<Student>, Serializable{
	private int studentId;
	private String studentName;
	private Date birthDay;
	private boolean sex; 
	private Province province;
	private float gpa_1;
	private float gpa_2;
	private float gpa_3;
	
	public Student() {
	}

	public Student(int studentId, String studentName, Date birthDay, boolean sex, Province province, float gpa_1,
			float gpa_2, float gpa_3) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.birthDay = birthDay;
		this.sex = sex;
		this.province = province;
		this.gpa_1 = gpa_1;
		this.gpa_2 = gpa_2;
		this.gpa_3 = gpa_3;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public float getGpa_1() {
		return gpa_1;
	}

	public void setGpa_1(float gpa_1) {
		this.gpa_1 = gpa_1;
	}

	public float getGpa_2() {
		return gpa_2;
	}

	public void setGpa_2(float gpa_2) {
		this.gpa_2 = gpa_2;
	}

	public float getGpa_3() {
		return gpa_3;
	}

	public void setGpa_3(float gpa_3) {
		this.gpa_3 = gpa_3;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", birthDay=" + birthDay + ", sex="
				+ sex + ", province=" + province + ", gpa_1=" + gpa_1 + ", gpa_2=" + gpa_2 + ", gpa_3=" + gpa_3 + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDay, gpa_1, gpa_2, gpa_3, province, sex, studentId, studentName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(birthDay, other.birthDay)
				&& Float.floatToIntBits(gpa_1) == Float.floatToIntBits(other.gpa_1)
				&& Float.floatToIntBits(gpa_2) == Float.floatToIntBits(other.gpa_2)
				&& Float.floatToIntBits(gpa_3) == Float.floatToIntBits(other.gpa_3)
				&& Objects.equals(province, other.province) && sex == other.sex && studentId == other.studentId
				&& Objects.equals(studentName, other.studentName);
	}

	@Override
	public int compareTo(Student o) {
		return Integer.compare(this.studentId, o.studentId);
	}
}
