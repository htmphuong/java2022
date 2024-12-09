import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginGUI extends JFrame {
	JFrame f = new JFrame();
	
	JLabel lblEmail = new JLabel("Email");
	JTextField tfEmail = new JTextField(30);
	JLabel lblPass = new JLabel("Password");
	JPasswordField tfPass = new JPasswordField(30);
	JButton btnLogin = new JButton("Login");
	
	public loginGUI() {
		f.setSize(400, 200);
		f.setLayout(new GridLayout(3, 2));
		Container cont = f.getContentPane();
		cont.add(lblEmail);
		cont.add(tfEmail);
		cont.add(lblPass);
		cont.add(tfPass);
		cont.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			if(authenticateUser(tfEmail.getText(), tfPass.getText())) {
				JOptionPane.showMessageDialog(null, "Connection sucessfully");
				account acc = new account();
			}
			else {
				JOptionPane.showMessageDialog(null, "Incorrect user or password");
			}
			
			}
		});
		f.setVisible(true);
	}
	public boolean authenticateUser(String user, String pass) {
		try {
			DBAccess db = new DBAccess();
			ResultSet rs = db.query("select * from account where email='"+user+"' and pass ='"+pass+"' ");
			return rs.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loginGUI acc = new loginGUI();
	}

}
