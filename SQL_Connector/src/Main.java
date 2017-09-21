import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.util.Scanner;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class Main extends JFrame {

   private JPanel contentPane;
   ArrayList columnNames = new ArrayList();
   ArrayList data = new ArrayList();
   Vector columnNamesVector = new Vector();
   Vector dataVector = new Vector();
   

   String url = "jdbc:mysql://localhost:3306/mydb";
   String userid = "root";
   String password = "dlxodn0!";
   static String sql = "SELECT * FROM awards";


   
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Main frame = new Main();
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
      
   public Main() {
	   
       //  Connect to an MySQL Database, run query, get result set

       // Java SE 7 has try-with-resources
       // This will ensure that the sql objects are closed when the program 
       // is finished with them
 
      
         JComboBox comboBox = new JComboBox();
         comboBox.setBackground(new Color(192, 192, 192));
         comboBox.setBounds(47, 70, 192, 24);
         comboBox.setModel(new DefaultComboBoxModel(new String[] {"Awards", "League", "Club", "Player", "Sponsor"}));
         
         
         comboBox.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e){
               if(comboBox.getSelectedItem().equals("Awards")){ 
                  sql = "SELECT * FROM awards";
               }
               else if(comboBox.getSelectedItem().equals("League")){
                  sql = "SELECT * FROM league";  
               }
               else if(comboBox.getSelectedItem().equals("Club")){
                  sql = "SELECT * FROM club";
                  
               }
               
               else if(comboBox.getSelectedItem().equals("Player")){
                  sql = "SELECT * FROM player";
               }
               
               else if(comboBox.getSelectedItem().equals("Sponsor")){
                   sql = "SELECT * FROM sponsor";
               }
            }
            
         });
         
         

         
         
///////////////////////////////////////////////////////////////////////////////
      

      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 494, 596);
      contentPane = new JPanel();
      contentPane.setBackground(new Color(0, 128, 0));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("\uC720\uB7FD \uCD95\uAD6C \uAC80\uC0C9 \uC2DC\uC2A4\uD15C");
      lblNewLabel.setForeground(new Color(255, 255, 255));
      lblNewLabel.setFont(new Font("돋움체", Font.BOLD, 21));
      lblNewLabel.setBounds(0, 12, 476, 36);
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      contentPane.add(lblNewLabel);
      

      JPanel buttonPanel = new JPanel();
      getContentPane().add( buttonPanel, BorderLayout.SOUTH );
      
      contentPane.add(comboBox);
      
      JLabel lblNewLabel_1 = new JLabel("<SQL \uBA85\uB839\uC5B4>");
      lblNewLabel_1.setForeground(new Color(255, 255, 255));
      lblNewLabel_1.setFont(new Font("돋움", Font.BOLD, 17));
      lblNewLabel_1.setBounds(14, 347, 448, 24);
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      contentPane.add(lblNewLabel_1);
      

      
      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(14, 372, 448, 121);
      contentPane.add(scrollPane_1);
      
      JTextArea sql1 = new JTextArea();
      scrollPane_1.setColumnHeaderView(sql1);
      
      JButton btnNewButton_1 = new JButton("\uC785\uB825");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //sql문 받음
            sql = sql1.getText();
           ShowTable.main(null);
         }
      });
      btnNewButton_1.setBounds(310, 505, 93, 27);
      contentPane.add(btnNewButton_1);
      
      
      JButton btnNewButton = new JButton("\uC804\uCCB4 \uC0AD\uC81C");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            sql1.setText("");
         }
      });
      btnNewButton.setBounds(78, 505, 93, 27);
      contentPane.add(btnNewButton);
  
      
      JButton btnNewButton_2 = new JButton("\uAD6C\uB2E8 \uCD94\uAC00");
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //클럽 추가
            AddClub.main(null);
         }
      });
      btnNewButton_2.setBounds(47, 116, 155, 27);
      contentPane.add(btnNewButton_2);
      
      JButton btnNewButton_3 = new JButton("\uAD6C\uB2E8 \uC0AD\uC81C");
      btnNewButton_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //클럽 삭제
            DeleteClub.main(null);
         }
      });
      btnNewButton_3.setBounds(280, 116, 155, 27);
      contentPane.add(btnNewButton_3);
      
      
      
      JButton btnNewButton_4 = new JButton("\uD655\uC778");
      btnNewButton_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //ShowTable
            ShowTable.main(null);
            
         }
      });
      btnNewButton_4.setBounds(296, 69, 121, 27);
      contentPane.add(btnNewButton_4);
      
      JLabel lblNewLabel_2 = new JLabel("");
      lblNewLabel_2.setIcon(new ImageIcon("Soccer.jpg"));
      lblNewLabel_2.setBounds(141, 165, 208, 170);
      contentPane.add(lblNewLabel_2);
     
      
      
   }
   
   /*
       Vector columnNamesVector = new Vector();
   Vector dataVector = new Vector();
    */
   
  
}