package hu.nive.ujratervezes.kepesitovizsga.vaccina;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AstraZeneca extends Vaccine {

    public AstraZeneca(MariaDbDataSource dataSource) {
        super(dataSource);
    }

    public List<Person> getVaccinationList() {
        List<Person> vaccinable = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            fillList(vaccinable, stmt, "SELECT * FROM vacc WHERE `Pregnancy` != 'PREGNANT' AND " +
                    "`ChronicDisease` = 'POSITIVE' ORDER BY `Registration`;");
            fillList(vaccinable, stmt, "SELECT * FROM vacc WHERE" +
                    "`Pregnancy` != 'PREGNANT' AND " +
                    "`ChronicDisease` != 'POSITIVE' ORDER BY `age`;");
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
