package com.montecarlo.service;

import com.montecarlo.model.Point;
import com.montecarlo.model.Rectangle;
import com.montecarlo.model.Triangle;
import com.montecarlo.util.JsonFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MonteCarloServiceTest {

    private MonteCarloService service;
    private Triangle testTriangle;
    private Rectangle testRectangle;

    @Before
    public void setUp() throws IOException {
        service = new MonteCarloService();

        // Загружаем тестовые фигуры из JSON-файлов
        testTriangle = JsonFileReader.readTriangle("triangle.json");
        testRectangle = JsonFileReader.readRectangle("rectangle.json");
    }

    @Test
    public void testCalculateExactTriangleArea() {
        // Площадь треугольника по формуле Герона
        double expectedArea = 12.0; // (0,0), (10,4), (6,0) - площадь = 12
        double actualArea = service.calculateExactTriangleArea(testTriangle);
        assertEquals("Точная площадь треугольника вычислена неверно",
                expectedArea, actualArea, 0.0001);
    }

    @Test
    public void testContainsPointInsideTriangle() {
        Point insidePoint = new Point(5, 2);
        assertTrue("Точка внутри треугольника должна определяться корректно",
                testTriangle.contains(insidePoint));
    }

    @Test
    public void testContainsPointOutsideTriangle() {
        Point outsidePoint = new Point(12, 3);
        assertFalse("Точка вне треугольника должна определяться корректно",
                testTriangle.contains(outsidePoint));
    }

    @Test
    public void testCalculateMonteCarloAreaWithLargePointCount() {
        long pointsCount = 1_000_000L;
        double exactArea = service.calculateExactTriangleArea(testTriangle);
        double monteCarloArea = service.calculateMonteCarloArea(testTriangle, testRectangle, pointsCount);

        // Проверяем, что ошибка не превышает 1%
        double error = Math.abs(monteCarloArea - exactArea) / exactArea * 100;
        assertTrue("Ошибка метода Монте-Карло должна быть меньше 1% при большом количестве точек",
                error < 1.0);
    }

    @Test
    public void testRectangleAreaCalculation() {
        double expectedArea = 56.0; // 14 * 4
        double actualArea = testRectangle.calculateArea();
        assertEquals("Площадь прямоугольника вычислена неверно",
                expectedArea, actualArea, 0.0001);
    }

    @Test
    public void testEdgeCasePointOnTriangleVertex() {
        Point vertexPoint = testTriangle.getA(); // Точка в вершине A (0,0)
        assertTrue("Точка на вершине треугольника должна считаться внутри",
                testTriangle.contains(vertexPoint));
    }

    @Test
    public void testEdgeCasePointOnTriangleEdge() {
        Point edgePoint = new Point(5, 2); // Точка на ребре между A(0,0) и E(10,4)
        assertTrue("Точка на ребре треугольника должна считаться внутри",
                testTriangle.contains(edgePoint));
    }

    @Test
    public void testRectangleContainsAllTriangleVertices() {
        // Проверяем, что все вершины треугольника находятся внутри прямоугольника
        assertTrue(testRectangle.getA().getX() <= testTriangle.getA().getX() &&
                testTriangle.getA().getX() <= testRectangle.getC().getX());
        assertTrue(testRectangle.getA().getY() <= testTriangle.getA().getY() &&
                testTriangle.getA().getY() <= testRectangle.getC().getY());

        // Аналогично для остальных вершин треугольника
        assertTrue(testRectangle.getA().getX() <= testTriangle.getE().getX() &&
                testTriangle.getE().getX() <= testRectangle.getC().getX());
        assertTrue(testRectangle.getA().getY() <= testTriangle.getE().getY() &&
                testTriangle.getE().getY() <= testRectangle.getC().getY());

        assertTrue(testRectangle.getA().getX() <= testTriangle.getF().getX() &&
                testTriangle.getF().getX() <= testRectangle.getC().getX());
        assertTrue(testRectangle.getA().getY() <= testTriangle.getF().getY() &&
                testTriangle.getF().getY() <= testRectangle.getC().getY());
    }
}