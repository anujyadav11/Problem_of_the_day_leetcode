/********************************************************** JAVA ****************************************************/

we are sorting the meetings and using a min heap for storing rooms based on their end time for available and used room.

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // Sort meetings by their start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        // Priority queue to store rooms based on their end time
        PriorityQueue<long[]> queue = new PriorityQueue<>(
                (a, b) -> a[0] == b[0] ? (int) (a[1] - b[1]) : (int) (a[0] - b[0]));
        // Array to store the count of meetings for each room
        int[] roomCount = new int[n];
        // Variable to store the room with the most meetings
        int result = 0;
        // Initialize the priority queue with rooms and their end times
        for (int i = 0; i < n; i++)
            queue.add(new long[] { 0, i });
        // Process each meeting
        for (int[] item : meetings) {
            int time = item[0]; // New meeting start time
            // Order all available rooms by room index
            while (queue.peek()[0] < time)
                queue.add(new long[] { time, queue.poll()[1] });
            // Get the room with the earliest end time
            long[] current = queue.poll();
            int curRoom = (int) current[1];
            long meetingEndTime = current[0] + (item[1] - item[0]); // Current room end time + this meeting time
            roomCount[curRoom]++;
            // Update the result room based on the count of meetings
            if (roomCount[curRoom] > roomCount[result])
                result = curRoom;
            else if (roomCount[curRoom] == roomCount[result])
                result = Math.min(result, curRoom);
            // Add the updated room with the new end time to the priority queue
            queue.add(new long[] { meetingEndTime, curRoom });
        }
        return result;
    }
}
    Time Complexity :- O(m log m) + O(m log n)
    Space Complexity :- O(N)
    this was a hard question soo i used the soultion for a youtuber name neetcodeio. 
