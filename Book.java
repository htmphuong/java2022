import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Book extends JFrame{
	JFrame f = new JFrame();
	ImageIcon imgAdd = new ImageIcon("D:\\MinhPhuong\\Dia E\\Minh Phuong\\Java\\2024-2025\\GUI\\image\\Book.png");
	JLabel lblBook = new JLabel(imgAdd);
	JButton btnAdd = new JButton("Add Book");
	public Book() {
		f.setSize(300, 300);
		f.setLayout(new FlowLayout());
		Container cont = f.getContentPane();
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		//btnAdd.setPreferredSize(new Dimension(10, 10));
		f.add(lblBook);
		f.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				account acc = new account();
				//acc.setVisible(true);
			}
		});
		f.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Book();
	}

}
