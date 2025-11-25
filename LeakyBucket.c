#include <stdio.h>
int main() {
 int bucket_size, output_rate, input_packets, stored = 0, time;
 printf("Enter bucket size: ");
 scanf("%d", &bucket_size);
 printf("Enter output rate (leak rate): ");
 scanf("%d", &output_rate);
 printf("Enter number of cycles (time units): ");
 scanf("%d", &time);
 while (time--) {
 printf("\nEnter number of packets arriving: ");
 scanf("%d", &input_packets);
 // Check for overflow condition
 if (stored + input_packets > bucket_size) {
 printf("Bucket overflow! %d packets dropped.\n", (stored +
input_packets) - bucket_size);
 stored = bucket_size;
 } else {
 stored += input_packets;
 }
 printf("Packets currently in bucket: %d\n", stored);
 // Leak / Send packets
 if (stored >= output_rate) {
 stored -= output_rate;
 printf("Packets sent: %d\n", output_rate);
 } else {
    printf("Packets sent: %d\n", stored);
 stored = 0;
 }
 printf("Packets left in bucket after sending: %d\n", stored);
 }
 return 0;
}