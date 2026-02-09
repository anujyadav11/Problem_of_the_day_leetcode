/*********************************************** JAVA **************************************************/

Optimal Solution - Converts a BST to a balanced BST using inorder traversal and divide-and-conquer reconstruction.
                   Inorder traversal gives sorted values; rebuilding from the middle ensures minimal height and balance.

class Solution {
    public TreeNode balanceBST(TreeNode root) {
        // List to store inorder traversal (sorted values)
        List<Integer> res = new ArrayList<>();
        // Perform inorder traversal to get sorted values
        inOrder(root, res);
        // Build balanced BST from sorted list
        return construct(0, res.size() - 1, res);
    }
    // Inorder traversal to collect values in sorted order
    public void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }
    // Construct balanced BST from sorted list using divide and conquer
    public TreeNode construct(int l, int r, List<Integer> res) {
        // Base case: no elements to construct subtree
        if (l > r) return null;
        // Choose middle element to keep tree balanced
        int mid = l + (r - l) / 2;
        // Create root using value from sorted list
        TreeNode root = new TreeNode(res.get(mid));
        // Recursively build left and right subtrees
        root.left = construct(l, mid - 1, res);
        root.right = construct(mid + 1, r, res);
        return root;
    }
}

Time Complexity :- O(n).
Space Complexity :- O(n).
