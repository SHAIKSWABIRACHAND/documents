#include <stdio.h>
#include <string.h>
#define FLAG '$' // Frame delimiter for Character Stuffing
#define ESC '\\' // Escape character for Character Stuffing
// Function for Character Stuffing
void characterStuffing(char *input) {
 printf("Character Stuffed Output: %c", FLAG);
 for (int i = 0; i < strlen(input); i++) {
 if (input[i] == FLAG || input[i] == ESC) {
 printf("%c", ESC);
 }
 printf("%c", input[i]);
 }
 printf("%c\n", FLAG);
}
// Function for Bit Stuffing
void bitStuffing(char *input) {
 printf("Bit Stuffed Output: 01111110 "); // Flag sequence for bit stuffing
 int count = 0;
 for (int i = 0; i < strlen(input); i++) {
 if (input[i] == '1') {
    count++;
 printf("1");
 if (count == 5) { // After five consecutive 1s, insert a 0
 printf("0");
 count = 0;
 }
 } else {
 printf("0");
 count = 0;
 }
 }
 printf(" 01111110\n"); // Ending flag sequence
}
int main() {
 int choice;
 char input[100];
 printf("Choose Framing Method:\n1. Character Stuffing\n2. Bit Stuffing\nEnter yourchoice: ");
 scanf("%d", &choice);
 getchar(); // Consume newline character
 printf("Enter the data to be framed: ");
 fgets(input, sizeof(input), stdin);
 input[strcspn(input, "\n")] = 0; // Remove newline character
 if (choice == 1) {
 characterStuffing(input);
 } else if (choice == 2) {
 bitStuffing(input);
 } else {
 printf("Invalid choice!\n");
 }
 return 0;
}