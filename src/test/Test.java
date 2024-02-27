package test;

import javax.swing.UIManager;

import view.StudentManagerView;

public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new StudentManagerView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
