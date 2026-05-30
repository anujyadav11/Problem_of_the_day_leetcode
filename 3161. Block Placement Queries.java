/*********************************************** JAVA **************************************************/

// Optimal Solution - Answers gap-size queries on obstacle positions using reverse processing with a max Fenwick tree tracking gaps between sorted obstacles in a TreeSet.
/* "Reverse processing converts deletion (hard) into insertion (easy) — a powerful technique for online query problems. The Fenwick tree here stores max gap sizes not sums 
    — Math.max replaces + in both update and query. The partial gap x - preVal handles queries landing within a gap not fully captured by the Fenwick tree." */

class Solution {
    private int[] bt;
    // update Fenwick tree with max value at position x
    private void update(int x, int v) {
        for (; x < bt.length; x += x & -x)
            bt[x] = Math.max(bt[x], v);
    }
    // query max value in range [1, x]
    private int query(int x) {
        int res = 0;
        for (; x > 0; x -= x & -x)
            res = Math.max(res, bt[x]);
        return res;
    }
    public List<Boolean> getResults(int[][] queries) {
        int mx = 50000;
        // sorted set of obstacle positions — 0 and mx as sentinels
        TreeSet<Integer> st = new TreeSet<>();
        st.add(0);
        st.add(mx);
        // pre-add all type-1 obstacle positions for reverse processing
        for (int[] q : queries)
            if (q[0] == 1) st.add(q[1]);
        bt = new int[mx + 1];
        // initialize Fenwick tree with gaps between consecutive obstacles
        int pre = 0;
        for (int x : st) {
            if (x == 0) continue;
            update(x, x - pre);
            pre = x;
        }
        List<Boolean> ans = new ArrayList<>();
        // process queries in reverse — removals become additions
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];
            if (q[0] == 2) {
                int x = q[1];
                int sz = q[2];
                // nearest obstacle at or before x
                int preVal = Optional.ofNullable(st.floor(x)).orElse(0);
                // max gap ending at or before preVal
                int maxSpace = query(preVal);
                // also consider gap between preVal and x
                maxSpace = Math.max(maxSpace, x - preVal);
                ans.add(maxSpace >= sz);
            } else {
                // type 1 — removing obstacle in reverse = adding it back
                int x = q[1];
                int preVal = Optional.ofNullable(st.lower(x)).orElse(0);
                int nxt = Optional.ofNullable(st.higher(x)).orElse(mx);
                // restore gap between preVal and nxt after removing x
                update(nxt, nxt - preVal);
                st.remove(x);
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}

// Time Complexity :- O((n + m) log m) .
// Space Complexity :- O(m).
