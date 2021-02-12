
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	
	ArrayList<String> passwords;
	String pwTooShortFail = "Be$1";
	String pwTooShortPass = "Be$198";
	
	String pwUAlphaFail = "apple#1342";
	String pwUAlphaPass = "Apple#1342";
	
	String pwLAlphaFail = "LOWER#1342";
	String pwLAlphaPass = "Lower#1342";
	
	String weakPassword = "1%Rabbit";
	String strongPassword = "200%Rabbit";
	
	String pwSequenceFail = "Raygun2000!";
	String pwSequencePass = "Raygun200!";
	
	String pwDigitFail = "Zero:Seven";
	String pwDigitPass = "0:Seven";

	String validPassword1 = "(Juno3)";
	String validPassword2 = "R0B0T_BRAiN";
	String validPassword3 = "P@ssw0rd";
	String validPassword4 = "Born@3PM";

	@Before
	public void setUp() throws Exception {
		
		passwords = new ArrayList<>();
		passwords.addAll(Arrays.asList("BossWorld1", "Kal-El-2", "ROBOT_BRAiN", "B0x!"));
		
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidPassword(pwTooShortFail);
			}			
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
		assertTrue(PasswordCheckerUtility.isValidPassword(pwTooShortPass));
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		Throwable exception = Assertions.assertThrows(NoUpperAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidPassword(pwUAlphaFail);
			}			
		});
		assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
		assertTrue(PasswordCheckerUtility.isValidPassword(pwUAlphaPass));
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		Throwable exception = Assertions.assertThrows(NoLowerAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidPassword(pwLAlphaFail);
			}			
		});
		assertEquals("The password must contain at least one lower case alphabetic character", exception.getMessage());
		assertTrue(PasswordCheckerUtility.isValidPassword(pwLAlphaPass));
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		Throwable exception = Assertions.assertThrows(WeakPasswordException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isWeakPassword(weakPassword);
			}			
		});
		assertEquals("Password is OK but weak", exception.getMessage());
		assertFalse(PasswordCheckerUtility.isWeakPassword(strongPassword));
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		Throwable exception = Assertions.assertThrows(InvalidSequenceException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidPassword(pwSequenceFail);
			}			
		});
		assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
		assertTrue(PasswordCheckerUtility.isValidPassword(pwSequencePass));
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		Throwable exception = Assertions.assertThrows(NoDigitException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidPassword(pwDigitFail);
			}			
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());
		assertTrue(PasswordCheckerUtility.isValidPassword(pwDigitPass));
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		assertTrue(PasswordCheckerUtility.isValidPassword(validPassword1));
		assertTrue(PasswordCheckerUtility.isValidPassword(validPassword2));
		assertTrue(PasswordCheckerUtility.isValidPassword(validPassword3));
		assertTrue(PasswordCheckerUtility.isValidPassword(validPassword4));
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		
		ArrayList<String> invalidPasswordsCalculated = PasswordCheckerUtility.getInvalidPasswords(passwords);
		
		assertEquals(invalidPasswordsCalculated.size(), 3);
		assertEquals(invalidPasswordsCalculated.get(0), "BossWorld1 -> The password must contain at least one special character");
		assertEquals(invalidPasswordsCalculated.get(1), "ROBOT_BRAiN -> The password must contain at least one digit");
		assertEquals(invalidPasswordsCalculated.get(2), "B0x! -> The password must be at least 6 characters long");
		
	}
	
}