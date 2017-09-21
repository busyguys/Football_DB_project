import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class AddClub extends JFrame {
   
   static String ClubName = null;
   static String Homeground = null;
   static String Coach = null;
   static String City = null;
   static String Sponsor = null;
   static String PlayerName = null;
   

   private JPanel contentPane;
   private JTextField clubname;
   private JTextField homeground;
   private JTextField coach;
   private JTextField city;
   private Container contentPane_1;
   private JTextField playername;
   private JTextField sponsor;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               AddClub frame = new AddClub();
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
   
   public AddClub() {
      JFrame frame1 = new JFrame();
      Container contentPane = frame1.getContentPane();
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 454, 415);
      contentPane_1 = new JPanel();
      ((JComponent) contentPane_1).setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane_1);
      contentPane_1.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("\uC774\uB984(name)");
      lblNewLabel.setBounds(33, 50, 170, 27);
      contentPane_1.add(lblNewLabel);
      
      clubname = new JTextField();
      clubname.setText("ex) \uD0D1\uD150");
      clubname.setBounds(206, 50, 160, 24);
      contentPane_1.add(clubname);
      clubname.setColumns(10);
      
      homeground = new JTextField();
      homeground.setText("ex) \uC131\uC740\uC774\uB124");
      homeground.setColumns(10);
      homeground.setBounds(206, 130, 160, 24);
      contentPane_1.add(homeground);
      
      coach = new JTextField();
      coach.setText("ex) \uC288\uD37C\uB450\uD37C\uC9F1 \uC774\uAC74");
      coach.setColumns(10);
      coach.setBounds(206, 90, 160, 24);
      contentPane_1.add(coach);
      
      JLabel lblposition = new JLabel("\uCF54\uCE58 \uC774\uB984(coach)");
      lblposition.setBounds(33, 90, 170, 27);
      contentPane_1.add(lblposition);
      
      JLabel lblnationality = new JLabel("\uD648 \uAD6C\uC7A5(homeground)");
      lblnationality.setBounds(33, 130, 170, 27);
      contentPane_1.add(lblnationality);
      
      JLabel lblheight = new JLabel("\uCD9C\uC2E0\uC9C0(city)");
      lblheight.setBounds(33, 170, 170, 27);
      contentPane_1.add(lblheight);
      
      city = new JTextField();
      city.setText("ex) \uD3EC\uD56D");
      city.setColumns(10);
      city.setBounds(206, 170, 160, 24);
      contentPane_1.add(city);
      
      JLabel lblweight = new JLabel("\uC2A4\uD3F0\uC11C(sponsor)");
      lblweight.setBounds(33, 210, 170, 27);
      contentPane_1.add(lblweight);
      
      JButton btnNewButton = new JButton("\uCD08\uAE30\uD654");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             clubname.setText("");
             homeground.setText("");
             coach.setText("");
             city.setText("");
             sponsor.setText("");
             playername.setText("");
             
            
         }
      });
      btnNewButton.setBounds(59, 309, 105, 27);
      contentPane_1.add(btnNewButton);

      JButton btnNewButton_1 = new JButton("\uCDE8\uC18C");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //취소
        	 dispose();
            
         }
      });
      btnNewButton_1.setBounds(178, 309, 105, 27);
      contentPane_1.add(btnNewButton_1);

      JButton btnNewButton_2 = new JButton("\uB4F1\uB85D\uD558\uAE30");
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //등록하기
            
            if((clubname.getText()).equals("")){
               JOptionPane.showMessageDialog(null, "이름은 꼭 입력해주세요 .");
            }else{
               ClubName = clubname.getText();
                Homeground = homeground.getText();
                Coach = coach.getText();
                City = city.getText();
                Sponsor = sponsor.getText();
                PlayerName = playername.getText();
                
                addClubSQL();

              
            }
            
//            System.out.println(ClubName + Homeground + Coach + City + Sponsor + PlayerName);
         }
      });
      btnNewButton_2.setBounds(297, 309, 105, 27);
      contentPane_1.add(btnNewButton_2);
      
      JLabel lblplayername = new JLabel("\uC120\uC218 \uC774\uB984(player name)");
      lblplayername.setBounds(33, 250, 170, 27);
      contentPane_1.add(lblplayername);
      
      playername = new JTextField();
      playername.setText("ex) \uC131\uC740\uC3ED\uC740");
      playername.setColumns(10);
      playername.setBounds(206, 250, 160, 24);
      contentPane_1.add(playername);
      
      sponsor = new JTextField();
      sponsor.setText("ex) \uC639pc");
      sponsor.setColumns(10);
      sponsor.setBounds(206, 210, 160, 24);
      contentPane_1.add(sponsor);
      
      JLabel lblNewLabel_1 = new JLabel("\uAD6C\uB2E8 \uCD94\uAC00");
      lblNewLabel_1.setFont(new Font("돋움", Font.BOLD, 20));
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setBounds(14, 12, 408, 26);
      contentPane_1.add(lblNewLabel_1);
      
   }
   
   public static void addClubSQL(){
       //Connection connection = DriverManager.getConnection( url, userid, password );
	   JButton btnNewButton_ac = new JButton("\uB4F1\uB85D\uD558\uAE30");
       PreparedStatement ps = null; // 동적 쿼리

       //  Connect to an MySQL Database, run query, get result set
       String url = "jdbc:mysql://localhost:3306/mydb";
       String userid = "root";
       String password = "dlxodn0!";
       String sql = "insert into club values(?,?,?,?,?,?)";
   
       
       // Java SE 7 has try-with-resources
       // This will ensure that the sql objects are closed when the program 
       // is finished with them
       try(Connection con = DriverManager.getConnection(url, userid, password);)
       {   
          ps = con.prepareStatement(sql);
          ps.setString(1, ClubName);
          ps.setString(2, Sponsor);
          ps.setString(3, City);
          ps.setString(4, Coach);
          ps.setString(5, Homeground);
          ps.setString(6, PlayerName);
          int n = ps.executeUpdate();
          
          if(n > 0){//에러처리
             JOptionPane.showMessageDialog(btnNewButton_ac, "등록되었습니다.");
             System.out.println("구단 추가 성공");
          }else{
             System.out.println("구단 추가 실패");
             JOptionPane.showMessageDialog(btnNewButton_ac, "등록실패.");
             //정상적인 쿼리 실패
          }
       }catch(SQLException e){
          e.printStackTrace();
          JOptionPane.showMessageDialog(null, e);
          //예외 쿼리 실패
       }
   }
}