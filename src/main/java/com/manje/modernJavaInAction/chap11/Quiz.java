package com.manje.modernJavaInAction.chap11;

import java.util.Optional;
import java.util.Properties;

import static com.manje.modernJavaInAction.chap11.OptionalMain.findCheapestInsurance;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class Quiz {

    // 11-1
    // 노란 경고가 발생하는 이유는 Optional 타입이 매개 변수가 아닌,
    // 반환 값으로 사용될 목적으로 고안되었기 때문이다.
    public Optional<Insurance> nullSafeFindInsurance(
            Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    // 11-2
    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    // 11-3
    public static int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(Quiz::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return empty();
        }
    }

}
