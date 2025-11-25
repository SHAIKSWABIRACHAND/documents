import java.util.Scanner;
public class HillCipher {
 // Function to multiply matrices (2x2 key with 2x1 vector)
 private static int[] multiplyMatrix(int[][] key, int[] vector) {
 int[] result = new int[2];
 result[0] = (key[0][0] * vector[0] + key[0][1] * vector[1]) % 26;
 result[1] = (key[1][0] * vector[0] + key[1][1] * vector[1]) % 26;
 return result;
 }
 // Function to find modular inverse of determinant mod 26
 private static int modInverse(int det) {
 det = det % 26;
 for (int x = 1; x < 26; x++) {
 if ((det * x) % 26 == 1)
 return x;
 }
 return -1; // inverse doesn't exist
 }
 // Function to compute inverse of 2x2 key matrix
 private static int[][] invertKey(int[][] key) {
 int det = key[0][0] * key[1][1] - key[0][1] * key[1][0];
 det = (det % 26 + 26) % 26;
 int invDet = modInverse(det);
 if (invDet == -1) {
 throw new RuntimeException("Inverse doesn't exist for this key matrix!");
 }
 int[][] invKey = new int[2][2];
 invKey[0][0] = (key[1][1] * invDet) % 26;
 invKey[1][1] = (key[0][0] * invDet) % 26;
 invKey[0][1] = ((-key[0][1] + 26) * invDet) % 26;
 invKey[1][0] = ((-key[1][0] + 26) * invDet) % 26;
 return invKey;
 }
 // Encrypt function
 public static String encrypt(String plaintext, int[][] key) {
 plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
 if (plaintext.length() % 2 != 0)
 plaintext += "X";
 StringBuilder cipher = new StringBuilder();
 for (int i = 0; i < plaintext.length(); i += 2) {
 int[] vector = {
 plaintext.charAt(i) - 'A',
 plaintext.charAt(i + 1) - 'A'
 };
 int[] result = multiplyMatrix(key, vector);
 cipher.append((char) (result[0] + 'A'));
 cipher.append((char) (result[1] + 'A'));
 }
 return cipher.toString();
 }
 // Decrypt function
 public static String decrypt(String ciphertext, int[][] key) {
 ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");
int[][] invKey = invertKey(key);
StringBuilder plain = new StringBuilder();
 for (int i = 0; i < ciphertext.length(); i += 2) {
 int[] vector = {
 ciphertext.charAt(i) - 'A',
 ciphertext.charAt(i + 1) - 'A'
 };
 int[] result = multiplyMatrix(invKey, vector);
 plain.append((char) (result[0] + 'A'));
 plain.append((char) (result[1] + 'A'));
 }
 return plain.toString();
 }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.println("Hill Cipher (2x2 Matrix)");
 System.out.println("Enter 2x2 key matrix (values 0–25):");
 int[][] key = new int[2][2];
 for (int i = 0; i < 2; i++)
 for (int j = 0; j < 2; j++)
 key[i][j] = sc.nextInt();
 sc.nextLine(); // clear buffer
 System.out.print("Enter plaintext: ");
 String plaintext = sc.nextLine();
 String encrypted = encrypt(plaintext, key);
 String decrypted = decrypt(encrypted, key);
 System.out.println("\n--- Results ---");
 System.out.println("Encrypted Text: " + encrypted);
 System.out.println("Decrypted Text: " + decrypted);
 sc.close();
 }
}