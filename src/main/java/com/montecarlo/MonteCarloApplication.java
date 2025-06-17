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

public class MonteCarloApplication {
    private static final Logger logger = LoggerFactory.getLogger(MonteCarloApplication.class);
    private static final String TRIANGLE_JSON = "triangle.json";
    private static final String RECTANGLE_JSON = "rectangle.json";

    public static void main(String[] args) {
        try {
            Triangle triangle = JsonFileReader.readTriangle(TRIANGLE_JSON);
            Rectangle rectangle = JsonFileReader.readRectangle(RECTANGLE_JSON);

            MonteCarloService service = new MonteCarloService();
            List<Long> pointsCounts = Arrays.asList(100L, 1000L, 10000L, 100000L, 1000000L, 10000000L);

            service.compareAreas(triangle, rectangle, pointsCounts);

        } catch (IOException e) {
            logger.error("Ошибка чтения файлов JSON: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Общая ошибка: {}", e.getMessage());
        }
    }
}