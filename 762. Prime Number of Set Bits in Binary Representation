/*********************************************** JAVA **************************************************/

Optimal Solution - Counts numbers in range whose number of set bits is prime using bitCount and primality check.
                   Since max set bits in 32-bit integer is 32, prime check is constant time — overall complexity is linear in range size.

class Solution {
    public int countPrimeSetBits(int left, int right) {
        // Stores count of numbers whose set-bit count is prime
        int count = 0;
        // Iterate through range [left, right]
        for (int i = left; i <= right; i++) {
            // Count number of 1s in binary representation
            int bit = Integer.bitCount(i);
            // If number of set bits is prime → increment count
            if (isPrime(bit)) {
                count++;
            }
        }
        return count;
    }
    // Check if a number is prime
    public boolean isPrime(int n) {
        // Numbers less than 2 are not prime
        if (n < 2) return false;
        // Check divisibility up to sqrt(n)
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

Time Complexity :- O(R).
Space Complexity :- O(1).
