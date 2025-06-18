package com.montecarlo.model;
//test
public interface Shape {
    /**
     * Проверяет, принадлежит ли данная точка фигуре.
     *
     * @param point проверяемая точка
     * @return true, если точка находится внутри фигуры, иначе false
     */
    boolean contains(Point point);

    /**
     * Вычисляет точную площадь фигуры.
     *
     * @return площадь фигуры
     */
    double calculateArea();
}
