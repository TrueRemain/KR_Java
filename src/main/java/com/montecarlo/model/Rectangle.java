package com.montecarlo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс прямоугольника, заданный четырьмя точками на координатной плоскости.
 */
public class Rectangle {
    /** Логгер класса для отладки. */
    private static final Logger logger = LoggerFactory.getLogger(Rectangle.class);

    /** Точка A прямоугольника. */
    private final Point a;

    /** Точка B прямоугольника. */
    private final Point b;

    /** Точка C прямоугольника. */
    private final Point c;

    /** Точка D прямоугольника. */
    private final Point d;

    /**
     * Создает новый прямоугольник, задав четыре вершины.
     *
     * @param a первая вершина прямоугольника
     * @param b вторая вершина прямоугольника
     * @param c третья вершина прямоугольника
     * @param d четвертая вершина прямоугольника
     */
    public Rectangle(@JsonProperty("a") Point a,
                     @JsonProperty("b") Point b,
                     @JsonProperty("c") Point c,
                     @JsonProperty("d") Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Возвращает первую вершину прямоугольника.
     *
     * @return вершина A
     */
    public Point getA() { return a; }

    /**
     * Возвращает вторую вершину прямоугольника.
     *
     * @return вершина B
     */
    public Point getB() { return b; }

    /**
     * Возвращает третью вершину прямоугольника.
     *
     * @return вершина C
     */
    public Point getC() { return c; }

    /**
     * Возвращает четвёртую вершину прямоугольника.
     *
     * @return вершина D
     */
    public Point getD() { return d; }

    /**
     * Вычисляет площадь прямоугольника исходя из его размеров.
     *
     * @return площадь прямоугольника
     */
    public double calculateArea() {
        double width = Math.abs(c.getX() - a.getX()); // ширина прямоугольника
        double height = Math.abs(c.getY() - a.getY()); // высота прямоугольника
        double area = width * height; // вычисление площади

        logger.info("Площадь прямоугольника: {}", area); // вывод результата в лог

        return area;
    }
}