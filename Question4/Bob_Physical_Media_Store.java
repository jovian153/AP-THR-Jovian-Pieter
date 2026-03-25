package Question4;

public class Bob_Physical_Media_Store {
 public static void main(String[] args) {
        
        Dvd item1 = new Dvd("Baby be Mine", 1982, 50000, 4);
        Magazine item2 = new Magazine("Nintendo Power #82", 1997, 25000, "Nintendo", 36);
        Vinyl item3 = new Vinyl("Song of The Wind", 1967, 350000, 12);

        item1.getDescription();
        System.out.println();
        item2.getDescription();
        System.out.println();
        item3.getDescription();
        
    }
}
class Media {
    protected String title;
    protected int releaseYear;
    protected double price;

    public Media(String title, int releaseYear, double price) {
        if (title.length() < 255) {
            this.title = title;
        } else {
            this.title = title.substring(0, 254);
        }
        if (releaseYear > 1800 && releaseYear < 2026) {
            this.releaseYear = releaseYear;
        } else {
            this.releaseYear = 2025;
        }
        this.price = price;
    }

    public void getDescription() {
        System.out.println("Title: " + title);
        System.out.println("releaseYear: " + releaseYear);
        System.out.println("Price: " + (int)price); 
    }
}
class Dvd extends Media {
    private double runtime;

    public Dvd(String title, int releaseYear, double price, double runtime) {
        super(title, releaseYear, price);
        if (runtime < 720) {
            this.runtime = runtime;
        } else {
            this.runtime = 719;
        }
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Runtime: " + (int)runtime + " minutes");
    }
}
class Magazine extends Media {
    private String author;
    private int numPages;

    public Magazine(String title, int releaseYear, double price, String author, int numPages) {
        super(title, releaseYear, price);
        if (author.length() < 50) {
            this.author = author;
        } else {
            this.author = author.substring(0, 49);
        }
        this.numPages = numPages;
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Author: " + author);
        System.out.println("Number of Pages: " + numPages);
    }
}
class Vinyl extends Media {
    private int size;

    public Vinyl(String title, int releaseYear, double price, int size) {
        super(title, releaseYear, price);
        if (size <= 12) {
            this.size = size;
        } else {
            this.size = 12;
        }
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Size in inches: " + size);
    }
}
