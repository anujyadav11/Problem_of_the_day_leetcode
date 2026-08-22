/*********************************************** JAVA **************************************************/

// Optimal Solution - Generates sequential digit numbers in sorted order using BFS, extending each number by appending its next consecutive digit and filtering within [low, high].
/* "There are only 36 sequential digit numbers total — 9 of length 1, 8 of length 2, down to 1 of length 9. BFS from 1-9 generates them in ascending order naturally. 
The extension formula n * 10 + (lastDigit + 1) builds the next sequential number in O(1). Early break on n > high prunes all remaining larger numbers since BFS maintains order." */

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        // BFS queue seeded with all single digits 1-9
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= 9; i++)
            que.add(i);
        while (!que.isEmpty()) {
            int n = que.poll();
            // all further numbers will also exceed high — stop
            if (n > high) break;
            // n is within [low, high] — valid sequential number
            if (n >= low) res.add(n);
            // extend by appending next sequential digit
            int lastDigit = n % 10;
            if (lastDigit < 9)
                que.offer(n * 10 + (lastDigit + 1));
        }
        return res;
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
