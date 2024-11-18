import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class account {
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
	public account() {
		f.setSize(500, 200);
		f.setLayout(new GridLayout(7, 2));
		Container cont = f.getContentPane();
		cont.add(lblEmail);
		cont.add(tfEmail);
		cont.add(lblPass);
		cont.add(tfPass);
		grGender.add(rdoMale);
		grGender.add(rdoFemale);
		cont.add(rdoMale);
		cont.add(rdoFemale);
		cont.add(lblQualication);
		cont.add(cboQualification);
		cont.add(cbComputer);
		cont.add(cbEconomic);
		
		cont.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			
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
					int record = dbAccess.execute("Insert into account(email,pass,gender,qualification,studyfield) values('"+tfEmail.getText()+"','"+tfPass.getText()+"','"+gender+"','"+qualification+"','"+field+"')");
					if(record>0) JOptionPane.showMessageDialog(null, "Add student successfully");
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
						
				
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
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		account acc = new account();
	}

}
