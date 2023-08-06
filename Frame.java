
package ac.za.tut;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class Frame extends JFrame 
{
    private JLabel headinglabel;
    private JTextArea displaytextarea;
    private JButton openbutton,savebutton,clearbutton;
    private JPanel headingpanel, displaypanel,buttonspanel, mainpanel;
    private JFileChooser filechooser;
    private File file;
    private BufferedWriter bw;
    private BufferedReader br;

    public Frame() 
    {
        setTitle("My Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setSize(600, 700);
        
        filechooser = new JFileChooser();
        
        headinglabel = new JLabel("Heading");
        headinglabel.setBorder(new BevelBorder(BevelBorder.RAISED));
        headinglabel.setFont(new Font(Font.SERIF, Font.BOLD + Font.ITALIC,30));
        headinglabel.setForeground(Color.blue);
        
        displaytextarea = new JTextArea(20, 30);
        displaytextarea.setEditable(false);
        displaytextarea.setBorder(new LineBorder(Color.black, 2, true));
        
        openbutton = new JButton("Open");
        openbutton.addActionListener(new openButtonHandler());
        
        savebutton = new JButton("Save");
        savebutton.addActionListener(new saveButtonHandler());
        
        clearbutton = new JButton("Clear");
        clearbutton.addActionListener(new clearButtonHandler());
        
        headingpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headingpanel.add(headinglabel);
        
        displaypanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        displaypanel.add(displaytextarea);
        
        buttonspanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonspanel.add(savebutton);
        buttonspanel.add(openbutton);
        buttonspanel.add(clearbutton);
        
        mainpanel = new JPanel(new BorderLayout());
        mainpanel.add(headingpanel, BorderLayout.NORTH);
        mainpanel.add(displaypanel, BorderLayout.CENTER);
        mainpanel.add(buttonspanel, BorderLayout.SOUTH);
        
        add(mainpanel);
        
        pack();
        setVisible(true);
    }
    
    private class openButtonHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
         /*this line poops up the dialog that the user must interact with, and the component is the
         file that is to be chosen from, so if the user presses an open button it means they approve and it will return an int value and if the close/cancel
         the dialog it returns*/
            
           int value = filechooser.showOpenDialog(Frame.this);
           if(value == JFileChooser.OPEN_DIALOG) //meaning I am approving open
            {
               try 
               {
                   file = filechooser.getSelectedFile();
                   br = new BufferedReader(new FileReader(file));
                   
                   String data = "", record;
                   
                   while((record = br.readLine()) != null)
                    {
                        data = data + record + "\n";
                    }
                   
                   br.close();
                   displaytextarea.setText(data);
                   
               } catch (FileNotFoundException ex) 
               {
                   Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) 
               {
                   Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        }
    
    }
    
    private class saveButtonHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
           int value = filechooser.showSaveDialog(Frame.this);
           if(value == JFileChooser.SAVE_DIALOG)
            {
                file = filechooser.getSelectedFile();
               try {
                   bw = new BufferedWriter(new FileWriter(file));
                   
                   String data = displaytextarea.getText();
                   bw.write(data);
                   bw.close();
                  
                   
               } catch (IOException ex) 
               {
                   Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        }
    
    }
    
    private class clearButtonHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
           displaytextarea.setText("");
        }
    
    }
    
}
