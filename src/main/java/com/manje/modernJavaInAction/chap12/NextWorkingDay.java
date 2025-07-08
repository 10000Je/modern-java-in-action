package com.manje.modernJavaInAction.chap12;

import java.time.DayOfWeek;
import java.time.temporal.*;

// Quiz: 12-2
// 날짜를 하루씩 다음날로 바꾸며, 토요일 일요일은 스킵하는 TemporalAdjuster
public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        temporal = temporal.plus(1, ChronoUnit.DAYS);
        if(temporal.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.SATURDAY.getValue()
        || temporal.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.SUNDAY.getValue()) {
            temporal = temporal.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }
        return temporal;
    }

}
