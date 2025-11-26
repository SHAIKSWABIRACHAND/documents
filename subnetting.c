#include <stdio.h>      // 1
#include <stdint.h>     // 2

// Convert dotted IP to 32-bit integer
uint32_t ip_to_int(int a, int b, int c, int d) {      // 5
    return (a<<24) | (b<<16) | (c<<8) | d;            // 6
}

// Convert 32-bit integer to dotted IP
void int_to_ip(uint32_t ip) {                         // 10
    printf("%d.%d.%d.%d",                             // 11
        (ip >> 24) & 255,                             // 12
        (ip >> 16) & 255,                             // 13
        (ip >> 8) & 255,                              // 14
        ip & 255                                      // 15
    );
}

int main() {                                          // 19
    int a,b,c,d,cidr;                                 // 20
    printf("Enter IP in format a.b.c.d/cidr: ");      // 21
    scanf("%d.%d.%d.%d/%d", &a,&b,&c,&d,&cidr);       // 22

    uint32_t ip = ip_to_int(a,b,c,d);                 // 24

    // Subnet mask calculation
    uint32_t mask = (cidr == 0) ? 0 : (~0U << (32 - cidr));  // 27

    // Network and broadcast addresses
    uint32_t network = ip & mask;                     // 30
    uint32_t broadcast = network | ~mask;             // 31

    int hosts = (cidr == 32) ? 1 : ((1 << (32 - cidr)) - 2); // 33

    printf("\n----- Subnetting Result -----\n");      // 35

    printf("Subnet Mask       : ");                   // 37
    int_to_ip(mask);                                  // 38
    printf("\n");                                     // 39

    printf("Network Address   : ");                   // 41
    int_to_ip(network);                               // 42
    printf("\n");                                     // 43

    printf("Broadcast Address : ");                   // 45
    int_to_ip(broadcast);                             // 46
    printf("\n");                                     // 47

    printf("Number of Hosts   : %d\n", hosts);        // 49

    printf("First Host        : ");                   // 51
    int_to_ip(network + 1);                           // 52
    printf("\n");                                     // 53

    printf("Last Host         : ");                   // 55
    int_to_ip(broadcast - 1);                         // 56
    printf("\n");                                     // 57

    return 0;                                         // 59
}