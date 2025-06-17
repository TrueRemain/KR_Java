package com.montecarlo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.montecarlo.model.Rectangle;
import com.montecarlo.model.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Утилитарный класс для чтения объектов из JSON файлов.
 * Предоставляет методы для загрузки объектов (например, треугольников и прямоугольников)
 * из файлов формата JSON.
 */
public class JsonFileReader {
    /** Логгер класса для ведения журнала операций. */
    private static final Logger logger = LoggerFactory.getLogger(JsonFileReader.class);

    /** Обработчик для преобразования JSON в объекты Java. */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Читает данные из файла в виде объекта {@link Triangle}.
     *
     * @param filePath путь к файлу с JSON-данными
     * @return объект треугольника, созданный из JSON файла
     * @throws IOException если файл не найден или произошла ошибка парсинга
     */
    public static Triangle readTriangle(String filePath) throws IOException {
        try (InputStream inputStream = JsonFileReader.class.getClassLoader().getResourceAsStream(filePath)) {
            Triangle triangle = objectMapper.readValue(inputStream, Triangle.class);
            logger.info("Файл (треугольник) успешно прочитан: {}", filePath);
            return triangle;
        }
    }

    /**
     * Читает данные из файла в виде объекта {@link Rectangle}.
     *
     * @param filePath путь к файлу с JSON-данными
     * @return объект прямоугольника, созданный из JSON файла
     * @throws IOException если файл не найден или произошла ошибка парсинга
     */
    public static Rectangle readRectangle(String filePath) throws IOException {
        try (InputStream inputStream = JsonFileReader.class.getClassLoader().getResourceAsStream(filePath)) {
            Rectangle rectangle = objectMapper.readValue(inputStream, Rectangle.class);
            logger.info("Файл (прямоугольник) успешно прочитан: {}", filePath);
            return rectangle;
        }
    }
}