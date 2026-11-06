/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Count Zero Request Servers using offline query sorting, sliding window, two pointers, and frequency counting in O(L log L + Q log Q) time.
/* "I sort the logs by timestamp and process queries in increasing order. I maintain a sliding window using two pointers, adding logs up to the query timestamp and removing logs that fall outside the time interval. 
    A frequency array tracks the number of logs per server, while a separate counter tracks distinct active servers. The answer is the total number of servers minus the active server count. 
    Finally, I restore the original query order." */

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // Sort the logs based on the time (second element of each log entry)
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));
        // Number of queries
        int qlen = queries.length;
        // Initialize counters and other variables
        // cnt[] tracks the active status of servers, used tracks the count of active servers communicating at time <= current query time
        int cnt[] = new int[n + 1];
        int i = 0, j = 0, used = 0, res[] = new int[qlen], ll = logs.length;
        // Sort the queries in increasing order and track their original indices
        var ids = IntStream.range(0, qlen).boxed().sorted(Comparator.comparingInt(a -> queries[a])).toList();
        // Process each query in the order of query times
        for (int id : ids) {
            // Add logs that are within the current query time
            while (i < ll && logs[i][1] <= queries[id]) {
                used += (++cnt[logs[i][0]] == 1) ? 1 : 0;  // Mark the server as used, increase count if it's the first time the server is used
                ++i;
            }
            // Remove logs that are beyond the time window of the current query (queries[id] - x)
            while (j < ll && logs[j][1] < queries[id] - x) {
                used -= (--cnt[logs[j][0]] == 0) ? 1 : 0;  // Decrease the used count if a server is no longer active
                ++j;
            }
            // The result for this query is the total number of servers minus the number of servers used (communicating)
            res[id] = n - used;
        }
        // Return the results for all queries
        return res;
    }
}


// Time Complexity :- O(L log L + Q log Q + l + q).
// Space Complexity :- O(L log L + Q log Q).
