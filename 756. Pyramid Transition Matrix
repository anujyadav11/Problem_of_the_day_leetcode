class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // Map to store allowed transitions
        // Key: pair of characters (base)
        // Value: list of possible characters that can be placed on top
        HashMap<String, List<Character>> map = new HashMap<>();
        // Build the transition map from allowed list
        for (String s : allowed) {
            String key = s.substring(0, s.length() - 1); // base pair
            char root = s.charAt(s.length() - 1);        // top character
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(root);
        }
        // Start recursive backtracking from the bottom row
        return helper(bottom, map, new HashSet<>());
    }
    // Recursive helper function to check if pyramid can be built
    public boolean helper(String bottom,
                          HashMap<String, List<Character>> map,
                          HashSet<String> seen) {

        // Base case: reached the top of the pyramid
        if (bottom.length() == 1) {
            return true;
        }
        // If this configuration has already been tried and failed
        if (seen.contains(bottom)) {
            return false;
        }
        // Generate all possible next-level strings
        List<String> next = new ArrayList<>();
        getAll(bottom, 0, next, map, new StringBuilder());
        // Try each possible next-level configuration
        for (String s : next) {
            if (helper(s, map, seen)) {
                return true;
            }
        }
        // Mark this configuration as impossible
        seen.add(bottom);
        return false;
    }
    // Generate all valid strings for the next pyramid level
    public void getAll(String s,
                       int idx,
                       List<String> next,
                       HashMap<String, List<Character>> map,
                       StringBuilder sb) {
        // If we've built a full next-level string
        if (idx == s.length() - 1) {
            next.add(sb.toString());
            return;
        }
        // Take two adjacent characters as base
        String base = s.substring(idx, idx + 2);
        // If no transition exists, stop this path
        if (!map.containsKey(base)) {
            return;
        }
        // Try all possible characters on top of this base
        for (char ch : map.get(base)) {
            sb.append(ch);                          // choose
            getAll(s, idx + 1, next, map, sb);      // explore
            sb.deleteCharAt(sb.length() - 1);       // backtrack
        }
    }
}
