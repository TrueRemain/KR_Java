package com.montecarlo.service;

import com.montecarlo.model.Point;
import com.montecarlo.model.Rectangle;
import com.montecarlo.model.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Random;

/**
 * Сервис для вычисления площади методом Монте-Карло и сравнения с точным значением.
 */
public class MonteCarloService {
    private static final Logger logger = LoggerFactory.getLogger(MonteCarloService.class);
    private final Random random = new Random();

    /**
     * Вычисляет площадь треугольника методом Монте-Карло.
     *
     * @param triangle Треугольник, площадь которого нужно вычислить
     * @param rectangle Ограничивающий прямоугольник для генерации случайных точек
     * @param pointsCount Количество случайных точек для генерации
     * @return Приближенное значение площади треугольника
     */
    public double calculateMonteCarloArea(Triangle triangle, Rectangle rectangle, long pointsCount) {
        long pointsInside = 0;
        double rectangleArea = rectangle.calculateArea();

        // Границы прямоугольника для генерации случайных точек
        double minX = rectangle.getA().getX();
        double maxX = rectangle.getC().getX();
        double minY = rectangle.getA().getY();
        double maxY = rectangle.getC().getY();

        logger.info("Начало расчета методом Монте-Карло с количеством точек: {}", pointsCount);

        for (long i = 0; i < pointsCount; i++) {
            double randomX = minX + (maxX - minX) * random.nextDouble();
            double randomY = minY + (maxY - minY) * random.nextDouble();
            if (triangle.contains(new Point(randomX, randomY))) {
                pointsInside++;
            }
        }

        double calculatedArea = rectangleArea * pointsInside / pointsCount;
        logger.info("Рассчитанная площадь треугольника методом Монте-Карло: {}", calculatedArea);
        return calculatedArea;
    }

    /**
     * Вычисляет точную площадь треугольника по формуле Герона.
     *
     * @param triangle Треугольник для вычисления площади
     * @return Точное значение площади треугольника
     */
    public double calculateExactTriangleArea(Triangle triangle) {
        Point a = triangle.getA();
        Point e = triangle.getE();
        Point f = triangle.getF();

        // Вычисление длин сторон
        double ae = Math.sqrt(Math.pow(e.getX() - a.getX(), 2) + Math.pow(e.getY() - a.getY(), 2));
        double ef = Math.sqrt(Math.pow(f.getX() - e.getX(), 2) + Math.pow(f.getY() - e.getY(), 2));
        double fa = Math.sqrt(Math.pow(a.getX() - f.getX(), 2) + Math.pow(a.getY() - f.getY(), 2));

        // Формула Герона
        double s = (ae + ef + fa) / 2;
        double exactArea = Math.sqrt(s * (s - ae) * (s - ef) * (s - fa));

        logger.info("Точная площадь треугольника: {}", exactArea);
        return exactArea;
    }

    /**
     * Сравнивает площади, вычисленные разными методами, для разных количеств точек.
     *
     * @param triangle Треугольник для анализа
     * @param rectangle Ограничивающий прямоугольник
     * @param pointsCounts Список количеств точек для тестирования
     */
    public void compareAreas(Triangle triangle, Rectangle rectangle, List<Long> pointsCounts) {
        double exactArea = calculateExactTriangleArea(triangle);
        logger.info("Начата процедура сравнения площадей.");

        System.out.println("Exact triangle area: " + exactArea);
        System.out.println("| Points Count | Monte Carlo Area | Exact Area | Error (%) | Time (ms) |");
        System.out.println("|--------------|------------------|------------|-----------|-----------|");

        for (Long count : pointsCounts) {
            long startTime = System.currentTimeMillis();
            double monteCarloArea = calculateMonteCarloArea(triangle, rectangle, count);
            long endTime = System.currentTimeMillis();
            double error = Math.abs(monteCarloArea - exactArea) / exactArea * 100;

            System.out.printf("| %,11d | %16.2f | %10.2f | %8.2f | %9d |\n",
                    count, monteCarloArea, exactArea, error, (endTime - startTime));
        }
    }
}