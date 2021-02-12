import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
	
	private PasswordCheckerUtility() {
		
	}
	
	/** 
	 * Compare equality of two passwords
	 * @param password - - password string to be checked for
	 * @param passwordConfirm - - passwordConfirm string to be checked against password for
	 * @throws UnmatchedException - thrown if not same (case sensitive)
	 */
	public static void comparePasswords(java.lang.String password, java.lang.String passwordConfirm) throws UnmatchedException {
		
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException("Passwords do not match");
		}
	}
	
	/**
	 * Compare equality of two passwords
	 * @param password - - password string to be checked for
	 * @param passwordConfirm - - passwordConfirm string to be checked against password for
	 * @return true if both same (case sensitive)
	 */
	public static boolean comparePasswordsWithReturn(java.lang.String password, java.lang.String passwordConfirm) {
		return password.equals(passwordConfirm);
	}
	
	/**
	 * Reads a file of passwords and the passwords that failed the check will be added to an invalidPasswords with the reason
	 * @param passwords - - list of passwords read from a file
	 * @return invalidPasswords - ArrayList of invalid passwords in the correct format
	 */
	public static java.util.ArrayList<java.lang.String> getInvalidPasswords(java.util.ArrayList<java.lang.String> passwords) {
		
		java.util.ArrayList<java.lang.String> invalidPasswords = new java.util.ArrayList<>();
		if (passwords == null) {
			invalidPasswords.add("No passwords in the file");
			return invalidPasswords;
		}
		
		for (java.lang.String next : passwords) {
			
			String blankSpace = " -> ";
			try {
				
				isValidPassword(next);
				
			} catch (Exception e) {
				invalidPasswords.add(next + blankSpace + e.getMessage());
				
			}
		}
		
		return invalidPasswords;
	}
	
	/**
	 * Weak password length check - Password contains 6 to 9 characters , still considers valid, just weak
	 * @param password - - password string to be checked for Sequence requirement
	 * @return true if password contains 6 to 9 characters
	 */
	public static boolean hasBetweenSixAndNineChars(java.lang.String password) {
		return password.length() >= 6 && password.length() <= 9;
	}
	
	/**
	 * Checks the password Digit requirement - Password must contain a numeric character
	 * @param password - - password string to be checked for Digit requirement
	 * @return true if meet Digit requirement
	 * @throws NoDigitException - thrown if does not meet Digit requirement
	 */
	public static boolean hasDigit(java.lang.String password) throws NoDigitException {
		
		for (int i = 0; i < password.length(); i++) {
			
			char charAtI = password.charAt(i);
			if (charAtI >= 48 && charAtI <= 57)
				return true;
		}
		throw new NoDigitException("The password must contain at least one digit");
	}

	/**
	 * Checks the password lowercase requirement - Password must contain a lowercase alpha character
	 * @param password - - password string to be checked for lowercase requirement
	 * @return true if meet lowercase requirement
	 * @throws NoLowerAlphaException - thrown if does not meet lowercase requirement
	 */
	public static boolean hasLowerAlpha(java.lang.String password) throws NoLowerAlphaException {

		for (int i = 0; i < password.length(); i++) {

			char charAtI = password.charAt(i);
			if (charAtI >= 97 && charAtI <= 122)
				return true;
		}
		throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
	}
	
	/**
	 * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
	 * @param password - - password string to be checked for Sequence requirement
	 * @return false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException - thrown if does not meet Sequence requirement
	 */
	public static boolean hasSameCharInSequence(java.lang.String password) throws InvalidSequenceException {
		
		for (int i = 0; i < password.length() - 2; i++) {
			
			java.lang.String sequence = password.substring(i, i + 3);
			if (sequence.charAt(0) == sequence.charAt(1) && sequence.charAt(1) == sequence.charAt(2))
				throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
		}
		return false;
	}
	
	/**
	 * Checks the password SpecialCharacter requirement - Password must contain a Special Character
	 * @param password - - password string to be checked for SpecialCharacter requirement
	 * @return true if meet SpecialCharacter requirement
	 * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
	 */
	public static boolean hasSpecialChar(java.lang.String password) throws NoSpecialCharacterException {
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		if(!matcher.matches())
			return true;
		throw new NoSpecialCharacterException("The password must contain at least one special character");
		
	}
	
	/**
	 * Checks the password alpha character requirement - Password must contain an uppercase alpha character
	 * @param password - - password string to be checked for alpha character requirement
	 * @return true if meet alpha character requirement
	 * @throws NoUpperAlphaException - thrown if does not meet alpha character requirement
	 */
	public static boolean hasUpperAlpha(java.lang.String password) throws NoUpperAlphaException {

		for (int i = 0; i < password.length(); i++) {

			char charAtI = password.charAt(i);
			if (charAtI >= 65 && charAtI <= 90)
				return true;
		}
		throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
	}
	
	/**
	 * Checks the password length requirement - – The password must be at least 6 characters long
	 * @param password - - password string to be checked for length
	 * @return true if meet min length requirement
	 * @throws LengthException - thrown if does not meet min length requirement
	 */
	public static boolean isValidLength(java.lang.String password) throws LengthException {
		
		if (password.length() < 6)
			throw new LengthException("The password must be at least 6 characters long");
		return true;
	}
	
	/**
	 * Return true if valid password (follows all rules from above)
	 * @param password - - string to be checked for validity
	 * @return true if valid password (follows all rules from above)
	 * @throws LengthException - thrown if length is less than 6 characters
	 * @throws NoUpperAlphaException - thrown if no uppercase alphabetic
	 * @throws NoLowerAlphaException - thrown if no lowercase alphabetic
	 * @throws NoDigitException - thrown if no digit
	 * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
	 * @throws InvalidSequenceException - thrown if more than 2 of same character
	 */
	public static boolean isValidPassword(java.lang.String password) throws LengthException, NoUpperAlphaException,
			NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

		isValidLength(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		hasSameCharInSequence(password);
		return true;
	}
	
	/**
	 * Checks if password is valid but between 6 -9 characters
	 * @param password - - string to be checked if weak password
	 * @return true if length of password is between 6 and 9 (inclusive)
	 * @throws WeakPasswordException - thrown if password is weak or if password is invalid
	 */
	public static boolean isWeakPassword(java.lang.String password) throws WeakPasswordException {

		if (hasBetweenSixAndNineChars(password))
			throw new WeakPasswordException("Password is OK but weak");
		return false;
	}
}
