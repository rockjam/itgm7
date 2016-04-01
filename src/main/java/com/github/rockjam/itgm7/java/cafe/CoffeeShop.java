package com.github.rockjam.itgm7.java.cafe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {

    public Coffee buyCoffee(CreditCard cc) {
        Coffee cup = new Coffee();
        cc.charge(cup.getPrice());
        return cup;
    }

    // можно ли переиспользовать buyCoffee для того чтобы купить 5 чашек кофе по одной карте?
    // нет. Нужно повторять логику
    public List<Coffee> buyCoffees(Integer coffeeCount, CreditCard cc) {
        BigDecimal sum = BigDecimal.ZERO;
        final List<Coffee> cups = new ArrayList<>();
        for (int i = 0; i < coffeeCount; i++) {
            Coffee cup = new Coffee();
            cups.add(cup);
            sum = sum.add(cup.getPrice());
        }
        //единственный способ сделать charge
        cc.charge(sum);
        return cups;
    }

    public Coffee buyCoffeeViaBuyCoffees(CreditCard cc) {
        return buyCoffees(1, cc).get(0);
    }

}
