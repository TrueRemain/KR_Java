package com.montecarlo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.montecarlo.model.Rectangle;
import com.montecarlo.model.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;

public class JsonFileReader {
    private static final Logger logger = LoggerFactory.getLogger(JsonFileReader.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Triangle readTriangle(String filePath) throws IOException {
        try (InputStream inputStream = JsonFileReader.class.getClassLoader().getResourceAsStream(filePath)) {
            Triangle triangle = objectMapper.readValue(inputStream, Triangle.class);
            logger.info("Файл (треугольник) успешно прочитан: {}", filePath);
            return triangle;
        }
    }

    public static Rectangle readRectangle(String filePath) throws IOException {
        try (InputStream inputStream = JsonFileReader.class.getClassLoader().getResourceAsStream(filePath)) {
            Rectangle rectangle = objectMapper.readValue(inputStream, Rectangle.class);
            logger.info("Файл (прямоугольник) успешно прочитан: {}", filePath);
            return rectangle;
        }
    }
}