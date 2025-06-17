package com.montecarlo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс точки, представляющий точку на координатной плоскости.
 */
public class Point {
    /** Логгер класса для отладки. */
    private static final Logger logger = LoggerFactory.getLogger(Point.class);

    /** Координата X точки. */
    private final double x;

    /** Координата Y точки. */
    private final double y;

    /**
     * Создает новую точку с заданными координатами.
     *
     * @param x значение координаты X
     * @param y значение координаты Y
     */
    public Point(@JsonProperty("x") double x,
                 @JsonProperty("y") double y) {
        this.x = x;
        this.y = y;
        logger.debug("Создана точка с координатами: ({}, {})", x, y);
    }

    /**
     * Возвращает значение координаты X.
     *
     * @return значение координаты X
     */
    public double getX() { return x; }

    /**
     * Возвращает значение координаты Y.
     *
     * @return значение координаты Y
     */
    public double getY() { return y; }
}