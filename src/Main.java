import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main extends JFrame {
    
    public void init() {
        setTitle("Welcome");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.init();
    }
}