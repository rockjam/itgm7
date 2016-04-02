package com.github.rockjam.itgm7.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Functions {

    //Guava, Play<F>, etc.
    // increment via hand written function
    public static Func<Integer, Integer> inc = new Func<Integer, Integer>() {
        @Override
        public Integer apply(Integer integer) {
            return integer + 1;
        }
    };

    // increment via new lambda syntax
    public static Function<Integer, Integer> lInc = integer -> integer + 1;

    public static Function<Integer, Integer> plusTwo = lInc.andThen(lInc);

    public static Integer foo(Function<Integer, Integer> calc, Integer num) {
        return calc.apply(num);
    }

    static {
        inc.apply(2);
        lInc.apply(1);
        plusTwo.apply(1);
        foo(lInc, 1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("foo");
            }
        });
        new Thread(() -> System.out.println("foo"));


final List<Person> persons = new ArrayList<>();
persons.add(new Person("Homer", "Simpson"));
persons.add(new Person("Marge", "Simpson"));
persons.add(new Person("Mr.", "Burns"));

Collections.sort(persons, new Comparator<Person>() {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
});
Collections.sort(persons, (p1, p2) -> p1.getName().compareTo(p2.getName()));

        final List<RegisteredPerson> registered = new ArrayList<>();
        registered.add(new RegisteredPerson("Homer", "Simpson", "homer@gmail.com", "fatguy"));
        registered.add(new RegisteredPerson("Marge", "Simpson", "marge@gmail.com", "marge"));
        registered.add(new RegisteredPerson("Mr.", "Burns", "burns@gmail.com", "scrudge"));

        removeSecretFields(registered);

    }

    // убираем все секретные данные пользователей.
    // можно использовать FluentIterable и transform в Guava.
    // в Java 8 можно заменить на collect
    static List<Person> removeSecretFields(List<RegisteredPerson> registered) {
        final List<Person> withoutSecrets = new ArrayList<>();
        for (RegisteredPerson p : registered) {
            withoutSecrets.add(new Person(p.getName(), p.getLastName()));
        }
        return withoutSecrets;
    }

}
