package ru.javawebinar.topjava.repository.datajpa;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    Meal findByIdAndUserId(Integer id, Integer userId);

    @Modifying
    @Transactional
    int deleteByIdAndUserId(Integer id, Integer userId);

    List<Meal> findAllByUserId(Integer userId, Sort sort);

    List<Meal> findAllByUserIdAndDateTimeGreaterThanEqualAndDateTimeLessThan(Integer userId, LocalDateTime startTime, LocalDateTime endTime, Sort sort);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user u JOIN FETCH u.roles WHERE m.id=:id AND u.id=:userId")
    Meal getWithUser(@Param("id") int id, @Param("userId") int userId);
}
