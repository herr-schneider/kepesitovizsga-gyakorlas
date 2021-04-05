package hu.nive.ujratervezes.kepesitovizsga.dogs;

public class Dog {
    private String name;
    private String section;
    private String provisional;
    private String country;
    private String url;
    private String image;
    private String pdf;

    public Dog(String name, String section, String provisional,
               String country, String url, String image, String pdf) {
        this.name = name;
        this.section = section;
        this.provisional = provisional;
        this.country = country;
        this.url = url;
        this.image = image;
        this.pdf = pdf;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }

    public String getProvisional() {
        return provisional;
    }

    public String getCountry() {
        return country;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getPdf() {
        return pdf;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
