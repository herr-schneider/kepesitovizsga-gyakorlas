package hu.nive.ujratervezes.kepesitovizsga.vaccina;

import org.mariadb.jdbc.MariaDbDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pfizer extends Vaccine {

    public Pfizer(MariaDbDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {
        List<Person> vaccinable = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            fillList(vaccinable, stmt, "SELECT * FROM vacc WHERE age >= 65 ORDER BY `Registration`;");
            fillList(vaccinable, stmt, "SELECT * FROM vacc WHERE age < 65 ORDER BY `Registration`;");
            return vaccinable;
        } catch (SQLException sqlException) {
            throw new IllegalStateException("DataBase error! " + sqlException);
        }
    }

    private void fillList(List<Person> vaccinable, Statement stmt, String s) throws SQLException {
        try (ResultSet rs =
                     stmt.executeQuery(s)) {
            while (rs.next()) {
                vaccinable.add(new Person(rs.getString("name"),
                        rs.getInt("age"),
                        ChronicDisease.valueOf(rs.getString("ChronicDisease")),
                        Pregnancy.valueOf(rs.getString("Pregnancy"))));
            }
        }
    }
}

