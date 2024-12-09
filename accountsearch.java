import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class accountsearch extends JFrame {
	JFrame frame = new JFrame("Table with Labels and TextFields");
	JTextField tfSearch = new JTextField(20);
	JButton btnSearch = new JButton("Search");
	public accountsearch() {
		/*frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(tfSearch);
        panel.add(btnSearch);
        frame.add(panel);
        setVisible(true);*/
		
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Set up the panel for the table and form
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Table with data model
        String[] columnNames = {"ID", "Name", "Age"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        
        // Add the table to the center of the panel
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Panel for labels and text fields (form)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2)); // 4 rows, 2 columns

        JLabel labelId = new JLabel("ID:");
        JTextField textFieldId = new JTextField();
        
        JLabel labelName = new JLabel("Name:");
        JTextField textFieldName = new JTextField();
        
        JLabel labelAge = new JLabel("Age:");
        JTextField textFieldAge = new JTextField();
        
        JButton addButton = new JButton("Add to Table");

        // Add components to the form panel
        formPanel.add(labelId);
        formPanel.add(textFieldId);
        formPanel.add(labelName);
        formPanel.add(textFieldName);
        formPanel.add(labelAge);
        formPanel.add(textFieldAge);
        formPanel.add(new JLabel()); // Empty label for layout
        formPanel.add(addButton);

        // Add the form panel to the bottom of the main panel
        panel.add(formPanel, BorderLayout.SOUTH);

        // Add action listener to the "Add" button
        addButton.addActionListener(e -> {
            // Retrieve the data from text fields
            String id = textFieldId.getText();
            String name = textFieldName.getText();
            String age = textFieldAge.getText();

            // Add data to the table
            model.addRow(new Object[]{id, name, age});

            // Clear the text fields after adding the row
            textFieldId.setText("");
            textFieldName.setText("");
            textFieldAge.setText("");
        });

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		accountsearch acc = new accountsearch();
	}

}