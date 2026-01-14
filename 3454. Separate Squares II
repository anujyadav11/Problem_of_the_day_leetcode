/*********************************************** JAVA **************************************************/

I did not solve this problem. I have asked ChatGPT for this problem, it's so damn hard.

Optimal Solution - Use a sweep-line algorithm with a segment tree to compute the union area of squares and locate the horizontal line that splits the total area into two equal halves.

class Solution {
    /*
     * Each Node represents a segment on the x-axis.
     * `considered` : how many active intervals fully cover this segment
     * `activeLength`: total covered length in this segment
     */
    static class Node {
        long start, end;
        int considered;
        long activeLength;
        Node left, right;
        public Node(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
    /*
     * Segment Tree to maintain the total active x-length at a given sweep-line height
     */
    static class SegmentTree {
        Node head;
        public SegmentTree(int n) {
            head = new Node(0, n);
        }
        // Update interval [leftRange, rightRange)
        public void update(long left, long right, long leftRange, long rightRange, int delta) {
            update(left, right, leftRange, rightRange, head, delta);
        }
        public long getLength() {
            return head.activeLength;
        }
        private void update(long left, long right, long l, long r, Node node, int delta) {
            if (r <= left || right <= l) return;
            if (l <= left && right <= r) {
                node.considered += delta;
                updateNode(node, left, right);
                return;
            }
            long mid = getMid(left, right, node);
            update(left, mid, l, r, node.left, delta);
            update(mid, right, l, r, node.right, delta);
            updateNode(node, left, right);
        }
        private void updateNode(Node node, long start, long end) {
            if (node.considered > 0) {
                node.activeLength = end - start;
            } else {
                node.activeLength = (node.left == null ? 0 : node.left.activeLength)
                                  + (node.right == null ? 0 : node.right.activeLength);
            }
        }
        private long getMid(long left, long right, Node node) {
            long mid = left + (right - left) / 2;
            if (node.left == null) node.left = new Node(left, mid);
            if (node.right == null) node.right = new Node(mid, right);
            return mid;
        }
    }
    /*
     * Event for sweep line:
     * - start event adds interval
     * - end event removes interval
     */
    static class Event implements Comparable<Event> {
        long x1, x2, y;
        boolean isEnd;
        public Event(long x1, long x2, long y, boolean isEnd) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.isEnd = isEnd;
        }
        public int compareTo(Event other) {
            if (this.y != other.y) return Long.compare(this.y, other.y);
            return this.isEnd ? -1 : 1;
        }
    }
    private static final int MAX = 2_000_000_001;
    SegmentTree segmentTree = new SegmentTree(MAX);
    /*
     * Main function:
     * 1. Convert squares to sweep-line events
     * 2. Compute total union area
     * 3. Sweep again to find y where area is split in half
     */
    public double separateSquares(int[][] squares) {
        List<Event> events = new ArrayList<>();
        for (int[] sq : squares) {
            long x1 = sq[0];
            long x2 = (long) sq[0] + sq[2];
            long y1 = sq[1];
            long y2 = (long) sq[1] + sq[2];
            events.add(new Event(x1, x2, y1, false)); // start
            events.add(new Event(x1, x2, y2, true));  // end
        }
        Collections.sort(events);
        double totalArea = computeTotalArea(events);
        segmentTree = new SegmentTree(MAX); // reset tree
        return findSplitLine(events, totalArea);
    }
    // Computes total union area using sweep line
    private double computeTotalArea(List<Event> events) {
        double area = 0;
        long prevY = 0;
        for (Event e : events) {
            area += segmentTree.getLength() * (e.y - prevY);
            segmentTree.update(0, MAX, e.x1, e.x2, e.isEnd ? -1 : 1);
            prevY = e.y;
        }
        return area;
    }
    // Finds y-coordinate where area is split into two equal halves
    private double findSplitLine(List<Event> events, double totalArea) {
        double currArea = 0;
        long prevY = 0;
        for (Event e : events) {
            double delta = segmentTree.getLength() * (e.y - prevY);
            if (currArea + delta >= totalArea / 2) {
                return prevY + (totalArea / 2 - currArea) / segmentTree.getLength();
            }
            currArea += delta;
            segmentTree.update(0, MAX, e.x1, e.x2, e.isEnd ? -1 : 1);
            prevY = e.y;
        }
        return -1;
    }
}

Time Complexity :- O(N log C).
Space Complexity :- O(N log C).
