
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import paint.JFontChooser;

public class Template extends javax.swing.JFrame {
 
    void setImage(JLabel lab)
    {   
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("4 Extensions Supported","jpg","png","jpeg","gif");
        fileChooser.setFileFilter(filter);
        int selected = fileChooser.showOpenDialog(this);
        if(selected== JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            String getselectedImage= file.getAbsolutePath();
            JOptionPane.showMessageDialog(null, getselectedImage);
            ImageIcon imIco = new ImageIcon(getselectedImage );
            Image imFit= imIco.getImage();
            Image imgFit = imFit.getScaledInstance(lab.getWidth(), lab.getHeight(),Image.SCALE_SMOOTH );
            lab.setIcon(new ImageIcon(imgFit));
        }
    }
    
    void setFont(JButton jButton1,JTextArea jTextArea2){
        JFontChooser fontChooser = new JFontChooser();
         int result = fontChooser.showDialog(jButton1);
            if (result == JFontChooser.OK_OPTION)
            {
                 Font font = fontChooser.getSelectedFont(); 
                 jTextArea2.setFont(font);
            }
    }
    
    void setFontColor(JTextArea jTextArea2){
         Color color = JColorChooser.showDialog(this, "Colors", Color.BLUE);
        jTextArea2.setForeground(color);
    }
    
    boolean saveMag(String title, String body, String file){
         try {        
         String username = Setting.user.getuser();
//        String username = "sad";
        PreparedStatement st = Setting.c.prepareStatement("INSERT INTO maga(username, title, body, file) VALUES(?, ?, ?, ?)");
        st.setString(1, username);
        st.setString(2, title);
        st.setString(3, body);
        st.setString(4, file);
        st.execute();
        return true;
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
        return false;
    }
    
    void print(JPanel panel,String s) {
        try
        {
            if(s.isEmpty())
            {
                throw new Exception("Please Enter Title");
            }
        
      PrinterJob job= PrinterJob.getPrinterJob();
        job.setJobName("Print Data");
        job.setPrintable((Graphics pg, PageFormat pf, int pageNum) -> {
            if(pageNum>0){
                return Printable.NO_SUCH_PAGE;
            }
            Graphics2D g2=(Graphics2D)pg;
            g2.translate(pf.getImageableX(),pf.getImageableY());
            g2.scale(0.55,1);
            panel.paint(g2);
            return Printable.PAGE_EXISTS;
        });
        boolean ok=job.printDialog();
        if(ok){
            try{
                job.print();
            }
            catch(PrinterException ex){}
        }
    }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    ResultSet getMags(){
        try{
         PreparedStatement st = Setting.c.prepareStatement("SELECT * FROM public.maga WHERE username = ?");
         String username = Setting.user.getuser();
        st.setString(1, username);
        st.execute();
        ResultSet r1=st.executeQuery();
        return r1;
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
        return null;
    }
    
}
