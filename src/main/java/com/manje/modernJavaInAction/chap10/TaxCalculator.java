/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manje.modernJavaInAction.chap10;

import com.manje.modernJavaInAction.chap10.model.Order;
import com.manje.modernJavaInAction.chap10.model.Tax;

import java.util.function.DoubleUnaryOperator;

import static com.manje.modernJavaInAction.chap10.mixed.MixedBuilder.*;

public class TaxCalculator {

    public static void main(String[] args) {
        Order order = forCustomer("BigBank",
                buy(t -> t.quantity(80)
                        .stock("IBM")
                        .on("NYSE")
                        .at(125.00)),
                sell(t -> t.quantity(50)
                        .stock("GOOGLE")
                        .on("NASDAQ")
                        .at(125.00))
        );

        double value = TaxCalculator.calculate(order, true, false, true);
        System.out.printf("Boolean arguments: %.2f%n", value);

        value = new TaxCalculator()
                .withTaxRegional()
                .withTaxSurcharge()
                .calculate(order);
        System.out.printf("Method Chaining: %.2f%n", value);

        value = new TaxCalculator()
                .with(Tax::regional)
                .with(Tax::surcharge)
                .calculateF(order); // calculate 와 혼동하지 않게 조심
        System.out.printf("Method references: %.2f%n", value);
    }

    // 세금을 계산 하는 함수... 이를 직접 호출해서 사용하면 불편함
    public static double calculate(Order order, boolean useRegional, boolean useGeneral, boolean useSurcharge) {
        double value = order.getValue();
        if (useRegional) {
            value = Tax.regional(value);
        }
        if (useGeneral) {
            value = Tax.general(value);
        }
        if (useSurcharge) {
            value = Tax.surcharge(value);
        }
        return value;
    }

    // 인스턴스 변수를 이용해서 메서드 체이닝으로 계산할 수 있게 구현
    private boolean useRegional;
    private boolean useGeneral;
    private boolean useSurcharge;

    public TaxCalculator withTaxRegional() {
        useRegional = true;
        return this;
    }

    public TaxCalculator withTaxGeneral() {
        useGeneral = true;
        return this;
    }

    public TaxCalculator withTaxSurcharge() {
        useSurcharge = true;
        return this;
    }

    public double calculate(Order order) {
        return calculate(order, useRegional, useGeneral, useSurcharge);
    }

    // 람다 표현식 또는 메서드 참조를 이용하여 동작을 파라미터로 받을 수 있도록 구현
    public DoubleUnaryOperator taxFunction = d -> d;

    public TaxCalculator with(DoubleUnaryOperator f) {
        taxFunction = taxFunction.andThen(f);
        return this;
    }

    public double calculateF(Order order) {
        return taxFunction.applyAsDouble(order.getValue());
    }

}
