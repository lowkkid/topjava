package ru.javawebinar.topjava.repository.inmemory;

import static java.util.Collections.singletonList;
import static java.util.Comparator.comparing;
import static ru.javawebinar.topjava.util.DateTimeUtil.isBetweenClosed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepository implements MealRepository {

    private final Map<Integer, List<Meal>> userMeals = new ConcurrentHashMap<>();

    private final AtomicInteger count = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(1, meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(count.incrementAndGet());
            userMeals.merge(userId, new CopyOnWriteArrayList<>(singletonList(meal)), (existingMeals, newMeal) -> {
                existingMeals.addAll(newMeal);
                return existingMeals;
            });
            return meal;
        }
        // handle case: update, but not present in storage
        List<Meal> meals = userMeals.get(userId);
        if (meals != null) {
            synchronized (meals) {
                return meals.stream()
                        .filter(m -> m.getId().equals(meal.getId()))
                        .findFirst()
                        .map(existingMeal -> {
                            meals.set(meals.indexOf(existingMeal), meal);
                            return meal;
                        })
                        .orElse(null);
            }
        }
        return null;
    }

    @Override
    public boolean delete(int userId, int id) {
        List<Meal> meals = userMeals.get(userId);
        return meals != null && meals.removeIf(meal -> meal.getId().equals(id));
    }

    @Override
    public Meal get(int userId, int id) {
        return userMeals.get(userId).stream()
                .filter(meal -> meal.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        Collection<Meal> meals = userMeals.get(userId);
        return meals == null ? Collections.emptyList() : meals.stream()
                .filter(meal -> isBetweenClosed(meal.getDate(), startDate, endDate))
                .sorted(comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toCollection(ArrayList::new));

    }
}

