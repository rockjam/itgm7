package com.github.rockjam.itgm7.java.cafe;

import java.math.BigDecimal;

public class Coffee {

    private BigDecimal price;

    public Coffee() {
        this.price = new BigDecimal(100);
    }

    public BigDecimal getPrice() {
        return price;
    }
}
