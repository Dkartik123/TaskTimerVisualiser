import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TimerWindow extends JFrame {
    private JLabel timerLabel;
    private JButton startButton;
    private JButton stopButton;
    private WorkTimer workTimer; // Поле для хранения объекта WorkTimer

    public TimerWindow() {
        super("Work Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        timerLabel = new JLabel("00:00:00");
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));

        startButton = new JButton("Старт");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerWindow window = TimerWindow.this; // Создаем ссылку на текущий экземпляр TimerWindow
                if (window.workTimer == null) { // Проверяем, существует ли уже таймер
                    window.workTimer = new WorkTimer(window); // Если нет, создаем новый объект WorkTimer
                    window.workTimer.startTimer(); // Запускаем таймер
                } else {
                    window.workTimer.startTimer(); // Если таймер уже существует, просто запускаем его
                }
            }
        });

        stopButton = new JButton("Стоп");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (workTimer != null) {
                    workTimer.stopTimer(); // Останавливаем таймер при нажатии на кнопку "Стоп"
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(timerLabel, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.NORTH);
        panel.add(stopButton, BorderLayout.SOUTH);
        add(panel);
    }

    // Метод для обновления метки времени
    public void updateTimerLabel(String timeString) {
        timerLabel.setText(timeString);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimerWindow timerWindow = new TimerWindow();
            timerWindow.setVisible(true);
        });
    }
}
