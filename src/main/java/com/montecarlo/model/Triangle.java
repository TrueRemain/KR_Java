package com.montecarlo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс, представляющий треугольник с тремя вершинами.
 * Поддерживает проверку принадлежности точки треугольнику.
 */
public class Triangle {
    private static final Logger logger = LoggerFactory.getLogger(Triangle.class);
    private final Point a;
    private final Point e;
    private final Point f;

    /**
     * Конструктор треугольника.
     *
     * @param a Первая вершина треугольника
     * @param e Вторая вершина треугольника
     * @param f Третья вершина треугольника
     */
    public Triangle(@JsonProperty("a") Point a,
                    @JsonProperty("e") Point e,
                    @JsonProperty("f") Point f) {
        this.a = a;
        this.e = e;
        this.f = f;
    }

    public Point getA() { return a; }
    public Point getE() { return e; }
    public Point getF() { return f; }

    /**
     * Проверяет, принадлежит ли точка треугольнику.
     * Использует барицентрические координаты для определения положения точки.
     *
     * @param point Точка для проверки
     * @return true, если точка внутри треугольника, иначе false
     */
    public boolean contains(Point point) {
        double x = point.getX();
        double y = point.getY();
        logger.debug("Проверка точки {} внутри треугольника.", point);

        double xA = a.getX(), yA = a.getY();
        double xE = e.getX(), yE = e.getY();
        double xF = f.getX(), yF = f.getY();

        // Вычисление барицентрических координат
        double alpha = ((yE - yF)*(x - xF) + (xF - xE)*(y - yF)) /
                ((yE - yF)*(xA - xF) + (xF - xE)*(yA - yF));
        double beta = ((yF - yA)*(x - xF) + (xA - xF)*(y - yF)) /
                ((yE - yF)*(xA - xF) + (xF - xE)*(yA - yF));
        double gamma = 1.0 - alpha - beta;

        return alpha >= 0 && beta >= 0 && gamma >= 0;
    }
}