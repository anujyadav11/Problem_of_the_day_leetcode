/*********************************************** JAVA **************************************************/

// Optimal Solution - Parses nested brace expressions using recursive descent, combining union and Cartesian-product concatenation with TreeSet-based deduplication and ordering.
/* “I treat the expression as a small grammar with three levels: unit, concatenation, and union. A unit is either a character or a nested brace expression. For concatenation, 
    I take the Cartesian product of the two sets of generated strings. For union, I merge the sets. I use a shared index to parse the expression recursively and 
    TreeSet to automatically remove duplicates and maintain lexicographical ordering.” */

class Solution {
    // The complete expression
    String s;
    // Length of the expression
    int n;
    // Current parsing position
    int idx = 0;
    public List<String> braceExpansionII(String expression) {
        n = expression.length();
        s = expression;
        idx = 0;
        // Parse the complete expression as a union.
        Set<String> st = performUnion();
        // TreeSet keeps results sorted lexicographically.
        return new ArrayList<>(st);
    }
    private Set<String> getUnit() {
        Set<String> result;
        // If the current unit starts with '{',
        // recursively parse everything inside the braces.
        if (s.charAt(idx) == '{') {
            idx++;
            result = performUnion();
        } else {
            // A single alphabet character is a unit.
            result = new TreeSet<>();
            result.add(String.valueOf(s.charAt(idx)));
        }
        // Move past the current character.
        // For a character, this moves past the character.
        // For a brace expression, this moves past the closing '}'.
        idx++;
        return result;
    }
    private Set<String> performConcat() {
        Set<String> result = new TreeSet<>();
        // Empty string is the identity for concatenation.
        // "" + "a" = "a"
        result.add("");
        // Continue while the next part can be concatenated.
        while (idx < n &&
              (s.charAt(idx) == '{' || Character.isLetter(s.charAt(idx)))) {
            // Parse the next unit.
            Set<String> temp = getUnit();
            Set<String> concatResult = new TreeSet<>();
            // Cartesian product:
            // concatenate every existing result
            // with every string from the new unit.
            for (String left : result) {
                for (String right : temp) {
                    concatResult.add(left + right);
                }
            }
            result = concatResult;
        }
        return result;
    }
    private Set<String> performUnion() {
        Set<String> result = new TreeSet<>();
        while (true) {
            // Parse one concatenation group.
            Set<String> temp = performConcat();
            // Union all possible strings.
            result.addAll(temp);
            // If there is a comma, another union
            // expression follows.
            if (idx < n && s.charAt(idx) == ',') {
                idx++;
            } else {
                break;
            }
        }
        return result;
    }
}

// Time Complexity :- O(total generated strings × string length) approximately, with additional TreeSet insertion overhead.
// Space Complexity :- O(total generated strings × string length) for the generated results.
