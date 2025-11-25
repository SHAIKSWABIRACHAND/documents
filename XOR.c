#include <stdio.h>
int main() {
 char str[100]; // Buffer to store user input
 int i = 0;
 printf("Enter a string: ");
 fgets(str, sizeof(str), stdin); // Read input including spaces
 // XOR each character with 0 and display the result
 while (str[i] != '\0') {
 printf("%c", str[i] ^ 0); // XOR with 0 (no change)
 i++;
 }
 return 0;
}