package Question5;

import java.util.Scanner;
import java.util.Stack;

public class Library_of_Alexandria_Restoration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Book> library = new Stack<>();

        library.push(new Book("Why Black Moves First", "Wesley So", 2025));
        library.push(new GeneralBook("Inside Black Mesa", "Dr. Isaac Kleiner", 1997, "Documentary"));
        library.push(new ChildrenBook("Got Science?", "Rachel Dawes", 2015, 5, true));
        library.push(new Book("The Art of War", "Sun Tzu", 1990));
        library.push(new GeneralBook("Dune", "Frank Herbert", 1965, "Sci-Fi"));

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== Library of Alexandria ===");
            System.out.println("1. View All Books");
            System.out.println("2. Add New Book");
            System.out.println("3. Delete a Book");
            System.out.println("4. Exit");
            System.out.print("Choose option (1-4): ");
            
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Stored Books ---");
                    if (library.isEmpty()) {
                        System.out.println("The library is empty.");
                    } else {
                        for (Book b : library) {
                            b.getInfo();
                            System.out.println();
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- Add New Book ---");
                    System.out.println("Select Book Type:");
                    System.out.println("1. Standard Book");
                    System.out.println("2. General Book (with Genre)");
                    System.out.println("3. Children Book (with Age & Visuals)");
                    System.out.print("Choice (1-3): ");
                    int bookType = sc.nextInt();
                    sc.nextLine(); // Membersihkan sisa enter

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Year (1801 - 2025): ");
                    int year = sc.nextInt();
                    sc.nextLine(); // Membersihkan sisa enter

                    // Percabangan untuk membuat objek sesuai pilihan tipe buku
                    if (bookType == 1) {
                        library.push(new Book(title, author, year));
                        System.out.println("Standard Book successfully added!");
                        
                    } else if (bookType == 2) {
                        System.out.print("Enter Genre (max 30 chars): ");
                        String genre = sc.nextLine();
                        library.push(new GeneralBook(title, author, year, genre));
                        System.out.println("General Book successfully added!");
                        
                    } else if (bookType == 3) {
                        System.out.print("Enter Minimum Age (4 - 11): ");
                        int minAge = sc.nextInt();
                        System.out.print("Has Visualisation? (true/false): ");
                        boolean hasVis = sc.nextBoolean();
                        library.push(new ChildrenBook(title, author, year, minAge, hasVis));
                        System.out.println("Children Book successfully added!");
                        
                    } else {
                        System.out.println("Invalid type! Canceling add book.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- Delete Book ---");
                    System.out.print("Enter the EXACT title to delete: ");
                    String targetTitle = sc.nextLine();
                    Stack<Book> tempStack = new Stack<>();
                    boolean isFound = false;
                    while (!library.isEmpty()) {
                        Book currentBook = library.pop();
                        if (currentBook.title.equalsIgnoreCase(targetTitle)) {
                            isFound = true;
                            break; 
                        } else {
                            tempStack.push(currentBook);
                        }
                    }
                    while (!tempStack.isEmpty()) {
                        library.push(tempStack.pop());
                    }
                    if (isFound) {
                        System.out.println("Success: '" + targetTitle + "' has been deleted.");
                    } else {
                        System.out.println("Error: Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("Closing library doors. Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        sc.close();
    }
}

class Book {
    protected String title;
    protected String author;
    protected int year;

    public Book(String title, String author, int year) {
        if (title.length() < 255) {
            this.title = title;
        } else {
            this.title = title.substring(0, 254);
        }
        if (author.length() < 50) {
            this.author = author;
        } else {
            this.author = author.substring(0, 49);
        }
        if (year > 1800 && year < 2026) {
            this.year = year;
        } else {
            this.year = 2025;
        }
    }

    public void getInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year of Publication: " + year);
    }
}

class GeneralBook extends Book {
    private String genre;

    public GeneralBook(String title, String author, int year, String genre) {
        super(title, author, year);
        if (genre.length() <= 30) {
            this.genre = genre;
        } else {
            this.genre = genre.substring(0, 30);
        }
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Genre: " + genre);
    }
}

class ChildrenBook extends Book {
    private int minAge;
    private boolean hasVisualisation;

    public ChildrenBook(String title, String author, int year, int minAge, boolean hasVisualisation) {
        super(title, author, year);
        if (minAge > 3 && minAge < 12) {
            this.minAge = minAge;
        } else {
            this.minAge = 5;
        }
        this.hasVisualisation = hasVisualisation;
    }
    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Minimum Age: " + minAge);
        if (hasVisualisation) {
            System.out.println("Has Visualisation: Yes");
        } else {
            System.out.println("Has Visualisation: No");
        }
    }
}