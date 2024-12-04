import static org.junit.Assert.*;


import org.junit.Test;

public class LibraryManagementTest {
	private Transaction transaction;

	@Test
	public void testBookId()
	{
		try {
			new Book(100, "testBook1");
			new Book(999, "testBook2");
			equals("Valid ID Detected Sucessfully");
		} catch (Exception e) {
			fail("Exception thrown for valid IDs");
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

}
