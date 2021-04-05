package hu.nive.ujratervezes.kepesitovizsga;

public class NumberOfDigits {

    public int getNumberOfDigits(int input) {
        String temp="";
        for (int i = 1; i <= input; i++) {
            temp += String.valueOf(i);
            System.out.println(i);
        }
        return temp.toCharArray().length;
    }
}
