package com.montecarlo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Triangle implements Shape {
    private static final Logger logger = LoggerFactory.getLogger(Triangle.class);
    private final Point a;
    private final Point e;
    private final Point f;

    public Triangle(
            @JsonProperty("a") Point a,
            @JsonProperty("e") Point e,
            @JsonProperty("f") Point f
    ) {
        this.a = a;
        this.e = e;
        this.f = f;
    }

    public Point getA() {
        return a;
    }

    public Point getE() {
        return e;
    }

    public Point getF() {
        return f;
    }

    @Override
    public boolean contains(Point point) {
        double x = point.getX();
        double y = point.getY();
        logger.debug("Проверка точки {} внутри треугольника.", point);
        double xA = a.getX(), yA = a.getY();
        double xE = e.getX(), yE = e.getY();
        double xF = f.getX(), yF = f.getY();
        double alpha = ((yE - yF) * (x - xF) + (xF - xE) * (y - yF))
                / ((yE - yF) * (xA - xF) + (xF - xE) * (yA - yF));
        double beta = ((yF - yA) * (x - xF) + (xA - xF) * (y - yF))
                / ((yE - yF) * (xA - xF) + (xF - xE) * (yA - yF));
        double gamma = 1.0 - alpha - beta;
        return alpha >= 0 && beta >= 0 && gamma >= 0;
    }

    @Override
    public double calculateArea() {
        Point a = getA();
        Point e = getE();
        Point f = getF();
        double ae = Math.sqrt(Math.pow(e.getX() - a.getX(), 2) + Math.pow(e.getY() - a.getY(), 2));
        double ef = Math.sqrt(Math.pow(f.getX() - e.getX(), 2) + Math.pow(f.getY() - e.getY(), 2));
        double fa = Math.sqrt(Math.pow(a.getX() - f.getX(), 2) + Math.pow(a.getY() - f.getY(), 2));
        double s = (ae + ef + fa) / 2;
        return Math.sqrt(s * (s - ae) * (s - ef) * (s - fa));
    }
}