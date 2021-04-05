package hu.nive.ujratervezes.kepesitovizsga.vaccina;

import org.mariadb.jdbc.MariaDbDataSource;

import java.util.List;

public abstract class Vaccine {
   MariaDbDataSource dataSource;

    public Vaccine(MariaDbDataSource dataSource) {
        this.dataSource = dataSource;
    }

    abstract List<Person> getVaccinationList();
}
