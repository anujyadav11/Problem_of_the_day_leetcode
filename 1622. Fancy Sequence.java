/*********************************************** JAVA **************************************************/

// Optimal Solution - Maintain lazy affine transformations using modular arithmetic to support append, addAll, and multAll in constant time.

class Fancy {
    // Modulo value
    long M = 1_000_000_007;
    // Stores normalized sequence values
    List<Long> seq = new ArrayList<>();
    // Global addition applied to the sequence
    long add = 0;
    // Global multiplication applied to the sequence
    long mult = 1;
    // Fast modular exponentiation (a^b % M)
    long power(long a, long b) {
        if (b == 0)
            return 1;
        long half = power(a, b / 2);
        long result = (half * half) % M;
        // If exponent is odd multiply once more
        if (b % 2 == 1) {
            result = (result * a) % M;
        }
        return result;
    }
    public Fancy() {
        // Constructor does nothing special
    }
    public void append(int val) {
        /*
        We store the normalized value by reversing the
        current transformation (mult and add).
        */
        long x = ((val - add) % M + M) * power(mult, M - 2) % M;
        seq.add(x);
    }
    public void addAll(int inc) {
        // Update global addition lazily
        add = (add + inc) % M;
    }
    public void multAll(int m) {
        // Update global multiplier
        mult = (mult * m) % M;
        // Addition must also scale after multiplication
        add = (add * m) % M;
    }
    public int getIndex(int idx) {
        // If index out of bounds
        if (idx >= seq.size())
            return -1;
        // Apply transformation to stored normalized value
        return (int) ((seq.get(idx) * mult + add) % M);
    }
}

// Time Complexity :- O(log m ).
// Space Complexity :- O(n).
