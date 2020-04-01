package org.excavator.jvm.inside.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaMapDoubleTest {

    @Test
    @DisplayName("testJavaMapToDouble")
    public void testJavaMapToDouble(){

        List<Price> list = new ArrayList<>();
        list.add(new Price(10.0));
        list.add(new Price(20.0));
        list.add(new Price(30.0));

        double sum = list.stream().mapToDouble(Price::getPrice).sum();

        assertEquals(60.0, sum);

    }

}

class Price{
    private double price;

    public Price(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
