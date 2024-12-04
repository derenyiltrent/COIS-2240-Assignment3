
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Transaction {
	
	private static Transaction instance;
	
	private Transaction() {};

    // Perform the borrowing of a book
    public static boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public static void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Get the current date and time in a readable format
    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    public static Transaction getTransaction()
    {
    	if(instance == null)
    	{
    		instance = new Transaction();
    	}
    	return instance;
    }
    
    public static void saveTransaction(String transDetails)
    {
    	try (FileWriter writer = new FileWriter("transactions.txt", true);){
			writer.write(transDetails + System.lineSeparator());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static void displayTransactionHistory()
    {
    	File file = new File("transactions.txt");
    	
    	System.out.println("Transaction History");
    	
    	try(BufferedReader br = new BufferedReader(new FileReader(file)))
    	{
    		String printLine;
    		;
    		while((printLine = br.readLine()) != null)
    		{
    			System.out.printf(printLine);
    			System.out.println();
    		}
    	}
    	catch(IOException e)
    	{
    		System.out.println("Error reading transaction file");
    	}
    }
}