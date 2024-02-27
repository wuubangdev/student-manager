package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.sun.tools.javac.Main;

import controller.StudentManagerController;
import model.Province;
import model.Student;
import model.StudentManager;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class StudentManagerView extends JFrame {

	private Province province;
	public Student student;
	public StudentManager studentManager;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable table;
	public JTextField jtf_info_id;
	public JTextField jtf_info_name;
	public JTextField jtf_info_date;
	public JTextField jtf_info_gpa1;
	public JTextField jtf_info_gpa2;
	public JTextField jtf_info_gpa3;
	public JComboBox cbb_info_birthPlace;
	public JComboBox cbb_fill_birthPlace;
	public ButtonGroup jrb_info_sexGroup;
	private JRadioButton jrb_info_female;
	private JRadioButton jrb_info_male;
	private ArrayList<Province> proList;
	private JTextField jtf_fill_studentId;
	private JButton btn_fill_fillter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManagerView frame = new StudentManagerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentManagerView() {
		student = new Student();
		studentManager = new StudentManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Student Manager");
		setResizable(false);
		setBounds(100, 100, 1042, 673);
		Image icon_app = Toolkit.getDefaultToolkit().createImage("resources/data/icon/studentManager.png");
		this.setIconImage(icon_app);
		
		Action action = new StudentManagerController(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.setBounds(0, 0, this.getWidth(), 25);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnFile);
		
		ImageIcon icon_open = new ImageIcon("resources/data/icon/open.png");
		JMenuItem mni_open = new JMenuItem(" Open", icon_open);
		mni_open.addActionListener(action);
		mni_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		mni_open.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnFile.add(mni_open);
		
		ImageIcon icon_save = new ImageIcon("resources/data/icon/save.png");
		JMenuItem mni_save = new JMenuItem(" Save", icon_save);
		mni_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mni_save.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mni_save.addActionListener(action);
		mnFile.add(mni_save);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		ImageIcon icon_exit = new ImageIcon("resources/data/icon/close.png");
		JMenuItem mni_exit = new JMenuItem(" Exit", icon_exit);
		mni_exit.addActionListener(action);
		mni_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		mni_exit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnFile.add(mni_exit);
		
		JMenu mnAbout = new JMenu("About");
		mnAbout.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnAbout);
		
		ImageIcon icon_aboutMe = new ImageIcon("resources/data/icon/aboutMe.png");
		JMenuItem mni_aboutMe = new JMenuItem(" About me", icon_aboutMe);
		mni_aboutMe.addActionListener(action);
		mni_aboutMe.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnAbout.add(mni_aboutMe);
		
		JLabel jlb_studentFillter = new JLabel("Student List");
		jlb_studentFillter.setFont(new Font("Tahoma", Font.BOLD, 18));
		jlb_studentFillter.setBounds(10, 107, 170, 25);
		contentPane.add(jlb_studentFillter);
		
		JPanel panelFillter = new JPanel();
		panelFillter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelFillter.setBounds(10, 62, 1012, 45);
		contentPane.add(panelFillter);
		panelFillter.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));
		
		JLabel jlb_fill_birthplace = new JLabel("Birth Place");
		jlb_fill_birthplace.setForeground(new Color(128, 0, 0));
		jlb_fill_birthplace.setHorizontalAlignment(SwingConstants.CENTER);
		jlb_fill_birthplace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelFillter.add(jlb_fill_birthplace);
		
		cbb_fill_birthPlace = new JComboBox();
		proList = province.getProvinceList();
		cbb_fill_birthPlace.addItem("");
		for (Province province : proList) {
			cbb_fill_birthPlace.addItem(province.getProvinceName());
		}
		cbb_fill_birthPlace.setMaximumRowCount(8);
		cbb_fill_birthPlace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelFillter.add(cbb_fill_birthPlace);
		
		JLabel jlb_fill_studentId = new JLabel("Student ID");
		jlb_fill_studentId.setForeground(new Color(128, 0, 0));
		jlb_fill_studentId.setHorizontalAlignment(SwingConstants.CENTER);
		jlb_fill_studentId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelFillter.add(jlb_fill_studentId);
		
		jtf_fill_studentId = new JTextField();
		jtf_fill_studentId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jtf_fill_studentId.setColumns(10);
		panelFillter.add(jtf_fill_studentId);
		
		btn_fill_fillter = new JButton("Fillter");
		btn_fill_fillter.addActionListener(action);
		btn_fill_fillter.setForeground(new Color(0, 0, 128));
		btn_fill_fillter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelFillter.add(btn_fill_fillter);
		
		JLabel jlb_studentList = new JLabel("Student Fillter");
		jlb_studentList.setFont(new Font("Tahoma", Font.BOLD, 18));
		jlb_studentList.setBounds(10, 35, 150, 25);
		contentPane.add(jlb_studentList);
		
		JPanel panelList = new JPanel();
		panelList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelList.setBounds(10, 131, 1012, 246);
		contentPane.add(panelList);
		panelList.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, panelList.getWidth()-20, panelList.getHeight()-20);
		panelList.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"ID", "Name", "Place", "Date", "Sex", "GPA1", "GPA2", "GPA3"
			}
		) {
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel jlb_studentInfo = new JLabel("Student infomation");
		jlb_studentInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		jlb_studentInfo.setBounds(10, 386, 201, 25);
		contentPane.add(jlb_studentInfo);
		
		JPanel panel_info_id = new JPanel();
		panel_info_id.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_info_id.setBounds(10, 410, 1006, 161);
		contentPane.add(panel_info_id);
		panel_info_id.setLayout(null);
		
		JLabel jlb_info_id = new JLabel("ID");
		jlb_info_id.setBounds(50, 11, 83, 20);
		jlb_info_id.setHorizontalAlignment(SwingConstants.LEFT);
		jlb_info_id.setForeground(new Color(0, 0, 255));
		jlb_info_id.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_info_id.add(jlb_info_id);
		
		JLabel jlb_info_name = new JLabel("Name");
		jlb_info_name.setHorizontalAlignment(SwingConstants.LEFT);
		jlb_info_name.setForeground(Color.BLUE);
		jlb_info_name.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jlb_info_name.setBounds(50, 51, 83, 20);
		panel_info_id.add(jlb_info_name);
		
		JLabel jlb_info_birthPlace = new JLabel("Birth Place");
		jlb_info_birthPlace.setHorizontalAlignment(SwingConstants.LEFT);
		jlb_info_birthPlace.setForeground(Color.BLUE);
		jlb_info_birthPlace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jlb_info_birthPlace.setBounds(50, 91, 83, 20);
		panel_info_id.add(jlb_info_birthPlace);
		
		JLabel jlb_info_date = new JLabel("Date");
		jlb_info_date.setHorizontalAlignment(SwingConstants.LEFT);
		jlb_info_date.setForeground(Color.BLUE);
		jlb_info_date.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jlb_info_date.setBounds(50, 131, 83, 20);
		panel_info_id.add(jlb_info_date);
		
		JLabel jlb_info_gpa1 = new JLabel("GPA 1");
		jlb_info_gpa1.setHorizontalAlignment(SwingConstants.LEFT);
		jlb_info_gpa1.setForeground(Color.BLUE);
		jlb_info_gpa1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jlb_info_gpa1.setBounds(535, 51, 83, 20);
		jlb_info_gpa1.setToolTipText("Gpa input must be about 0 to 10.");
		panel_info_id.add(jlb_info_gpa1);
		
		JLabel jlb_info_gpa2 = new JLabel("GPA 2");
		jlb_info_gpa2.setHorizontalAlignment(SwingConstants.LEFT);
		jlb_info_gpa2.setForeground(Color.BLUE);
		jlb_info_gpa2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jlb_info_gpa2.setBounds(535, 91, 83, 20);
		jlb_info_gpa2.setToolTipText("Gpa input must be about 0 to 10.");
		panel_info_id.add(jlb_info_gpa2);
		
		JLabel jlb_info_gpa3 = new JLabel("GPA 3");
		jlb_info_gpa3.setHorizontalAlignment(SwingConstants.LEFT);
		jlb_info_gpa3.setForeground(Color.BLUE);
		jlb_info_gpa3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jlb_info_gpa3.setBounds(535, 131, 83, 20);
		jlb_info_gpa3.setToolTipText("Gpa input must be about 0 to 10.");
		panel_info_id.add(jlb_info_gpa3);
		
		JLabel jlb_info_sex = new JLabel("Sex");
		jlb_info_sex.setHorizontalAlignment(SwingConstants.LEFT);
		jlb_info_sex.setForeground(Color.BLUE);
		jlb_info_sex.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jlb_info_sex.setBounds(535, 17, 83, 20);
		panel_info_id.add(jlb_info_sex);
		
		jtf_info_id = new JTextField();
		jtf_info_id.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jtf_info_id.setColumns(10);
		jtf_info_id.setBounds(175, 9, 221, 26);
		jtf_info_id.setToolTipText("int value.");
		panel_info_id.add(jtf_info_id);
		
		jtf_info_name = new JTextField();
		jtf_info_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jtf_info_name.setColumns(10);
		jtf_info_name.setBounds(175, 46, 221, 26);
		jtf_info_name.setToolTipText("String value.");
		panel_info_id.add(jtf_info_name);
		
		cbb_info_birthPlace = new JComboBox();
		cbb_info_birthPlace.addItem("");
		for (Province province : proList) {
			cbb_info_birthPlace.addItem(province.getProvinceName());
		}
		cbb_info_birthPlace.setMaximumRowCount(8);
		cbb_info_birthPlace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbb_info_birthPlace.setBounds(175, 89, 221, 26);
		panel_info_id.add(cbb_info_birthPlace);
		
		jtf_info_date = new JTextField();
		jtf_info_date.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jtf_info_date.setColumns(10);
		jtf_info_date.setBounds(175, 125, 221, 26);
		jtf_info_date.setToolTipText("Date format is dd/MM/yyyy");
		panel_info_id.add(jtf_info_date);
		
		jtf_info_gpa1 = new JTextField();
		jtf_info_gpa1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jtf_info_gpa1.setColumns(10);
		jtf_info_gpa1.setBounds(606, 46, 221, 26);
		jtf_info_gpa1.setToolTipText("Gpa input must be about 0 to 10.");
		panel_info_id.add(jtf_info_gpa1);
		
		jtf_info_gpa2 = new JTextField();
		jtf_info_gpa2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jtf_info_gpa2.setColumns(10);
		jtf_info_gpa2.setBounds(606, 89, 221, 26);
		jtf_info_gpa2.setToolTipText("Gpa input must be about 0 to 10.");
		panel_info_id.add(jtf_info_gpa2);
		
		jtf_info_gpa3 = new JTextField();
		jtf_info_gpa3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jtf_info_gpa3.setColumns(10);
		jtf_info_gpa3.setBounds(606, 125, 221, 26);
		jtf_info_gpa3.setToolTipText("Gpa input must be about 0 to 10.");
		panel_info_id.add(jtf_info_gpa3);
		
		jrb_info_sexGroup = new ButtonGroup();
		jrb_info_male = new JRadioButton("Male");
		jrb_info_male.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jrb_info_male.setBounds(606, 13, 109, 23);
		panel_info_id.add(jrb_info_male);
		
		jrb_info_female = new JRadioButton("Female");
		jrb_info_female.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jrb_info_female.setBounds(717, 13, 109, 23);
		panel_info_id.add(jrb_info_female);
		
		jrb_info_sexGroup.add(jrb_info_male);
		jrb_info_sexGroup.add(jrb_info_female);
		
		JButton btn_insert = new JButton("Close list");
		btn_insert.setBackground(UIManager.getColor("Button.darkShadow"));
		btn_insert.setForeground(new Color(128, 128, 128));
		btn_insert.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_insert.setBounds(20, 582, 140, 30);
		btn_insert.addActionListener(action);
		contentPane.add(btn_insert);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.setForeground(new Color(128, 128, 128));
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_delete.setBackground(UIManager.getColor("Button.darkShadow"));
		btn_delete.setBounds(225, 582, 140, 30);
		btn_delete.addActionListener(action);
		contentPane.add(btn_delete);
		
		JButton btn_edit = new JButton("Update");
		btn_edit.setForeground(new Color(128, 128, 128));
		btn_edit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_edit.setBackground(UIManager.getColor("Button.darkShadow"));
		btn_edit.setBounds(447, 582, 140, 30);
		btn_edit.addActionListener(action);
		contentPane.add(btn_edit);
		
		JButton btn_ok = new JButton("OK");
		btn_ok.setForeground(new Color(255, 69, 0));
		btn_ok.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_ok.setBackground(UIManager.getColor("Button.darkShadow"));
		btn_ok.setBounds(667, 582, 140, 30);
		btn_ok.addActionListener(action);
		contentPane.add(btn_ok);
		
		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.setForeground(new Color(220, 20, 60));
		btn_cancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_cancel.setBackground(UIManager.getColor("Button.darkShadow"));
		btn_cancel.setBounds(861, 582, 140, 30);
		btn_cancel.addActionListener(action);
		contentPane.add(btn_cancel);
		
		this.setVisible(true);
	}
	public void clearForm() {
		jtf_info_id.setEnabled(true);
		jtf_info_id.setText("");
		jtf_info_name.setText("");
		jtf_info_date.setText("");
		jtf_info_gpa1.setText("");
		jtf_info_gpa2.setText("");
		jtf_info_gpa3.setText("");
		cbb_info_birthPlace.setSelectedIndex(-1);
		jrb_info_sexGroup.clearSelection();
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		model_table.setRowCount(0);
	}
	public Date stringToDate(String string) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return sdf.parse(string);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, "Date error, please check!!!");
			return null;
		}
	}
	public String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
	public void reRenderStudentList(ArrayList<Student> stdList) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		model_table.setRowCount(0);
		Collections.sort(stdList, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return Integer.compare(o1.getStudentId(), o2.getStudentId());
			}
		});
		for (Student student : stdList) {
			model_table.addRow(new Object[] {student.getStudentId(),
					student.getStudentName(),
					student.getProvince().getProvinceName(),
					this.dateToString(student.getBirthDay()),
					(student.isSex()?"Male":"Femal"),
					student.getGpa_1(),
					student.getGpa_2(),
					student.getGpa_3()} );
		}
	}
	public void addStudentToTable(Student student) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
			model_table.addRow(new Object[] {student.getStudentId(),
					student.getStudentName(),
					student.getProvince().getProvinceName(),
					this.dateToString(student.getBirthDay()),
					(student.isSex()?"Male":"Femal"),
					student.getGpa_1(),
					student.getGpa_2(),
					student.getGpa_3()} );
	}
	public void updateRow(Student student) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int row_count = model_table.getRowCount();
		for (int i = 0; i < row_count; i++) {
			if (model_table.getValueAt(i, 0).equals(student.getStudentId())) {
				model_table.setValueAt(student.getStudentName(), i, 1);
				model_table.setValueAt(student.getProvince().getProvinceName(), i, 2);
				model_table.setValueAt(dateToString(student.getBirthDay()), i, 3);
				model_table.setValueAt(student.isSex()?"Male":"Female", i, 4);
				model_table.setValueAt(student.getGpa_1(), i, 5);
				model_table.setValueAt(student.getGpa_2(), i, 6);
				model_table.setValueAt(student.getGpa_3(), i, 7);
			}
		}
		jtf_info_id.setEnabled(true);
	}
	public void addOrUpdateStudent(Student st) {
		if (!this.studentManager.hasStudent(st)) {
			this.studentManager.insert(st);
			this.addStudentToTable(st);
		} else {
			JOptionPane.showMessageDialog(this, "Student, will upodate :3");
			this.studentManager.update(st);
			this.updateRow(st);
		}
		
	}
	public Student getSelectedStudent() {
		int i_row_selected = table.getSelectedRow();
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		Student student;
		int studentId = Integer.valueOf(model_table.getValueAt(i_row_selected, 0) + "");
		String studentName = model_table.getValueAt(i_row_selected, 1) + "";
		Province birthPlace = Province.getPlaceByName(model_table.getValueAt(i_row_selected, 2) + "");
		Date birthDay = this.stringToDate(model_table.getValueAt(i_row_selected, 3) + "");
		boolean sex;
		if (model_table.getValueAt(i_row_selected, 4).equals("Male")) {
			sex = true;
		} else {
			sex = false;
		}
		Float gpa_1 = Float.valueOf(model_table.getValueAt(i_row_selected, 5) + "");
		Float gpa_2 = Float.valueOf(model_table.getValueAt(i_row_selected, 6) + "");
		Float gpa_3 = Float.valueOf(model_table.getValueAt(i_row_selected, 7) + "");
		student = new Student(studentId, studentName, birthDay, sex, birthPlace, gpa_1, gpa_2, gpa_3);
		return student;
	}
	
	public void setFormInfo(Student student) {
		jtf_info_id.setText(student.getStudentId()+"");
		jtf_info_name.setText(student.getStudentName());
		cbb_info_birthPlace.setSelectedItem(student.getProvince().getProvinceName());
		jtf_info_date.setText(this.dateToString(student.getBirthDay()));
		jrb_info_male.setSelected(student.isSex());
		jrb_info_female.setSelected(!student.isSex());
		jtf_info_gpa1.setText(student.getGpa_1()+"");
		jtf_info_gpa2.setText(student.getGpa_2()+"");
		jtf_info_gpa3.setText(student.getGpa_3()+"");
	}
	public boolean checkValidMark(Float f_value) {
		if (f_value<=10&&f_value>=0 ) return true;
		return false;
	}
	public Student createStudentFromForm() {
		Student st;
		try {
			int studentID = Integer.valueOf(jtf_info_id.getText()+"");
			String studentName = jtf_info_name.getText();
			String datetring = jtf_info_date.getText();
			Date birthDate = this.stringToDate(datetring);
			boolean sex = jrb_info_male.isSelected();
			int placeId = cbb_info_birthPlace.getSelectedIndex();
			Province province = Province.getPlacebyID(placeId - 1);
			Float gpa_1 = Float.valueOf(jtf_info_gpa1.getText());
			Float gpa_2 = Float.valueOf(jtf_info_gpa2.getText());
			Float gpa_3 = Float.valueOf(jtf_info_gpa3.getText());
			if (this.checkValidMark(gpa_1)&& this.checkValidMark(gpa_2)&&this.checkValidMark(gpa_3)) {
				st = new Student(studentID, studentName, birthDate, sex, province, gpa_1, gpa_2, gpa_3);				
			} else {
				JOptionPane.showMessageDialog(this, "Data erorr, pls check!!!");				
				st = null;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Data erorr, pls check!!!");
			return null;
		}
		return st;
	}
	public void removeSelectedStudent() {
		Student student = this.getSelectedStudent();
		int isDelete = JOptionPane.showConfirmDialog(this, "You want delete?");
		if (isDelete == JOptionPane.OK_OPTION) {
			this.studentManager.delete(student);
			DefaultTableModel model_table = (DefaultTableModel) table.getModel();
			model_table.removeRow(table.getSelectedRow());			
		}
	}
	public void fillterTable() {
		int placeFillId = cbb_fill_birthPlace.getSelectedIndex()-1;
		Province placeFill;
		if (placeFillId < 0 ) {
			placeFill = null;
		} else {
			placeFill = Province.getPlacebyID(placeFillId);
		}
	    String fill_StudentId = jtf_fill_studentId.getText();
	    ArrayList<Student> fillList = this.studentManager.createFillterList(fill_StudentId, placeFill);
	    this.reRenderStudentList(fillList);
	    this.studentManager.clearFillterList();
	    }
	public void saveStudentList() {//File
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(this.studentManager.getFile());
		int returnValue = jfc.showSaveDialog(null);
		File selectedFile = null;
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			this.studentManager.setFile(selectedFile);
		}
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.studentManager.getFile()));
			objectOutputStream.writeObject(this.studentManager.getStudentList());
			objectOutputStream.flush();
			objectOutputStream.close();
			jfc.setCurrentDirectory(this.studentManager.getFile());
			JOptionPane.showMessageDialog(this, "File has saved at: " + selectedFile.getAbsolutePath());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Pls, cloose file path!");
		}
	}
	public void openStudentList() {//File
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(this.studentManager.getFile());
		int returnValue = jfc.showOpenDialog(null);
		File selectedFile = null;
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			this.studentManager.setFile(selectedFile);
		}
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.studentManager.getFile()));
			ArrayList<Student> stdList = (ArrayList<Student>) objectInputStream.readObject();
			this.studentManager.setStudentList(stdList);
			objectInputStream.close();
			jfc.setCurrentDirectory(this.studentManager.getFile());
			this.reRenderStudentList(this.studentManager.getStudentList());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Pls, choose the file!");
		}
	}

	public void showAbout() {
		JOptionPane.showMessageDialog(this, "This app was built by Bang(^!^)");
		
	}

	public void closeApp() {
		int choosed = JOptionPane.showConfirmDialog(this, 
													"Do you want close app?",
													"Exit option",
													JOptionPane.YES_NO_OPTION);
		if (choosed == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
