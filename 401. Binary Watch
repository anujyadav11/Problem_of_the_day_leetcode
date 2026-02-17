/*************************************** java **************************************/

Optimal solution :- Brute-force solution that iterates through all possible hour (0–11) and minute (0–59) combinations.
    								 Counts total set bits using Integer.bitCount() and selects times matching the given LED count.
										 Time complexity is constant O(1) due to fixed watch constraints (720 total combinations).

class Solution {
    // Main function to generate all possible times
    // where the total number of set bits in hour and minute
    // equals the given turnedOn value
    public List<String> readBinaryWatch(int turnedOn) {    
        // List to store all valid time combinations
        List<String> time = new ArrayList<>();
        // Iterate through all possible hours (0–11)
        for(int h = 0; h <= 11; h++){
            // Iterate through all possible minutes (0–59)
            for(int m = 0; m <= 59; m++){
                // Check if total set bits in hour + minute equals turnedOn
                if(bitcount(h) + bitcount(m) == turnedOn){ 
                    // Add formatted time string to result list
                    time.add(formatted(h, m));
                }
            }
        }
        // Return all valid times
        return time;
    }
    // Helper method to count number of 1's in binary representation
    public int bitcount(int value){
        return Integer.bitCount(value); // Built-in method
    }
    // Helper method to format time in "H:MM" format
    public String formatted(int h, int m){
        String formatedTime = h + ":";
        // Add leading zero if minute is less than 10
        if(m < 10){
            formatedTime += "0";
        }
        formatedTime += m;
        return formatedTime;
    }
}

Time Complexity:- O(H × M)
Space Complexity :- O(K) where K = number of valid times