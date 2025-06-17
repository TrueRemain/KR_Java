package com.montecarlo;

import com.montecarlo.model.Rectangle;
import com.montecarlo.model.Triangle;
import com.montecarlo.service.MonteCarloService;
import com.montecarlo.util.JsonFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Основной класс приложения, реализующий алгоритм Монте-Карло для сравнения площадей фигур.
 * Запускает приложение и сравнивает площади треугольника и прямоугольника методом Монте-Карло.
 */
public class MonteCarloApplication {
    /** Логгер класса для регистрации ошибок и сообщений. */
    private static final Logger logger = LoggerFactory.getLogger(MonteCarloApplication.class);

    /** Путь к файлу с описанием треугольника. */
    private static final String TRIANGLE_JSON = "triangle.json";

    /** Путь к файлу с описанием прямоугольника. */
    private static final String RECTANGLE_JSON = "rectangle.json";

    /**
     * Основная точка входа в программу.
     *
     * Выполняет следующие шаги:
     * 1. Чтение исходных данных (треугольник и прямоугольник) из JSON-файлов.
     * 2. Создание экземпляра сервиса для расчета площадей методом Монте-Карло.
     * 3. Выполнение расчетов с различными числами точек.
     *
     * @param args аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        try {
            // Загрузка фигуры треугольника из JSON файла
            Triangle triangle = JsonFileReader.readTriangle(TRIANGLE_JSON);

            // Загрузка фигуры прямоугольника из JSON файла
            Rectangle rectangle = JsonFileReader.readRectangle(RECTANGLE_JSON);

            // Сервис для расчета площадей методом Монте-Карло
            MonteCarloService service = new MonteCarloService();

            // Количество случайных точек для каждого теста
            List<Long> pointsCounts = Arrays.asList(100L, 1000L, 10000L, 100000L, 1000000L, 10000000L);

            // Сравнение площадей
            service.compareAreas(triangle, rectangle, pointsCounts);

        } catch (IOException e) {
            logger.error("Ошибка чтения файлов JSON: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Общая ошибка: {}", e.getMessage());
        }
    }
}