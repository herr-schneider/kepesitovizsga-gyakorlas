package hu.nive.ujratervezes.kepesitovizsga.dogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DogManager {

    public List<Dog> getCountryByExactDogSpecies(String name) {
        List<Dog> dogList = new ArrayList<>(fileReader());
        List<Dog> result = new ArrayList<>();
        for (Dog search : dogList) {
            if (search.getName().toLowerCase().equals(name.toLowerCase())) {
                result.add(search);
            }
        }
        return result;
    }

    public Map<String, Integer> getDogStatistics() {
        Map<String, Integer> result = new HashMap<>();
        String line;
        try (BufferedReader is =
                     new BufferedReader(
                             new InputStreamReader(Dog.class.getResourceAsStream("/dogs.csv")))) {
            is.readLine();
            while ((line = is.readLine()) != null) {
                String[] temp = line.split(";");
                String key = temp[4];
                if (!result.containsKey(key)) {
                    result.put(key, 1);
                } else {
                    int act = result.get(key);
                    result.replace(key, act + 1);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new IllegalArgumentException("File not found", ioe);
        }
        return result;
    }


    public List<Dog> getDogsInAlphabeticalOrderByName() {
        List<Dog> result = new ArrayList<>(fileReader());
        Collections.sort(result, new Comparator<Dog>() {
            public int compare(Dog o1, Dog o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return result;
    }

    private List<Dog> fileReader() {
        List<Dog> dogs = new ArrayList<>();
        String line;
        try (BufferedReader is =
                     new BufferedReader(
                             new InputStreamReader(Dog.class.getResourceAsStream("/dogs.csv")))) {
            is.readLine();
            while ((line = is.readLine()) != null) {
                String[] temp = line.split(";");
                if (temp.length==8){
                dogs.add(new Dog(temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]));}
                else{System.out.println(temp[0] + ": " + temp.length);}
            }
            return dogs;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new IllegalArgumentException("File not found", ioe);
        }
    }

    public static void main(String[] args) {
        DogManager dm = new DogManager();
        System.out.println(dm.getCountryByExactDogSpecies("KERRY BLUE TERRIER"));
        System.out.println(dm.getDogsInAlphabeticalOrderByName());
        System.out.println(dm.getDogStatistics());
    }
}
