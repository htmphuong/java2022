import java.awt.Button;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Student {
	JFrame p = new JFrame();
	JLabel lblEmail = new JLabel("Email");
	JTextField tfEmail = new JTextField(30);
	JLabel lblPass = new JLabel("Password");
	JPasswordField tfPass = new JPasswordField(30);
	JRadioButton rdoMale = new JRadioButton("Male");
	JRadioButton rdoFemale = new JRadioButton("Female");
	ButtonGroup grGender = new ButtonGroup();
	JButton btnRegis = new JButton("Register");
	JLabel lblNation = new JLabel("National");
	String qual[]= {"Vietnam", "England","Singapore","Lao"};
	JComboBox cboNation = new JComboBox(qual);
	JCheckBox cbComputer = new JCheckBox("Computer Science");
	JCheckBox cbEconomic	= new JCheckBox("Economic");
	public Student() {
		p.setSize(400, 300);
		p.setLayout(new GridLayout(7, 2));
		Container cont = p.getContentPane();
		cont.add(lblEmail);
		cont.add(tfEmail);
		cont.add(lblPass);
		cont.add(tfPass);
		cont.add(rdoMale);
		cont.add(rdoFemale);
		grGender.add(rdoFemale);
		grGender.add(rdoMale);
		cont.add(lblNation);
		cont.add(cboNation);
		cont.add(cbComputer);
		cont.add(cbEconomic);
		cont.add(btnRegis);
		btnRegis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Business bs = new Business();
				bs.connect();
				try {
					String gender="";
					if(rdoFemale.isSelected()) gender = rdoFemale.getText();
					if(rdoMale.isSelected()) gender = rdoMale.getText();
					String nation = cboNation.getSelectedItem().toString();
					String study ="";
					if(cbComputer.isSelected()) study += cbComputer.getText().toString() +";";
					if(cbEconomic.isSelected()) study += cbEconomic.getText().toString();
					int record = bs.executeDB("Insert into account(email,pass,gender,nation,study) values('"+tfEmail.getText()+"','"+tfPass.getText()+"','"+gender+"','"+nation+"','"+study+"')");
					if(record>0) JOptionPane.showMessageDialog(null, "Add OK");
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		p.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Student();
	}

}
