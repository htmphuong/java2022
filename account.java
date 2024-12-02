import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.synth.SynthButtonUI;
import javax.swing.table.DefaultTableModel;

public class account extends JFrame {
	JFrame f = new JFrame();
	
	JLabel lblEmail = new JLabel("Email");
	JTextField tfEmail = new JTextField(30);
	JLabel lblPass = new JLabel("Password");
	JPasswordField tfPass = new JPasswordField(30);
	JButton btnRegister = new JButton("Register");
	JRadioButton rdoMale = new JRadioButton("Male");
	JRadioButton rdoFemale = new JRadioButton("Female");
	JLabel lblQualication = new JLabel("Qualification");
	String qual[]= {"Doctor", "Master","Bachelor","Undergrade"};
	JComboBox cboQualification = new JComboBox(qual);
	JCheckBox cbComputer = new JCheckBox("Computer Science");
	JCheckBox cbEconomic	= new JCheckBox("Economic");
	
	JButton btnEdit	= new JButton("Edit");
	JButton btnDelete = new JButton("Delete");
	ButtonGroup grGender = new ButtonGroup();
	JButton btnAll = new JButton("Display");
	
	//display JTable
	JTable tbAccount;
	//title of table
	Object[] title = {"Email","Password","Gender","Qualification","Study Field"};
	//luu tru cau truc cua bang
	DefaultTableModel model = new DefaultTableModel(title,0);
	
	
	public account() {
		f.setSize(500, 200);
		f.setLayout(new GridLayout(8, 2));
		Container cont = f.getContentPane();
		cont.add(lblEmail);
		cont.add(tfEmail);
		cont.add(lblPass);
		cont.add(tfPass);
		cont.add(rdoMale);
		cont.add(rdoFemale);
		grGender.add(rdoMale);
		grGender.add(rdoFemale);
		cont.add(btnRegister);
		cont.add(lblQualication);
		cont.add(cboQualification);
		cont.add(cbComputer);
		cont.add(cbEconomic);
		cont.add(btnRegister);
		
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBAccess dbAccess = new DBAccess();
				dbAccess.connect();
				String gender="";
				if(rdoMale.isSelected()) gender = rdoMale.getText();
				if(rdoFemale.isSelected()) gender = rdoFemale.getText();
				String qualification = cboQualification.getSelectedItem().toString();
				String study="";
				if(cbComputer.isSelected()) study = study.concat(cbComputer.getText())+";";
				if(cbEconomic.isSelected()) study = study.concat(cbEconomic.getText());
				int record = dbAccess.execute("Insert into account values('"+tfEmail.getText()+"','"+tfPass.getText()+"','"+gender+"','"+qualification+"','"+study+"')");
				if(record>0) JOptionPane.showMessageDialog(null, "Add student successfully");
				
			}
		});
		cont.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					DBAccess dbAccess = new DBAccess();
					dbAccess.connect();
					String gender="";
					if(rdoFemale.isSelected()) gender= rdoFemale.getText();
					if(rdoMale.isSelected()) gender= rdoMale.getText();
					String qualification = cboQualification.getSelectedItem().toString();
					String field ="";
					if(cbComputer.isSelected()) field = field.concat(cbComputer.getText()) +";";
					if(cbEconomic.isSelected()) field = field.concat(cbEconomic.getText());
					int record = dbAccess.execute("Update account set pass='"+tfPass.getText()+"',gender ='"+gender+"',qualification='"+qualification+"',studyfield='"+field+"' where email ='"+tfEmail.getText()+"'");
					if(record>0) JOptionPane.showMessageDialog(null, "Add student successfully");
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		cont.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					DBAccess dbAccess = new DBAccess();
					dbAccess.connect();
					
					int record = dbAccess.execute("Delete from account where email ='"+tfEmail.getText()+"'");
					if(record>0) JOptionPane.showMessageDialog(null, "Add student successfully");
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		cont.add(btnAll);
		btnAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					DBAccess dbAccess = new DBAccess();
					ResultSet rs = dbAccess.query("select * from account");
					while(rs.next()) {
						String email = rs.getString("email");
						String pass = rs.getString("pass");
						String gender = rs.getString("gender");
						String qualification = rs.getString("qualification");
						String field = rs.getString("studyfield");
						Object row[]= new Object[] {email,pass,gender,qualification,field};
						model.addRow(row);						
					}
					tbAccount.setModel(model);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		tbAccount = new JTable();
		tbAccount.setSize(150, 150);
		/*
		ListSelectionModel cell = tbAccount.getSelectionModel();
		cell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cell.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				String selectedData = null;

			    int[] selectedRow = tbAccount.getSelectedRows();
			    int[] selectedColumns = tbAccount.getSelectedColumns();
			    for (int i = 0; i < selectedRow.length; i++) {			      
			          selectedData = (String) tbAccount.getName();			 
			      }
			      System.out.println("Selected: " + selectedData);
			}
		});*/
		tbAccount.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				cbComputer.setSelected(false);
				cbEconomic.setSelected(false);
				tfEmail.setText(tbAccount.getValueAt(tbAccount.getSelectedRow(), 0).toString());
				tfPass.setText(tbAccount.getValueAt(tbAccount.getSelectedRow(), 1).toString());
				String gender = tbAccount.getValueAt(tbAccount.getSelectedRow(), 2).toString();
				if(gender.equals("Female")) rdoFemale.setSelected(true);
				if(gender.equals("Male")) rdoMale.setSelected(true);
				cboQualification.setSelectedItem(tbAccount.getValueAt(tbAccount.getSelectedRow(), 3).toString());
				if(tbAccount.getValueAt(tbAccount.getSelectedRow(), 4).toString().contains("Economic")) cbEconomic.setSelected(true);
				if(tbAccount.getValueAt(tbAccount.getSelectedRow(), 4).toString().contains("Computer")) cbComputer.setSelected(true);
			}
		});
		cont.add(tbAccount);		
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		account acc = new account();
	}

}
