import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowTable extends JFrame
{
    public ShowTable(String sql)
    {
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/mydb";
        String userid = "root";
        String password = "dlxodn0!";
        JButton btnNewButton_sw = new JButton("\uB4F1\uB85D\uD558\uAE30");
        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, userid, password );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
        }
        catch (SQLException e)
        {	JOptionPane.showMessageDialog(btnNewButton_sw, e);
            System.out.println( e.getMessage() );
        }

        // Create Vectors and copy over elements from ArrayLists to them
        // Vector is deprecated but I am using them in this example to keep 
        // things simple - the best practice would be to create a custom defined
        // class which inherits from the AbstractTableModel class
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));
        getContentPane().setLayout(null);

        //  Create table with database data    
        JTable table = new JTable(dataVector, columnNamesVector)
        {
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

        JScrollPane scrollPane = new JScrollPane( table );
        scrollPane.setBounds(14, 0, 673, 313);
        getContentPane().add( scrollPane );
        
        JButton btnNewButton = new JButton("\uB2EB\uAE30");
        btnNewButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              dispose();
           }
        });
        btnNewButton.setBounds(295, 343, 105, 27);
        getContentPane().add(btnNewButton);
    }

    public static void main(String[] args)
    {
       Main mm = new Main();
        ShowTable frame = new ShowTable(mm.sql);
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.getContentPane().setPreferredSize(new Dimension(700,420));
        frame.pack();
        frame.setVisible(true);
    }
}