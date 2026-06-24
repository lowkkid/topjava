package ru.javawebinar.topjava.repository.jdbc;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

@Repository
@Profile("postgres")
public class PostgresJdbcMealRepository extends AbstractJdbcMealRepository {

    @Autowired
    public PostgresJdbcMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected RowMapper<Meal> getRowMapper() {
        return BeanPropertyRowMapper.newInstance(Meal.class);
    }

    @Override
    protected MapSqlParameterSource getParameterMapForSave(Meal meal, int userId) {
        return new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories())
                .addValue("date_time", meal.getDateTime())
                .addValue("user_id", userId);
    }

    @Override
    protected MapSqlParameterSource getParameterMapForGetBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("startDateTime", startDateTime)
                .addValue("endDateTime", endDateTime);
    }
}
