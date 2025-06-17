package com.montecarlo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Point {
    private static final Logger logger = LoggerFactory.getLogger(Point.class);
    private final double x;
    private final double y;

    public Point(@JsonProperty("x") double x,
                 @JsonProperty("y") double y) {
        this.x = x;
        this.y = y;
        logger.debug("Создана точка с координатами: ({}, {})", x, y);
    }

    // Геттеры
    public double getX() { return x; }
    public double getY() { return y; }
}