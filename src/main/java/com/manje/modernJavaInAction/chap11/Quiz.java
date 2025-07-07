package com.manje.modernJavaInAction.chap11;

import java.util.Optional;

import static com.manje.modernJavaInAction.chap11.OptionalMain.findCheapestInsurance;

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

}
