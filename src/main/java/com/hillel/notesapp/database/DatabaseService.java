package com.hillel.notesapp.database;

import com.hillel.notesapp.dto.Note;
import com.hillel.notesapp.util.JdbcTemplate;
import com.hillel.notesapp.util.RowMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DatabaseService implements Service {

    private final JdbcTemplate jdbcTemplate;

    {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/notesapp");
        config.setUsername("postgres");
        config.setPassword("0000");
        config.setMaximumPoolSize(8);
        config.setMinimumIdle(4);
        DataSource dataSource = new HikariDataSource(config);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        createTable();
    }

    @Override
    public List<Note> getAllNotes() {
        String sql = "SELECT id, title, description, datetime " +
                "FROM notes ORDER BY title;";
        return jdbcTemplate.query(sql, getRowMapper());
    }

    @Override
    public void delete(int index) {
        String sql = "DELETE FROM notes WHERE id = ?;";
        Object[] params = new Object[]{index};
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void add(String title, String description) {
        String sql = "INSERT INTO notes (title, description, datetime) " +
                "SELECT ?, ?, ? ;";
        Object[] params = new Object[]{
                title,
                description,
                LocalDateTime.now()
        };
        jdbcTemplate.update(sql, params);
    }

    private RowMapper getRowMapper() {
        return resultSet -> {
            try {
                return new Note(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("datetime").toLocalDateTime());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return new Note();
        };
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS notes (" +
                "id SERIAL PRIMARY KEY, " +
                "title VARCHAR(50), " +
                "description VARCHAR(250), " +
                "datetime TIMESTAMP NOT NULL);";
        jdbcTemplate.update(sql);
    }

}
