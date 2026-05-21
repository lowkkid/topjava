package ru.javawebinar.topjava.service;

import static ru.javawebinar.topjava.util.MealsUtil.getFilteredTos;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Collection<MealTo> getAll(int userId, int caloriesPerDay, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return getFilteredTos(repository.getAll(userId, startDate, endDate), caloriesPerDay, startTime, endTime);
    }

    public Meal get(int userId, int mealId) {
        return checkNotFound(repository.get(userId, mealId), "userId=" + userId + " mealId=" + mealId);
    }

    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    public void update(int userId, Meal meal) {
        checkNotFound(repository.save(userId, meal), meal.getId());
    }

    public void delete(int userId, int mealId) {
        checkNotFound(repository.delete(userId, mealId), "userId=" + userId + " mealId=" + mealId);
    }

}