import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class LibraryManagementTest {
	
	private Transaction transaction;
	
	private Book testBook;
	
	private Member testMember;

    @Before
    public void initializingVar() throws Exception {
        transaction = Transaction.getInstance();

        testBook = new Book(100, "testName");

        testMember = new Member(1, "testMemName");
    }
	
	@Test
	public void testBookId()
	{
		try {
			new Book(100, "testBook1");
			new Book(999, "testBook2");
			equals("Valid ID Detected Sucessfully");
		} catch (Exception e) {
			fail("Exception thrown for valid ID");
		}
		
		try
		{
			new Book(99, "testBook");
			fail("Exception thrown for invalid ID");
		}
		catch (Exception e)
		{
			equals("Invalid ID Detected succesfully.");
		}
	}
	
	
	
	@Test
	public void testBorrowReturn()
	{
			assertTrue("Book has been detected as availible initially", testBook.isAvailable());
			
			boolean success = transaction.borrowBook(testBook, testMember);
			assertTrue("Initial borrowing should succeed", success);
			assertFalse("Book should now be unavailible", testBook.isAvailable());
			
			boolean fail = transaction.borrowBook(testBook, testMember);
			assertFalse("Second borrow should fail (already been borrowed)", fail);
			assertFalse("Book should still be unavailible", testBook.isAvailable());
			
			boolean returnSuccess = transaction.returnBook(testBook, testMember);
			assertTrue("Returning book which is out should succeed", returnSuccess);
			assertTrue("Book should now be availible, has been returned", testBook.isAvailable());
			
			boolean returnFail = transaction.returnBook(testBook, testMember);
			assertFalse("Book is already availible, return should fail", returnFail);
			assertTrue("Book should still be availible", testBook.isAvailable());
		}
	}

