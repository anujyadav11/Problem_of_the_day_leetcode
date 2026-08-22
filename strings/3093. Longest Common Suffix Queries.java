/*********************************************** JAVA - I DID NOT SOLVE THIS QUESTION I JUST POSTED THE SOLUTION **************************************************/

// Optimal Solution - Finds shortest container word with longest common suffix for each query using a reverse-insertion suffix trie storing shortest-word indices at each node.
/* "Inserting words in reverse converts suffix matching into prefix matching — the standard trie operation. Storing the shortest word index at each node ensures that when a query's suffix path ends, 
    we have the optimal answer immediately. The root stores the globally shortest word as fallback for queries with no matching suffix." */

class Solution {
    static class TrieNode {
        int idx;
        TrieNode[] children;
        TrieNode() {
            // idx = -1 means no word assigned to this node yet
            idx = -1;
            children = new TrieNode[26];
        }
    }
    public TrieNode getNode(int idx) {
        TrieNode temp = new TrieNode();
        temp.idx = idx;
        return temp;
    }
    public void insertTrie(TrieNode pCrawl, int i, String[] wordsContainer) {
        String word = wordsContainer[i];
        int n = word.length();
        // insert word in reverse — suffix trie for suffix matching
        for (int j = n - 1; j >= 0; j--) {
            int ch_idx = word.charAt(j) - 'a';
            if (pCrawl.children[ch_idx] == null)
                pCrawl.children[ch_idx] = getNode(i);
            pCrawl = pCrawl.children[ch_idx];
            // prefer shorter word — update node's word index if current is shorter
            if (wordsContainer[pCrawl.idx].length() > n)
                pCrawl.idx = i;
        }
    }
    public int search(TrieNode pCrawl, String word, String[] wordsContainer) {
        // start with root's best candidate
        int resultIdx = pCrawl.idx;
        int n = word.length();
        // traverse query word in reverse to match suffix
        for (int i = n - 1; i >= 0; i--) {
            int ch_idx = word.charAt(i) - 'a';
            pCrawl = pCrawl.children[ch_idx];
            // no further suffix match — return best found so far
            if (pCrawl == null) return resultIdx;
            resultIdx = pCrawl.idx;
        }
        return resultIdx;
    }
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int m = wordsContainer.length;
        int n = wordsQuery.length;
        int[] result = new int[n];
        // initialize root with index 0 as default best
        TrieNode root = getNode(0);
        for (int i = 0; i < m; i++) {
            // update root's best index if shorter word found
            if (wordsContainer[root.idx].length() > wordsContainer[i].length())
                root.idx = i;
            insertTrie(root, i, wordsContainer);
        }
        for (int i = 0; i < n; i++)
            result[i] = search(root, wordsQuery[i], wordsContainer);
        return result;
    }
}

// Time Complexity :- O(Σ|wordsContainer| + Σ|wordsQuery|).
// Space Complexity :- O(Σ|wordsContainer| × 26).
