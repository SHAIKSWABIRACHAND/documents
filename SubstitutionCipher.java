import java.util.*;
public class SubstitutionCipher {
 // Method to encrypt text using substitution key
 public static String encrypt(String text, String key) {
 StringBuilder result = new StringBuilder();
 key = key.toUpperCase();
 for (char ch : text.toCharArray()) {
 if (Character.isUpperCase(ch)) {
 int index = ch - 'A';
 result.append(key.charAt(index));
 } else if (Character.isLowerCase(ch)) {
 int index = ch - 'a';
 result.append(Character.toLowerCase(key.charAt(index)));
 } else {
 result.append(ch); // keep spaces, numbers, symbols
 }
 }
 return result.toString();
 }
 // Method to decrypt text using substitution key
 public static String decrypt(String cipher, String key) {
 StringBuilder result = new StringBuilder();
 key = key.toUpperCase();
char[] reverseKey = new char[26];
 for (int i = 0; i < 26; i++) {
 reverseKey[key.charAt(i) - 'A'] = (char) ('A' + i);
 }
 for (char ch : cipher.toCharArray()) {
 if (Character.isUpperCase(ch)) {
 int index = ch - 'A';
 result.append(reverseKey[index]);
 } else if (Character.isLowerCase(ch)) {
 int index = Character.toUpperCase(ch) - 'A';
 result.append(Character.toLowerCase(reverseKey[index]));
 } else {
 result.append(ch);
 }
 }
 return result.toString();
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter 26-letter substitution key (e.g.,QWERTYUIOPASDFGHJKLZXCVBNM): ");
 String key = sc.nextLine();
 // Validate key
 if (key.length() != 26 || !key.matches("[A-Za-z]+")) {
    System.out.println("Invalid key! Key must be 26 alphabetic characters.");

 }
 System.out.print("Enter text to encrypt: ");
 String text = sc.nextLine();
 String encrypted = encrypt(text, key);
 String decrypted = decrypt(encrypted, key);
 System.out.println("\n--- Results ---");
 System.out.println("Key Used: " + key);
 System.out.println("Encrypted Text: " + encrypted);
 System.out.println("Decrypted Text: " + decrypted);
 sc.close();
 }
}