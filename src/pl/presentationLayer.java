package pl;

import javax.swing.*;

import TransferObject.PoemTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import dal.PoemDal;
import dal.RootDal;
import dal.VerseDal;
public class presentationLayer {
    private JFrame frame;
  
    private JTextField misra1Field;
    private JTextField misra2Field;
 

    public presentationLayer() {
        frame = new JFrame("Poem Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(6, 3));

      
       
        JLabel misra1Label = new JLabel("Misra 1:");
        misra1Field = new JTextField();
        JLabel misra2Label = new JLabel("Misra 2:");
        misra2Field = new JTextField();
     

        JButton insertButton = new JButton("Insert Verse");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPoem();
            }
        });


      
        frame.add(misra1Label);
        frame.add(misra1Field);
        frame.add(misra2Label);
        frame.add(misra2Field);
        frame.add(insertButton);

        frame.setVisible(true);
    }

    private void insertPoem() {
      
      
        String misra1 = misra1Field.getText();
        String misra2 = misra2Field.getText();
      

        PoemTO poem = new PoemTO();
     
        poem.setMisra1(misra1);
        poem.setMisra2(misra2);
     

       

        try {
            PoemDal.savePoem(poem);
            JOptionPane.showMessageDialog(frame, "Poem inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while inserting poem.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
     
        misra1Field.setText("");
        misra2Field.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new presentationLayer());
    }
}