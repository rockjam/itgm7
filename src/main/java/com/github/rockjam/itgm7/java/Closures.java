package com.github.rockjam.itgm7.java;

import java.util.ArrayList;
import java.util.List;

public class Closures {

    public List<Integer> closureFilter() {

Integer minValue = 5;
Filter<Integer> filter = new MinFilter(minValue);

List<Integer> ints = new ArrayList<>();
ints.add(1);
ints.add(2);
ints.add(4);
ints.add(6);
ints.add(8);
ints.add(12);
ints.add(8);

List<Integer> filtered = new ArrayList<>();
for (Integer i : ints) {
    if(filter.filter(i)) filtered.add(i);
}
return filtered;
    }

    public class MinFilter implements Filter<Integer> {
        private Integer minValue;

        public MinFilter(Integer minValue) {
            this.minValue = minValue;
        }

        @Override
        public boolean filter(Integer i) {
            return i > minValue;
        }
    }


}
