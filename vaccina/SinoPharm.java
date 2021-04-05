package hu.nive.ujratervezes.kepesitovizsga.vaccina;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SinoPharm extends Vaccine {

    public SinoPharm(MariaDbDataSource dataSource) {
        super(dataSource);
    }

    public List<Person> getVaccinationList() {
        List<Person> vaccinable = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM vacc WHERE `ChronicDisease` != ? and `Pregnancy` != ? ORDER BY `age`;")) {
            stmt.setString(1, "POSITIVE");
            stmt.setString(2,"PREGNANT");
            vaccinable.addAll(fillList(stmt));
            return vaccinable;
        } catch (SQLException sqlException) {
            throw new IllegalStateException("DataBase error! " + sqlException);
        }
    }

    private List<Person> fillList(PreparedStatement stmt) throws SQLException {
        List<Person> results = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(new Person(rs.getString("name"),
                        rs.getInt("age"),
                        ChronicDisease.valueOf(rs.getString("ChronicDisease")),
                        Pregnancy.valueOf(rs.getString("Pregnancy"))));
            }
        }
        return results;
    }
}
