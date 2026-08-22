/*********************************************** JAVA **************************************************/

// Optimal Solution- Check divisibility by extracting each digit and comparing the number against its digit sum plus digit product.
/* “I preserve the original number because I’ll modify n while extracting its digits. For each digit, I add it to the sum and multiply it into the product. 
    After processing all digits, I check whether the original number is divisible by sum + product.” */

class Solution {
    public boolean checkDivisibility(int n) {
        int original = n;
        int sum = 0;
        int prod = 1;
        while (n > 0) {
            // Extract the last digit
            int digit = n % 10;
            // Add digit to sum
            sum += digit;
            // Multiply digit into product
            prod *= digit;
            // Remove the last digit
            n /= 10;
        }
        // n is divisible by digit sum + digit product
        return original % (sum + prod) == 0;
    }
}

// Time Complexity :- O(d). d = numbers or digits.
// Space Complexity :- O(1).
