import java.util.Scanner;
public class CaesarCipher {
 // Method to encrypt text
 public static String encrypt(String text, int key) {
 StringBuilder result = new StringBuilder();
 for (char ch : text.toCharArray()) {
 if (Character.isUpperCase(ch)) {
 char c = (char) ((ch - 'A' + key) % 26 + 'A');
 result.append(c);
 } else if (Character.isLowerCase(ch)) {
 char c = (char) ((ch - 'a' + key) % 26 + 'a');
 result.append(c);
 } else {
 result.append(ch); // keep symbols/spaces unchanged
 }
 }
 return result.toString();
    }
 // Method to decrypt text
 public static String decrypt(String cipher, int key) {
 return encrypt(cipher, 26 - (key % 26)); // reverse shift
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.print("Enter text: ");
 String text = sc.nextLine();
 System.out.print("Enter key (shift value): ");
 int key = sc.nextInt();
 String encrypted = encrypt(text, key);
 String decrypted = decrypt(encrypted, key);
 System.out.println("\n--- Results ---");
 System.out.println("Encrypted Text: " + encrypted);
 System.out.println("Decrypted Text: " + decrypted);
 sc.close();
 }
}