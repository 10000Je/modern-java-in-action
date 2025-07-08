package com.manje.modernJavaInAction.chap12;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeExamples {

    // DateFormat 은 기본적으로 스레드 안전하지 않다. (여러 스레드에서 접근시 문제가 발생할 수 있음)
    // ThreadLocal 로 DateFormat 을 감싸면 스레드에서 이를 사용할 때마다 독립적인 복사본이 생성되고
    // 덕분에 다른 스레드에서의 변경이 영향을 미치지 않게되어 스레드 안전하게 사용할 수 있게 된다.
    private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("dd-MMM-yyyy");
        }
    };

    public static void main(String[] args) {
        useOldDate();
        useLocalDate();
        useTemporalAdjuster();
        useDateFormatter();
    }

    private static void useOldDate() {
        Date date = new Date(114, 2, 18); // deprecated 된 생성자
        System.out.println(date);

        System.out.println(formatters.get().format(date)); // DateFormat 사용, 뭔가 출력이 이상하게 오류남

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.FEBRUARY, 18);
        System.out.println(calendar); // toString() 메서드가 사람이 읽기 힘들게 구현되어 있음
    }

    private static void useLocalDate() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear(); // 2014
        Month month = date.getMonth(); // MARCH
        int day = date.getDayOfMonth(); // 18
        DayOfWeek dow = date.getDayOfWeek(); // TUESDAY
        int len = date.lengthOfMonth(); // 31 (3월의 길이)
        boolean leap = date.isLeapYear(); // false (윤년이 아님)
        System.out.println(date); // 2014-03-18

        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.of(13, 45, 20); // 13:45:20
        int hour = time.getHour(); // 13
        int minute = time.getMinute(); // 45
        int second = time.getSecond(); // 20
        System.out.println(time); // 13:45:20

        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20); // 2014-03-18T13:45
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt1); // 2014-03-18T13:45:20

        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1); // 2014-03-18
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1); // 13:45:20

        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400); // unix epoch time 기준 44년 이후
        Instant now = Instant.now();

        Duration d1 = Duration.between(LocalTime.of(13, 45, 10), time); // 10
        Duration d2 = Duration.between(instant, now);
        System.out.println(d1.getSeconds()); // 10
        System.out.println(d2.getSeconds()); // 현재 시간 - instant

        Duration threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(threeMinutes); // PT3M

        JapaneseDate japaneseDate = JapaneseDate.from(date);
        System.out.println(japaneseDate); // Japanese Heisei 26-03-18
    }

    private static void useTemporalAdjuster() {
        LocalDate date = LocalDate.of(2014, 3, 18); // 2014-03-18
        date = date.with(nextOrSame(DayOfWeek.SUNDAY)); // 현재 날짜를 포함한 처음 만나는 일요일
        System.out.println(date); // 2014-03-23
        date = date.with(lastDayOfMonth()); // 달의 마지막 날
        System.out.println(date); // 2014-03-31

        date = date.with(new NextWorkingDay()); // 다음 평일
        System.out.println(date); // 2014-04-01
        date = date.with(nextOrSame(DayOfWeek.FRIDAY)); // 현재 날짜를 포함한 처음 만나는 금요일
        System.out.println(date); // 2014-04-04
        date = date.with(new NextWorkingDay()); // 다음 날짜, 단 다음 날이 주말이면 스킵
        System.out.println(date); // 2014-04-07

        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date); // 2014-04-11
        date = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            }
            if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(date); // 2014-04-14
    }

    private static void useDateFormatter() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // 2014-03-18
        System.out.println(date.format(formatter)); // 18/03/2014
        System.out.println(date.format(italianFormatter)); // 18. marzo 2014

        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        System.out.println(date.format(complexFormatter)); // 18. marzo 2014
    }

}
