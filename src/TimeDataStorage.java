import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDataStorage {
    private static final String FILE_PATH = "time_data.txt";

    // Метод для сохранения данных
    public static void saveTimeData(long startTime, long elapsedTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Получаем текущую дату и время
            Date currentDate = new Date();
            // Форматируем дату и время в строку
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(currentDate);
            // Записываем дату, начальное время и прошедшее время через запятую
            writer.write(startTime + "," + elapsedTime + formattedDate + System.lineSeparator()); // Время
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для загрузки данных
    public static long[] loadTimeData() {
        long[] timeData = new long[3]; // Создаем массив для хранения данных
    
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            // Считываем дату
            String dateString = reader.readLine();
            // Считываем строку с временем
            String timeString = reader.readLine();
            
            // Проверяем, что строки не равны null
            if (dateString != null && timeString != null) {
                // Форматируем дату обратно в объект Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date currentDate = dateFormat.parse(dateString);
    
                // Получаем время в миллисекундах
                timeData[0] = currentDate.getTime();
                // Парсим начальное время и прошедшее время
                String[] parts = timeString.split(",");
                timeData[1] = Long.parseLong(parts[0]);
                timeData[2] = Long.parseLong(parts[1]);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    
        return timeData;
    }}