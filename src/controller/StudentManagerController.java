package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Action;

import model.Province;
import model.Student;
import view.StudentManagerView;

public class StudentManagerController implements Action {
	
	private StudentManagerView view;
	
	public StudentManagerController(StudentManagerView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand().trim();
		System.out.println(cm);
		if (cm.equals("Close list")) {
			this.view.clearForm();
			this.view.studentManager.clearFillterList();
		} else if (cm.equals("OK")) {
			Student student = this.view.createStudentFromForm();
			if (student!=null) {
				this.view.addOrUpdateStudent(student);				
			}
		} else if (cm.equals("Update")) {
			this.view.jtf_info_id.setEnabled(false);
			Student student = this.view.getSelectedStudent();
			this.view.setFormInfo(student);
		} else if (cm.equals("Delete")) {
			this.view.removeSelectedStudent();
		} else if (cm.equals("Fillter")) {
			this.view.fillterTable();
		} else if (cm.equals("Cancel")) {
			this.view.studentManager.clearFillterList();
			this.view.reRenderStudentList(this.view.studentManager.getStudentList());
		} else if (cm.equals("Save")) {
			this.view.saveStudentList();
		} else if (cm.equals("Open")) {
			this.view.openStudentList();
		} else if (cm.equals("About me")) {
			this.view.showAbout();
		} else if (cm.equals("Exit")) {
			this.view.closeApp();
		}
	}
	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
