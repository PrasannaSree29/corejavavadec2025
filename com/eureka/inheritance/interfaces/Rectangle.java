package com.eureka.inheritance.interfaces;

import java.math.BigDecimal;

public class Rectangle implements Shape{
    private BigDecimal length;
    private BigDecimal width;

    private Rectangle(){}
    public Rectangle(BigDecimal length,BigDecimal width){
        this.length=length;
        this.width=width;
    }

    public BigDecimal getLength(){
        return length;
    }
    public BigDecimal getWidth(){
        return width;
    }

    @Override
    public BigDecimal calculateArea(){
        return length.multiply(width);
    }

}
