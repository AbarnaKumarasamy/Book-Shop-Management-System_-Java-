import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Author {
    private String name;
    private String email;

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

class Book {
    private String id;
    private String title;
    private Author author;
    private int numberOfCopies;

    public Book(String id, String title, Author author, int numberOfCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numberOfCopies = numberOfCopies;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}

class Bookstore {
    private Map<String, Book> books;

    public Bookstore() {
        books = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void removeBook(String bookId) {
        books.remove(bookId);
    }

    public Book getBook(String bookId) {
        return books.get(bookId);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
}

class AuthorManager {
    private Map<String, Author> authors;

    public AuthorManager() {
        authors = new HashMap<>();
    }

    public void addAuthor(Author author) {
        authors.put(author.getName(), author);
    }

    public void removeAuthor(String authorName) {
        authors.remove(authorName);
    }

    public Author getAuthor(String authorName) {
        return authors.get(authorName);
    }

    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }
}

class Admin {
    private Bookstore bookstore;
    private AuthorManager authorManager;

    public Admin() {
        bookstore = new Bookstore();
        authorManager = new AuthorManager();
    }

    public void controlPanel() {
        
        System.out.println("\n\n\t\t\t\tCONTROL PANEL");
        System.out.println("1. ADD BOOK");
        System.out.println("2. DISPLAY BOOKS");
        System.out.println("3. CHECK PARTICULAR BOOK");
        System.out.println("4. UPDATE BOOK");
        System.out.println("5. DELETE BOOK");
        System.out.println("6. ADD AUTHOR");
        System.out.println("7. DISPLAY AUTHORS");
        System.out.println("8. EXIT");
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\t\t\t\tADD BOOKS");
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Book Title: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Author Name: ");
        String authorName = scanner.nextLine();
        System.out.print("Author Email: ");
        String authorEmail = scanner.nextLine();
        System.out.print("No. of Books: ");
        int numberOfCopies = scanner.nextInt();
        scanner.nextLine();

        Author author = authorManager.getAuthor(authorName);
        if (author == null) {
            author = new Author(authorName, authorEmail);
            authorManager.addAuthor(author);
        }

        Book book = new Book(bookId, bookTitle, author, numberOfCopies);
        bookstore.addBook(book);
    }

    public void showBooks() {
        System.out.println("\n\n\t\t\t\tAll BOOKS");
        List<Book> books = bookstore.getAllBooks();
        for (Book book : books) {
            System.out.println("Book ID: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
            System.out.println("Author Name: " + book.getAuthor().getName());
            System.out.println("Author Email: " + book.getAuthor().getEmail());
            System.out.println("No. of Books: " + book.getNumberOfCopies());
            System.out.println();
        }
        if (books.isEmpty()) {
            System.out.println("No books found.");
        }
    }

    public void checkBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\t\t\t\tCheck Particular Book");
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine();

        Book book = bookstore.getBook(bookId);
        if (book != null) {
            System.out.println("Book ID: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
            System.out.println("Author Name: " + book.getAuthor().getName());
            System.out.println("Author Email: " + book.getAuthor().getEmail());
            System.out.println("No. of Books: " + book.getNumberOfCopies());
        } else {
            System.out.println("Book ID Not Found...");
        }
    }

    public void updateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\t\t\t\tUpdate Book Record");
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine();

        Book book = bookstore.getBook(bookId);
        if (book != null) {
            System.out.print("New Book Title: ");
            String newTitle = scanner.nextLine();
            System.out.print("New Author Name: ");
            String newAuthorName = scanner.nextLine();
            System.out.print("New Author Email: ");
            String newAuthorEmail = scanner.nextLine();
            System.out.print("New No. of Books: ");
            int newNumberOfCopies = scanner.nextInt();
            scanner.nextLine();

            Author author = authorManager.getAuthor(newAuthorName);
            if (author == null) {
                author = new Author(newAuthorName, newAuthorEmail);
                authorManager.addAuthor(author);
            }

            book.setNumberOfCopies(newNumberOfCopies);

            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book ID Not Found...");
        }
    }

    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\t\t\t\tDelete a Book");
        System.out.print("Book ID: ");
        String bookId = scanner.nextLine();

        Book book = bookstore.getBook(bookId);
        if (book != null) {
            bookstore.removeBook(bookId);
            System.out.println("Book is Deleted Successfully...");
        } else {
            System.out.println("Book ID Not Found...");
        }
    }

    public void addAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\t\t\t\tADD AUTHORS");
        System.out.print("Author Name: ");
        String authorName = scanner.nextLine();
        System.out.print("Author Email: ");
        String authorEmail = scanner.nextLine();

        Author author = new Author(authorName, authorEmail);
        authorManager.addAuthor(author);
    }

    public void showAuthors() {
        System.out.println("\n\n\t\t\t\tAll AUTHORS");
        List<Author> authors = authorManager.getAllAuthors();
        for (Author author : authors) {
            System.out.println("Author Name: " + author.getName());
            System.out.println("Author Email: " + author.getEmail());
            System.out.println();
        }
        if (authors.isEmpty()) {
            System.out.println("No authors found.");
        }
    }

    public void run() {
            
            Scanner scan = new Scanner(System.in);
            System.out.println("User name : ");
            String Username = scan.next().toUpperCase();
            System.out.println("Password : ");
            String Password = scan.next();
            
            if(Username.equals("ADMIN")&&Password.equals("1234")){
                System.out.println("You have been logged in successfully.");
                
                
        Scanner scanner = new Scanner(System.in);
        int choice;
        

        do {
            controlPanel();
            System.out.print("Enter your choice (1-8): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    showBooks();
                    break;
                case 3:
                    checkBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    addAuthor();
                    break;
                case 7:
                    showAuthors();
                    break;
                case 8:
                    System.out.println("\nThank you for using the Bookshop Management System!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 8);
            }else{
                System.out.println("Invalid credentials");
            }
       
    }
}

class Customer {
    public void run() {
        Scanner scan = new Scanner(System.in);
        String option;
        
        do{
        System.out.println("1 - Purchase Books");
        System.out.println("2 - Generate Bill");
        System.out.println("3 - Exit");
            
        System.out.println("Enter your choice (1-3) : ");
        int choice = scan.nextInt();
        scan.nextLine(); 
            
            switch (choice){
                case 1 :
                    purchaseBooks();
                    break;
                case 2 :
                    generateBill();
                    break;
                case 3 :
                    System.out.println("Exitig....");
                    return;
                default :
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            System.out.println("Do you want to continue? (yes/no)");
            option = scan.nextLine();
        }while(option.equalsIgnoreCase("yes"));
        System.out.println("Exiting....");
    }
    
    public static void purchaseBooks(){
        Scanner scan = new Scanner(System.in);
        String opt;
        
        do{
            System.out.println("---------------------------------");
            System.out.println("\tCategory of books");
            System.out.println("---------------------------------");
            System.out.println("1 - Poetry");
            System.out.println("2 - Fantasy");
            System.out.println("3 - Horror");
            System.out.println("4 - Classics");
            System.out.println("5 - Romance");
            
            System.out.println("What kind of book(s) do you want to purchase?");
            int Category=scan.nextInt();
            String Book_ID;
            int q;
            
            switch(Category){
                case 1 :
            
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\t\t\t\t\t\t\tPoetry");
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("Book_ID\t\tBook_Name\t\t\t\t\tAuthor\t\t\t\tPrice\t\t\t\tNo. of Copies\n");
                    System.out.println("P001\t\tAbove Ground\t\t\t\tClint Smith\t\t\tRs_1000\t\t\t5");
                    System.out.println("P002\t\tThe Sun and Her Flowers\t\tRupi Kaur\t\t\tRs_850\t\t\t4");
                    System.out.println("P003\t\tThe Hurting Kind\t\t\tAda Limon\t\t\tRs_800\t\t\t6");
                    System.out.println("P004\t\tPillow Thoughts\t\t\t\tCoutney Peppernell\tRs_1100\t\t\t3");
                    System.out.println("P005\t\tMilk and Honey\t\t\t\tRupi Kaur\t\t\tRs_900\t\t\t6");
                    
                    System.out.println("What do you want to buy?");
                    Book_ID = scan.next().toUpperCase();
                    
                    if(Book_ID.equals("P001")){
                        System.out.println("Book_Name : Above Ground");
                        System.out.println("Author : Clint Smith");
                        System.out.println("Price : Rs_1000");
                        System.out.println("No. of Copies : 5");
                    }else if(Book_ID.equals("P002")){
                        System.out.println("Book_Name : The Sun and Her Flowers");
                        System.out.println("Author : Rupi Kaur");
                        System.out.println("Price : Rs_850");
                        System.out.println("No. of Copies : 4");
                    }else if(Book_ID.equals("P003")){
                        System.out.println("Book_Name : The Hurting Kind");
                        System.out.println("Author : Ada Limon");
                        System.out.println("Price : Rs_800");
                        System.out.println("No. of Copies : 6");
                    }else if(Book_ID.equals("P004")){
                        System.out.println("Book_Name : Pillow Thoughts");
                        System.out.println("Author : Coutney Peppernell");
                        System.out.println("Price : Rs_1100");
                        System.out.println("No. of Copies : 3");
                    }else if(Book_ID.equals("P005")){
                        System.out.println("Book_Name : Milk and Honey");
                        System.out.println("Author : Rupi Kaur");
                        System.out.println("Price : Rs_900");
                        System.out.println("No. of Copies : 6");
                    }else{
                        System.out.println("Invalid Book_ID");
                    }
                    System.out.println("How many books do you want to buy?");
                    q = scan.nextInt();
                    break;
                    
                case 2 :
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\t\t\t\t\t\t\tFantasy");
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("Book_ID\t\tBook_Name\t\t\t\tAuthor\t\t\t\tPrice\t\tNo. of Copies\n");
                    System.out.println("F001\t\tA Game of Thrones\t\tGeorge RR Martin\tRs_1500\t\t4");
                    System.out.println("F002\t\tThe Colour of Magic\t\tTerry Pratchett\t\tRs_1850\t\t6");
                    System.out.println("F003\t\tThe Lord of the Rings\tJRR Tolkien\t\t\tRs_900\t\t7");
                    System.out.println("F004\t\tThe Name of the Wind\tPatrick Rothfuss\tRs_1000\t\t7");
                    System.out.println("F005\t\tAmerican Gods\t\t\tNeil Gaiman\t\t\tRs_1900\t\t8");
                    
                    System.out.println("What do you want to buy?");
                    Book_ID = scan.next().toUpperCase();
                    
                    if(Book_ID.equals("F001")){
                        System.out.println("Book_Name : A Game of Thrones");
                        System.out.println("Author : George RR Martin");
                        System.out.println("Price : Rs_1500");
                        System.out.println("No. of Copies : 4");
                    }else if(Book_ID.equals("F002")){
                        System.out.println("Book_Name : The Colour of Magic");
                        System.out.println("Author : Terry Pratchett");
                        System.out.println("Price : Rs_1850");
                        System.out.println("No. of Copies : 6");
                    }else if(Book_ID.equals("F003")){
                        System.out.println("Book_Name : The Lord of the Rings");
                        System.out.println("Author : JRR Tolkien");
                        System.out.println("Price : Rs_900");
                        System.out.println("No. of Copies : 7");
                    }else if(Book_ID.equals("F004")){
                        System.out.println("Book_Name : The Name of the Wind");
                        System.out.println("Author : Patrick Rothfuss");
                        System.out.println("Price : Rs_1000");
                        System.out.println("No. of Copies : 7");
                    }else if(Book_ID.equals("F005")){
                        System.out.println("Book_Name : American Gods");
                        System.out.println("Author : Neil Gaiman");
                        System.out.println("Price : Rs_1900");
                        System.out.println("No. of Copies : 8");
                    }else{
                        System.out.println("Invalid Book_ID");
                    }
                    System.out.println("How many books do you want to buy?");
                    q = scan.nextInt();
                    break;
            
                case 3 :   
             
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\t\t\t\t\t\t\t\tHorror");
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("Book_ID\t\tBook_Name\t\t\t\t\tAuthor\t\t\t\tPrice\t\tNo. of Copies\n");
                    System.out.println("H001\t\tThe Stand\t\t\t\t\tStephen King\t\tRs_1700\t\t3");
                    System.out.println("H002\t\tDracula\t\t\t\t\t\tBram Stoker\t\t\tRs_2250\t\t7");
                    System.out.println("H003\t\tFlowers in the Attic\t\tVirginia Andrews\tRs_1400\t\t5");
                    System.out.println("H004\t\tThe Secret History\t\t\tDonna Tartt\t\t\tRs_2000\t\t5");
                    System.out.println("H005\t\tThe Silence of the Lambs\tThomas Harris\t\tRs_2300\t\t6");
                    
                    System.out.println("What do you want to buy?");
                    Book_ID = scan.next().toUpperCase();
                    
                    if(Book_ID.equals("H001")){
                        System.out.println("Book_Name : The Stand");
                        System.out.println("Author : Stephen King");
                        System.out.println("Price : Rs_1700");
                        System.out.println("No. of Copies : 3");
                    }else if(Book_ID.equals("H002")){
                        System.out.println("Book_Name : Dracula");
                        System.out.println("Author : Bram Stoker");
                        System.out.println("Price : Rs_2250");
                        System.out.println("No. of Copies : 7");
                    }else if(Book_ID.equals("H003")){
                        System.out.println("Book_Name : Flowers in the Attic");
                        System.out.println("Author : Virginia Andrews");
                        System.out.println("Price : Rs_1400");
                        System.out.println("No. of Copies : 5");
                    }else if(Book_ID.equals("H004")){
                        System.out.println("Book_Name : The Secret History");
                        System.out.println("Author : Donna Tartt");
                        System.out.println("Price : Rs_2000");
                        System.out.println("No. of Copies : 5");
                    }else if(Book_ID.equals("H005")){
                        System.out.println("Book_Name : The Silence of the Lambs");
                        System.out.println("Author : Thomas Harris");
                        System.out.println("Price : Rs_2300");
                        System.out.println("No. of Copies : 6");
                    }else{
                        System.out.println("Invalid Book_ID");
                    }
                    System.out.println("How many books do you want to buy?");
                    q = scan.nextInt();
                    break;
            
                case 4 :
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\t\t\t\t\t\t\tClassics");
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("Book_ID\t\tBook_Name\t\t\t\tAuthor\t\t\t\tPrice\t\tNo. of Copies\n");
                    System.out.println("C001\t\tPride and Prejudice\t\tJane Austen\t\t\tRs_1240\t\t5");
                    System.out.println("C002\t\tThe Art of War\t\t\tSun Tzu\t\t\t\tRs_1560\t\t4");
                    System.out.println("C003\t\tMeditations\t\t\t\tMarcus Aurelius\t\tRs_1890\t\t7");
                    System.out.println("C004\t\tThe Crucible\t\t\tArthur Miller\t\tRs_2100\t\t6");
                    System.out.println("C005\t\tNight and Day\t\t\tVirginia Woolf\t\tRs_1900\t\t5");
                    
                    System.out.println("What do you want to buy?");
                    Book_ID = scan.next().toUpperCase();
                    
                    if(Book_ID.equals("C001")){
                        System.out.println("Book_Name : Pride and Prejudice");
                        System.out.println("Author : Jane Austen");
                        System.out.println("Price : Rs_1240");
                        System.out.println("No. of Copies : 5");
                    }else if(Book_ID.equals("C002")){
                        System.out.println("Book_Name : The Art of War");
                        System.out.println("Author : Sun Tzu");
                        System.out.println("Price : Rs_1560");
                        System.out.println("No. of Copies : 4");
                    }else if(Book_ID.equals(("C003"))){
                        System.out.println("Book_Name : Meditations");
                        System.out.println("Author : Marcus Aurelius");
                        System.out.println("Price : Rs_1890");
                        System.out.println("No. of Copies : 7");
                    }else if(Book_ID.equals(("C004"))){
                        System.out.println("Book_Name : The Crucible");
                        System.out.println("Author : Arthur Miller");
                        System.out.println("Price : Rs_2100");
                        System.out.println("No. of Copies : 6");
                    }else if(Book_ID.equals(("C005"))){
                        System.out.println("Book_Name : Night and Day");
                        System.out.println("Author : Virginia Woolf");
                        System.out.println("Price : Rs_1900");
                        System.out.println("No. of Copies : 5");
                    }else{
                        System.out.println("Invalid Book_ID");
                    }
                    System.out.println("How many books do you want to buy?");
                    q = scan.nextInt();
                    break;
            
                case 5 :
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t\t\t\t\t\t\t\tRomance");
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("Book_ID\t\tBook_Name\t\t\t\tAuthor\t\t\tPrice\t\t\tNo. of Copies\n");
                    System.out.println("R001\t\tThe Witch is Back\t\tSophie H.Morgan\t\tRs_1400\t\t6");
                    System.out.println("R002\t\tThe Roommate Pact\t\tAllison Ashley\t\tRs_1640\t\t4");
                    System.out.println("R003\t\tBetween Us\t\t\t\tMihari McFarlane\tRs_2100\t\t4");
                    System.out.println("R004\t\tA Duke for Diana\t\tSabrina Jeffries\tRs_2380\t\t6");
                    System.out.println("R005\t\tA Calder at Heart\t\tJanet Dailey\t\tRs_1250\t\t6");
                    
                    System.out.println("What do you want to buy?");
                    Book_ID = scan.next().toUpperCase();
                    
                    if(Book_ID.equals(("R001"))){
                        System.out.println("Book_Name : The Witch is Back");
                        System.out.println("Author : Sophie H.Morgan");
                        System.out.println("Price : Rs_1400");
                        System.out.println("No. of Copies : 6");
                    }else if(Book_ID.equals(("R002"))){
                        System.out.println("Book_Name : The Roommate Pact");
                        System.out.println("Author : Allison Ashley");
                        System.out.println("Price : Rs_1640");
                        System.out.println("No. of Copies : 4");
                    }else if(Book_ID.equals("R003")){
                        System.out.println("Book_Name : Between Us");
                        System.out.println("Author : Mihari McFarlane");
                        System.out.println("Price : Rs_2100");
                        System.out.println("No. of Copies : 4");
                    }else if(Book_ID.equals(("R004"))){
                        System.out.println("Book_Name : A Duke for Diana");
                        System.out.println("Author : Sabrina Jeffries");
                        System.out.println("Price : Rs_2380");
                        System.out.println("No. of Copies : 6");
                    }else if(Book_ID.equals("R005")){
                        System.out.println("Book_Name : A Calder at Heart");
                        System.out.println("Author : Janet Dailey");
                        System.out.println("Price : Rs_1250");
                        System.out.println("No. of Copies : 6");
                    }else{
                        System.out.println("Invalid Book_ID");
                    }
                    System.out.println("How many books do you want to buy?");
                    q = scan.nextInt();
                    break;
            
                    
                default :
                    System.out.println("Invalid number");
                    break;
            }
               
                System.out.println("Do you want to continue buying? (yes/no)");
                opt = scan.next();
        }while(opt.equalsIgnoreCase("yes"));
             
        }
    
    
    public static void generateBill() {
       // Step 1: Read details from a file and store them in an array
        String fileName = "details.txt"; // Replace with your file name
        ArrayList<String> detailsArray = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                detailsArray.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file : "+ e.getMessage());
            return;
        }
        
        // Assuming the details are stored as one line per item: bookID, quantity, price
        // Example: ItemID1,3,1000.00
        //          ItemID2,2,1500.00
        //          ...

        // Step 2: Calculate the bill using details from the array

        double totalBill = 0.0;
        for (String details : detailsArray) {
            String[] parts = details.split(",");
            if (parts.length == 3) {
                String bookID = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                double price = Double.parseDouble(parts[2]);
                double itemTotal = quantity * price;
                totalBill += itemTotal;
                
                System.out.println(bookID + " - Quantity: " + quantity + ", Price: " + price + ", Total: " + itemTotal);
            }
        }

        // Step 3: Write the bill details to a separate file
        String billFileName = "bill.txt"; // Replace with your desired bill file name

        try (PrintWriter writer = new PrintWriter(new FileWriter(billFileName))) {
            writer.println("Bill Details:");
            for (String details : detailsArray) {
                writer.println(details);
            }
            writer.println("Total Bill: " + totalBill);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Total Bill: " + totalBill);
        System.out.println("Bill details written to " + billFileName);
    }
}


public class BookShopManagementSystem{
       
    public static void main(String[] args) {
        displayWelcomeMessage();
        LocalDate date = LocalDate.now();
        System.out.println("Current Date is : "+date);
        System.out.println("1 - Login as an admin");
        System.out.println("2 - Buy as a customer");
        System.out.println("3 - Exit the system");
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 1/2/3 to start the program : ");
        int n=scan.nextInt();
        
        switch(n){
            case 1 :
                System.out.println("Login as an admin");
                Admin admin = new Admin();
                admin.run();
                break;
        
            case 2 :
                System.out.println("Buy as a customer");
                Customer customer = new Customer();
                customer.run();
                break;
        
            case 3 :
                System.out.println("Exit the system");
                break;
                
            default: 
                System.out.println("Invalid number");
                break;
        }
        System.out.println("\t\tThank you for your shopping!");
        System.out.println("\t\t\tCome Again!");
        
        int rows = 7; // Number of rows in the pattern
        int spacesLeft = 30; //Number of spaces on the left side

        for (int i = 1; i <= rows; i++) {
            // Print spaces on the left
            for (int j = 1; j <= spacesLeft; j++) {
                System.out.print(" ");
            }

            // Print "x" characters
            for (int k = 1; k <= i * 2 - 1; k++) {
                System.out.print("x");
            }

            System.out.println(); // Move to the next line
            spacesLeft--; // Decrement spaces for next row
        }
        
    
    }
        public static void displayWelcomeMessage(){
            System.out.println("\t\t\t    OOP Mini Project");
            System.out.println("\n\t\t\t Book Shop Management");
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("\t\t\tWelcome to" .concat(" Lanka Book Shop").toUpperCase());
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("\t\t||          //\\\\     ||     ||  || //     //\\\\                            ");
            System.out.println("\t\t||         //__\\\\    ||\\    ||  ||//     //__\\\\                          ");
            System.out.println("\t\t||        //    \\\\   ||  \\  ||  ||\\\\    //    \\\\                       ");
            System.out.println("\t\t||_____  //      \\\\  ||    \\||  || \\\\  //      \\\\                      ");       
            System.out.println("----------------------------------------------------------------------------------");
        
        }
        
}





