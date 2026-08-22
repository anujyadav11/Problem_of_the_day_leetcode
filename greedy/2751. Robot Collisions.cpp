/*********************************************** JAVA **************************************************/

// Optimal Solution - Simulates robot collisions by processing position-sorted robots with a stack, matching right-movers against incoming left-movers and resolving health comparisons.
 
/* "The stack naturally models pending right-moving robots — they only collide when a left-mover appears. Sorting by position first ensures we never miss a collision. 
    The key detail is pushing the surviving right-robot back onto the stack since it may collide with more left-movers ahead." */

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        // index array to sort robots by position without losing original indices
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++)
            indices[i] = i;
        // sort indices by position so collisions are processed left to right
        Arrays.sort(indices, (i, j) -> Integer.compare(positions[i], positions[j]));
        // stack holds indices of right-moving robots waiting for potential collision
        Stack<Integer> stack = new Stack<>();
        for (int currentIndex : indices) {
            if (directions.charAt(currentIndex) == 'R') {
                // moving right — push onto stack as potential future collider
                stack.push(currentIndex);
            } else {
                // moving left — collide with all right-moving robots ahead
                while (!stack.isEmpty() && healths[currentIndex] > 0) {
                    int topIndex = stack.pop();
                    if (healths[topIndex] > healths[currentIndex]) {
                        // right robot wins — reduce its health, left robot dies
                        healths[topIndex]--;
                        healths[currentIndex] = 0;
                        // surviving right robot goes back on stack
                        stack.push(topIndex);
                    } else if (healths[topIndex] < healths[currentIndex]) {
                        // left robot wins — reduce its health, right robot dies
                        healths[currentIndex]--;
                        healths[topIndex] = 0;
                    } else {
                        // equal health — both robots destroy each other
                        healths[currentIndex] = 0;
                        healths[topIndex] = 0;
                    }
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        // collect surviving robots in original order
        for (int i = 0; i < n; i++)
            if (healths[i] > 0)
                result.add(healths[i]);
        return result;
    }
}

// Time Complexity :- O(n log n). for sorting and one pass
// Space Complexity :- O(n). for stack 
