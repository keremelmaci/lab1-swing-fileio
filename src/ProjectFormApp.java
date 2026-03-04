import javax.swing.JFrame;
import java.awt.*;

public class ProjectFormApp {
    public static void main(String[] args) {


        JFrame frame = new JFrame("Software Project Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ProjectFormPanel());
        frame.pack();
        frame.setSize(500,450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}


