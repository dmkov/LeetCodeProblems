package hash_table.easy.count_primes_204;

/**
 204. Count Primes
 https://leetcode.com/problems/count-primes/

 Count the number of prime numbers less than a non-negative number, n.

 Example:
 Input: 10
 Output: 4

 Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */

class Solution {
    public int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            notPrimes[i] = false;
        }

        for (int i = 2; i * i < n; i++) {
            if (notPrimes[i]) continue;
            for (int j = i; j * i < n; j++) {
                notPrimes[j * i] = true;
            }
        }

        int result = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrimes[i]) {
                result++;
            }
        }

        return result;
    }

//  More standard solution:
//    public int countPrimes(int n) {
//        int count = 0;
//        for (int i = 2; i<n; i++) {
//            if (isPrime(i)) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    public boolean isPrime(int i) {
//        for (int j = 2; j*j<=i; j++) {
//            if (i%j == 0) {
//                return false;
//            }
//        }
//        return true;
//    }
}
