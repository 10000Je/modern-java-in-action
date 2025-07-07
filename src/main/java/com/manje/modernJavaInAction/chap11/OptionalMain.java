package com.manje.modernJavaInAction.chap11;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class OptionalMain {

    // 전통적인 널 체크 코드를 이용한 방법...코드의 뎁스가 깊어지고 가독성이 떨어진다.
    public String getCarInsuranceNameNullSafeV1(PersonV1 person) {
        if(person != null) {
            CarV1 car = person.getCar();
            if(car != null) {
                Insurance insurance = car.getInsurance();
                if(insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    // 똑같이 널 체크 코드를 이용했지만, 여러 개의 얼리 리턴을 만들어서 뎁스를 유지한 코드
    // 그럼에도 코드가 난잡해지는 문제점이 있다.
    public String getCarInsuranceNameNullSafeV2(PersonV1 person) {
        if(person == null) {
            return "Unknown";
        }
        CarV1 car = person.getCar();
        if(car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if(insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }

  /*
    컴파일되지 않음:
    (1)에서 Optional<Person>에 map(Person::getCar) 호출을 진행하면, Optional<Optional<Car>>가 반환된다.
    왜냐하면, Person::getCar는 Car를 반환하는 것이 아니라 Optional<Car>를 반환하기 때문이다.
    그리고 (2)에서 Optional<Optional<Car>>에 map(Car::getInsurance) 호출을 시도하면,
    Optional<Car>에 직접 Car::getInsurance를 호출한다는 의미이므로 컴파일이 불가능하다.
    이렇게 매핑 함수가 Optional을 반환하는 경우, flatMap 을 통해 평탄화를 진행할 수 있다.

    public String getCarInsuranceName(Person person) {
    Optional<Person> optPerson = Optional.of(person);
    Optional<String> name = optPerson.map(Person::getCar) // (1)
        .map(Car::getInsurance) // (2)
        .map(Insurance::getName);
    return name.orElse("Unknown");
    }
   */

    // flatMap을 사용하여 Optional을 평탄화한 코드
    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    // Optional의 stream 메서드와 Stream의 flatMap 메서드를 이용하여
    // Optional 스트림을 일반 스트림으로 변환하는 코드
    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optInsurance -> optInsurance.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(toSet());
    }

    // Optional을 매개변수로 받아 널 체크를 수행하는 코드, 퀴즈 11-1의 레퍼런스
    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    static Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance(); // 대충 구현했다고 가정
    }

}
