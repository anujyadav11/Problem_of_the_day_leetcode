/******************************************************** JAVA *****************************************************/

Optimal Solution - We convert each row of the matrix into a histogram of consecutive 1s and compute the largest rectangle area for 
                    that histogram using a monotonic stack, keeping track of the maximum area across all rows.

class Solution {
    public int maximalRectangle(char[][] matrix) {
        // Check if the input matrix is empty or contains no rows or columns.
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0; // If any of these conditions is true, return 0.
        }
        int n = matrix.length; // Number of rows in the matrix.
        int m = matrix[0].length; // Number of columns in the matrix.
        int maxArea = 0; // Initialize the maximum area to 0.
        int[] heights = new int[m]; // Create an array to store the heights of bars in the histogram.
        // Loop through each row of the matrix.
        for (int i = 0; i < n; i++) {
            // Loop through each column in the current row.
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    // If the current cell contains '1', increment the corresponding height in the heights array.
                    heights[j] += 1;
                } else {
                    // If the current cell contains '0', reset the corresponding height to 0.
                    heights[j] = 0;
                }
            }
            // Calculate the largest rectangle area for the current histogram and update maxArea if needed.
            int area = largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea; // Return the maximum area of the rectangle.
    }
    // Helper function to calculate the largest rectangle area in a histogram represented by an array of heights.
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>(); // Create a stack to store indices of heights.
        int maxArea = 0; // Initialize the maximum area to 0.
        // Loop through each height, including an additional height of 0 at the end.
        for (int i = 0; i <= heights.length; i++) {
            while (!stack.isEmpty() && (i == heights.length || heights[stack.peek()] >= heights[i])) {
                // While the stack is not empty and the current height is less than or equal to the height at the top of the stack,
                int height = heights[stack.pop()]; // Pop the height at the top of the stack.
                int width = stack.isEmpty() ? i : i - stack.peek() - 1; // Calculate the width of the rectangle.
                maxArea = Math.max(maxArea, height * width); // Update maxArea if a larger rectangle is found.
            }
            stack.push(i); // Push the current index onto the stack.
        }
        return maxArea; // Return the maximum area of a rectangle in the histogram.
    }
}

Time Complexity :- O(M * N).
Space Complexity :- O(M * N).
