package ru.javawebinar.topjava.web.meal;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.getAuthUserId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Collection<MealTo> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getAll");
        return service.getAll(getAuthUserId(), authUserCaloriesPerDay(), startDate, endDate, startTime, endTime);
    }

    public Meal get(int mealId) {
        log.info("get");
        return service.get(getAuthUserId(), mealId);
    }

    public Meal create(Meal meal) {
        log.info("create");
        return service.create(getAuthUserId(), meal);
    }

    public void update(int mealId, Meal meal) {
        log.info("update");
        assureIdConsistent(meal, mealId);
        service.update(getAuthUserId(), meal);
    }

    public void delete(int mealId) {
        log.info("delete");
        service.delete(getAuthUserId(), mealId);
    }
}