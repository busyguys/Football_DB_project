import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class DeleteClub extends JFrame {

   private JPanel contentPane;
   static String writedClub = null;
   private JTextField deletec;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               DeleteClub frame = new DeleteClub();
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
   public DeleteClub() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 713, 504);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("\uAD6C\uB2E8 \uBAA9\uB85D");
      lblNewLabel.setFont(new Font("돋움", Font.BOLD, 20));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(14, 12, 667, 34);
      contentPane.add(lblNewLabel);
      
      
      ////////////////
      
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/mydb";
        String userid = "root";
        String password = "dlxodn0!";
        String sql = "SELECT * FROM club";

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
        {
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

        
        JButton btnNewButton = new JButton("\uB2EB\uAE30");
        btnNewButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
           //닫기
        	   dispose();
           }
        });
        btnNewButton.setBounds(294, 407, 105, 27);
        getContentPane().add(btnNewButton);
      

      
      
      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setBounds(0, 54, 695, 258);
      contentPane.add(scrollPane);
      
      JLabel lblNewLabel_1 = new JLabel("\uC0AD\uC81C\uD558\uC2E4 \uAD6C\uB2E8\uC744 \uC785\uB825\uD574\uC8FC\uC138\uC694:");
      lblNewLabel_1.setFont(new Font("돋움", Font.PLAIN, 19));
      lblNewLabel_1.setBounds(27, 337, 266, 34);
      contentPane.add(lblNewLabel_1);
      
      JButton btnNewButton_1 = new JButton("\uC0AD\uC81C\uD558\uAE30");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            writedClub = deletec.getText();
            deleteClubSQL(writedClub);
         }
      });
      btnNewButton_1.setBounds(550, 343, 112, 27);
      contentPane.add(btnNewButton_1);
      
      deletec = new JTextField();
      deletec.setBounds(313, 344, 208, 24);
      contentPane.add(deletec);
      deletec.setColumns(10);
      
      
      
      
   }
   
   
    public static void deleteClubSQL(String wclub){
    	JButton btnNewButton_dc = new JButton("\uB4F1\uB85D\uD558\uAE30");
          PreparedStatement ps = null; // 동적 쿼리

          //  Connect to an MySQL Database, run query, get result set
          String url = "jdbc:mysql://localhost:3306/mydb";
          String userid = "root";
          String password = "dlxodn0!";
          String sql = "delete from club where name='" + wclub + "'";
          
      
          
          // Java SE 7 has try-with-resources
          // This will ensure that the sql objects are closed when the program 
          // is finished with them
          try(Connection con = DriverManager.getConnection(url, userid, password);)
          {   
             ps = con.prepareStatement(sql);
             int n = ps.executeUpdate();
             
             if(n > 0){//에러처리
                System.out.println("구단 삭제 성공");
                JOptionPane.showMessageDialog(btnNewButton_dc, "삭제 하였습니다");
             }else{
            	JOptionPane.showMessageDialog(btnNewButton_dc, "삭제 실패");
            	//정상적인 쿼리 실패일 때 뜨는 문
                System.out.println("구단 삭제 실패");
             }
          }catch(SQLException e){
        	  JOptionPane.showMessageDialog(btnNewButton_dc, e);
        	  //특이경우 실패구문(키 참조 위배, 프라이머리키 값 질서 위배 등등 뜨는 경우)
             e.printStackTrace();
          }
      
    }
}