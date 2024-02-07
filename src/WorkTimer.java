import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class WorkTimer {
    private Timer timer;
    private long startTime;
    private long elapsedTime;
    private boolean running;
    private TimerWindow timerWindow; // Ссылка на объект TimerWindow

    public WorkTimer(TimerWindow timerWindow) {
        this.timerWindow = timerWindow;
        startTime = 0;
        elapsedTime = 0;
        running = false;
    }

    public void startTimer() {
        if (!running) {
            startTime = System.currentTimeMillis() - elapsedTime;
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    elapsedTime = System.currentTimeMillis() - startTime;
                    // Форматируем время и обновляем метку времени в TimerWindow
                    timerWindow.updateTimerLabel(formatTime(elapsedTime));
                }
            }, 0, 1000); // Обновление каждую секунду
            running = true;
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            running = false;
            // Сохраняем данные о времени работы при остановке таймера
            TimeDataStorage.saveTimeData(startTime, elapsedTime);
        }
    }

    public void loadTimeDataOnStartup() {
        long[] timeData = TimeDataStorage.loadTimeData();
        startTime = timeData[0]; // Получаем начальное время
        elapsedTime = timeData[1]; // Получаем прошедшее время
    }

    private String formatTime(long millis) {
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
