package com.eureka.inheritance.interfaces;

import java.math.BigDecimal;
import java.math.MathContext;

public class PlayingWithShapes {
    public static void main(String[] args) {
        Square square1 = new Square(new BigDecimal("5.0"));
        Rectangle rectangle1 = new Rectangle(new BigDecimal("10.00"), new BigDecimal("8.0"));
        //Another flavor of Polymorphism - Reference variable of interface type can point at any object of a class ipliminting that interface
        Shape rectangle2 = new Rectangle(new BigDecimal("5.0"), new BigDecimal("6.0"));
        Shape circle1 = new Circle(new BigDecimal("6.0"));

        Shape[] shapesArray= new Shape[]{square1, rectangle1, rectangle2, circle1};
        calculateTotalArea(shapesArray);
    }
    private static void calculateTotalArea(Shape[] shapeArray){
        BigDecimal totalArea=BigDecimal.ZERO;
        for(Shape eachShape:shapeArray){
            totalArea=totalArea.add(eachShape.calculateArea());
        }
        System.out.println("total area of all shapes is "+totalArea.round(MathContext.DECIMAL32));
    }

}
