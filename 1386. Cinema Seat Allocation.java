/*********************************************** JAVA **************************************************/

// Optimal Solution - Maximise cinema families by processing only rows with reservations and checking the three possible 4-seat blocks.
/* “I group reserved seats by row because unaffected rows can always accommodate two families. For each affected row, I check the three possible 4-seat blocks: 2–5, 4–7, and 6–9. 
    If both outer blocks are available, I place two families; otherwise, if any block is available, I place one. This lets me process only the reserved rows rather than all n rows.” */

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Store reserved seats only for affected rows
        Map<Integer, Set<Integer>> mp = new HashMap<>();
        for (int[] reservedSeat : reservedSeats) {
            int row = reservedSeat[0];
            int seat = reservedSeat[1];

            mp.computeIfAbsent(row, k -> new HashSet<>()).add(seat);
        }
        // Every completely empty row can fit 2 families
        int result = (n - mp.size()) * 2;
        for (Map.Entry<Integer, Set<Integer>> entry : mp.entrySet()) {
            Set<Integer> bookedSeats = entry.getValue();
            // Seats 2-5
            boolean groupA =
                !bookedSeats.contains(2) &&
                !bookedSeats.contains(3) &&
                !bookedSeats.contains(4) &&
                !bookedSeats.contains(5);
            // Seats 4-7
            boolean groupB =
                !bookedSeats.contains(4) &&
                !bookedSeats.contains(5) &&
                !bookedSeats.contains(6) &&
                !bookedSeats.contains(7);
            // Seats 6-9
            boolean groupC =
                !bookedSeats.contains(6) &&
                !bookedSeats.contains(7) &&
                !bookedSeats.contains(8) &&
                !bookedSeats.contains(9);
            // Both sides can fit a family
            if (groupA && groupC)
                result += 2;
            // At least one valid group exists
            else if (groupA || groupB || groupC)
                result += 1;
        }
        return result;
    }
}

// Time Complexity :- O(R).
// Space Complexity :- O(R).


// Greedy Solution - Optimize cinema seat allocation using row-level bitmasks and bitwise operations to identify valid 4-seat family blocks.
/* “Instead of storing every reserved seat in a Set, I represent each row’s reservations as an integer bitmask. Bit i represents whether seat i is reserved. 
    I then create masks for the three possible family blocks and use bitwise AND to check whether any required seat is occupied. Since rows without reservations can automatically fit two families, 
    I only process rows present in the map.” */

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // row -> bitmask of reserved seats
        Map<Integer, Integer> mp = new HashMap<>();
        for (int[] reservedSeat : reservedSeats) {
            int row = reservedSeat[0];
            int seat = reservedSeat[1];
            // Mark this seat as reserved
            mp.merge(row, 1 << seat, (a, b) -> a | b);
        }
        // Rows with no reservations can fit 2 families
        int result = (n - mp.size()) * 2;
        // Required seats for each possible family block
        int maskA = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int maskB = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        int maskC = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int bookedSeatsMask = entry.getValue();
            // Block is available if none of its bits are reserved
            boolean groupA = (bookedSeatsMask & maskA) == 0;
            boolean groupB = (bookedSeatsMask & maskB) == 0;
            boolean groupC = (bookedSeatsMask & maskC) == 0;
            // A and C do not overlap, so both can fit
            if (groupA && groupC) {
                result += 2;
            }
            // At least one valid block exists
            else if (groupA || groupB || groupC) {
                result += 1;
            }
        }
        return result;
    }
}

// Time Complexity :- O(R).
// Space Complexity :- O(R).
