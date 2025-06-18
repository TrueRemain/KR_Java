package com.montecarlo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rectangle implements Shape {
    private static final Logger logger = LoggerFactory.getLogger(Rectangle.class);
    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Rectangle(
            @JsonProperty("a") Point a,
            @JsonProperty("b") Point b,
            @JsonProperty("c") Point c,
            @JsonProperty("d") Point d
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public Point getD() {
        return d;
    }

    @Override
    public boolean contains(Point point) {
        double x = point.getX();
        double y = point.getY();
        return (
                x >= a.getX() && x <= c.getX()
                        && y >= a.getY() && y <= c.getY()
        );
    }

    @Override
    public double calculateArea() {
        double width = Math.abs(c.getX() - a.getX());
        double height = Math.abs(c.getY() - a.getY());
        double area = width * height;
        logger.info("Площадь прямоугольника: {}", area);
        return area;
    }
}