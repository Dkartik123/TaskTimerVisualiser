    import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
    
    public class TimeDataStorage {
        private static final String FILE_PATH = "time_data.txt";
    
        // Метод для сохранения данных
        public static void saveTimeData(long startTime, long elapsedTime) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                writer.write(startTime + "," + elapsedTime); // Записываем начальное время и прошедшее время через запятую
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        // Метод для загрузки данных
        public static long[] loadTimeData() {
            long[] timeData = new long[2]; // Создаем массив для хранения данных
    
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line = reader.readLine(); // Считываем строку из файла
                if (line != null) {
                    String[] parts = line.split(","); // Разделяем строку по запятой
                    timeData[0] = Long.parseLong(parts[0]); // Парсим начальное время
                    timeData[1] = Long.parseLong(parts[1]); // Парсим прошедшее время
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            return timeData;
        }
    }
    
    

