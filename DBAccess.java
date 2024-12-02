import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBAccess {
	//tao connection
	Connection conn;
	Statement stmt;
	ResultSet rs;//nhan data from select query
	
	//function connect to db
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
			//JOptionPane.showMessageDialog(null, "Successful Connection!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//insert,update, delete in sql
	public int execute(String sql) {
		int record=0;
		try {
			connect();
			stmt = conn.createStatement();
			record = stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return record;
	}
	//select data from database
	public ResultSet query(String sql) {
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
}
