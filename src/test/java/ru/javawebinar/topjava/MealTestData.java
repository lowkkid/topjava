package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static List<Meal> USER_MEALS = Arrays.asList(
            new Meal(100009,LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
            new Meal(100008,LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(100007,LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(100006, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(100005, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(100004, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(100003, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500)
    );

    public static List<Meal> FILTERED_USER_MEALS = Arrays.asList(
            new Meal(100005, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(100004, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(100003, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500)
    );

    public static List<Meal> ADMIN_MEALS = Arrays.asList(
            new Meal(100010, LocalDateTime.of(2015, Month.JUNE, 1, 14,0), "Админ ланч", 510),
            new Meal(100010, LocalDateTime.of(2015, Month.JUNE, 1, 21,0), "Админ ужин", 1500)
    );

    public static Meal userMeal = new Meal(100003, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);

    public static Meal adminMeal = new Meal(100010, LocalDateTime.of(2015, Month.JUNE, 1, 14,0), "Админ ланч", 510);

    public static int USER_MEAL_ID = 100003;

    public static int ADMIN_MEAL_ID = 100010;

    public static int NOT_FOUND_MEAL_ID = 1;

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2000,Month.JANUARY,1, 0,0,0), "Meal", 2000);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
