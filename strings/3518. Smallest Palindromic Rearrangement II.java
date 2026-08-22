/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds K-th lexicographically smallest palindrome by greedily selecting first-half characters using multinomial coefficient counting with early capping at MAX_K.
/* "K-th permutation problems follow the same greedy pattern — at each position, count permutations starting with each candidate character, subtract until you find where K falls. 
    Capping at MAX_K prevents overflow while maintaining comparison correctness. The palindrome structure halves the problem — only the first half needs K-th permutation logic." */

class Solution {
    static final long MAX_K = 1_000_001;

    public String smallestPalindrome(String inputStr, int K) {
        // count character frequencies
        int[] freq = new int[26];
        for (char ch : inputStr.toCharArray())
            freq[ch - 'a']++;
        // find and extract the middle character for odd-length palindromes
        char mid = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                mid = (char) ('a' + i);
                freq[i]--;
                break;
            }
        }
        // build half-frequency array for first half permutations
        int[] halfFreq = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
            halfLen += halfFreq[i];
        }
        // check if K-th permutation exists
        long totalPerms = multinomial(halfFreq);
        if (K > totalPerms) return "";
        // greedily build first half — pick smallest char that keeps rank <= K
        StringBuilder firstHalf = new StringBuilder();
        for (int i = 0; i < halfLen; i++) {
            for (int c = 0; c < 26; c++) {
                if (halfFreq[c] > 0) {
                    halfFreq[c]--;
                    long perms = multinomial(halfFreq);
                    if (perms >= K) {
                        // choosing char c keeps us within rank K
                        firstHalf.append((char) ('a' + c));
                        break;
                    } else {
                        // skip all perms starting with c and reduce K
                        K -= perms;
                        halfFreq[c]++;
                    }
                }
            }
        }
        // mirror first half to form complete palindrome
        String rev = new StringBuilder(firstHalf).reverse().toString();
        return mid == 0 ? firstHalf + rev : firstHalf + mid + rev;
    }

    // multinomial coefficient = total! / (count1! * count2! * ...)
    public long multinomial(int[] counts) {
        int total = 0;
        for (int cnt : counts) total += cnt;
        long res = 1;
        for (int i = 0; i < 26; i++) {
            res = res * binom(total, counts[i]);
            if (res >= MAX_K) return MAX_K;
            total -= counts[i];
        }
        return res;
    }

    // binomial coefficient C(n, k) computed iteratively
    public long binom(int n, int k) {
        if (k > n) return 0;
        if (k > n - k) k = n - k;
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
            if (result >= MAX_K) return MAX_K;
        }
        return result;
    }
}

// Time Complexity :- O(n + halfLen × 26 × 26). — frequency counting plus greedy selection with multinomial per position
// Space Complexity :- O(n). — StringBuilder stores first half
