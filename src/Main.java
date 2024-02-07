import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimerWindow timerWindow = new TimerWindow();
            WorkTimer workTimer = new WorkTimer(timerWindow);
            timerWindow.setVisible(true);
        });
    }
}