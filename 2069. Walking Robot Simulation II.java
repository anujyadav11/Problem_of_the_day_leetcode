/*********************************************** JAVA **************************************************/

// Optimal Solution - Simulates perimeter robot movement in O(1) per step by precomputing all boundary positions with directions and using modulo index advancement.
/*  "Precomputing the perimeter trades O(w+h) space for O(1) operations — ideal when step() is called many times. The subtle corner case is (0,0) — 
    it's added facing East during bottom-row traversal but after a full loop the robot arrives facing South, so we override its direction. The moved flag handles the special initial East direction requirement." */

class Robot {
    // current position index along the perimeter path
    int idx = 0;
    boolean moved = false;
    // stores {x, y, direction} for every cell along the perimeter
    List<int[]> pos = new ArrayList<>();
    public Robot(int width, int height) {
        // bottom row left to right — facing East
        for (int x = 0; x < width; x++)
            pos.add(new int[]{x, 0, 0});
        // right column bottom to top — facing North
        for (int y = 1; y < height; y++)
            pos.add(new int[]{width - 1, y, 1});
        // top row right to left — facing West
        for (int x = width - 2; x >= 0; x--)
            pos.add(new int[]{x, height - 1, 2});
        // left column top to bottom — facing South
        for (int y = height - 2; y > 0; y--)
            pos.add(new int[]{0, y, 3});
        // at (0,0) after full loop robot faces South not East
        pos.get(0)[2] = 3;
    }
    public void step(int num) {
        moved = true;
        // wrap around perimeter using modulo
        idx = (idx + num) % pos.size();
    }
    public int[] getPos() {
        // return current x, y coordinates
        return new int[]{pos.get(idx)[0], pos.get(idx)[1]};
    }
    public String getDir() {
        // before any movement robot always faces East
        if (!moved) return "East";
        int d = pos.get(idx)[2];
        // map direction integer to string
        if (d == 0) return "East";
        else if (d == 1) return "North";
        else if (d == 2) return "West";
        return "South";
    }
}

// Time Complexity :- O(w + h).
// Space Complexity :- O(w + h).
