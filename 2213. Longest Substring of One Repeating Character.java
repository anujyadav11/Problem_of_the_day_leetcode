/*********************************************** JAVA **************************************************/

// Optimal Solution - Answers longest repeating character queries after point updates using a segment tree storing prefix/suffix/max lengths and boundary characters for O(log n) merge operations.
/* "The boundary character check L.rightChar == R.leftChar is the core of the merge — it determines if the suffix of the left segment and prefix of the right can combine into a longer run. 
    Tracking leftLen and rightLen separately in merge is essential — they tell us if the prefix/suffix spans the entire child segment, enabling extension across the boundary." */

 class Solution {
    static class Node {
        int pre;      // length of longest prefix of equal chars
        int suf;      // length of longest suffix of equal chars
        int maxLen;   // longest repeating substring in this segment
        char leftChar;  // leftmost character
        char rightChar; // rightmost character
        Node() {}
        Node(int pre, int suf, int maxLen, char leftChar, char rightChar) {
            this.pre = pre;
            this.suf = suf;
            this.maxLen = maxLen;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
        }
    }
    int n;
    Node[] segTree;
    // merge two segment nodes — leftLen and rightLen are sizes of left and right segments
    Node merge(Node L, Node R, int leftLen, int rightLen) {
        Node res = new Node();
        res.leftChar = L.leftChar;
        res.rightChar = R.rightChar;
        // prefix extends into right if entire left segment is uniform and chars match
        res.pre = L.pre;
        if (L.pre == leftLen && L.rightChar == R.leftChar)
            res.pre = L.pre + R.pre;
        // suffix extends into left if entire right segment is uniform and chars match
        res.suf = R.suf;
        if (R.suf == rightLen && L.rightChar == R.leftChar)
            res.suf = R.suf + L.suf;
        // max is either within L, within R, or spanning the boundary
        res.maxLen = Math.max(L.maxLen, R.maxLen);
        if (L.rightChar == R.leftChar)
            res.maxLen = Math.max(res.maxLen, L.suf + R.pre);
        return res;
    }
    void buildSegmentTree(int i, int l, int r, String s) {
        if (l == r) {
            // leaf node — single character segment
            segTree[i] = new Node(1, 1, 1, s.charAt(l), s.charAt(l));
            return;
        }
        int mid = l + (r - l) / 2;
        buildSegmentTree(2 * i + 1, l, mid, s);
        buildSegmentTree(2 * i + 2, mid + 1, r, s);
        // merge children to fill internal node
        segTree[i] = merge(segTree[2 * i + 1], segTree[2 * i + 2], mid - l + 1, r - mid);
    }
    void update(int i, int l, int r, int pos, char ch) {
        if (l == r) {
            // update leaf node at position pos
            segTree[i] = new Node(1, 1, 1, ch, ch);
            return;
        }
        int mid = l + (r - l) / 2;
        if (pos <= mid)
            update(2 * i + 1, l, mid, pos, ch);
        else
            update(2 * i + 2, mid + 1, r, pos, ch);
        // recompute internal node after child update
        segTree[i] = merge(segTree[2 * i + 1], segTree[2 * i + 2], mid - l + 1, r - mid);
    }
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        segTree = new Node[4 * n];
        buildSegmentTree(0, 0, n - 1, s);
        int k = queryIndices.length;
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            // apply update then read max from root
            update(0, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            result[i] = segTree[0].maxLen;
        }
        return result;
    }
}

// Time Complexity :- O((n + k) log n) — O(n log n) build, O(log n) per query × k queries.
// Space Complexity :- O(n) — segment tree of size 4n.
