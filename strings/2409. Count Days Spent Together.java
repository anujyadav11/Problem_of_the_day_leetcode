/*********************************************** JAVA **************************************************/

// Optimal Solution - Solved Count Days Spent Together using date-to-day conversion and inclusive interval intersection in O(1) time and O(1) auxiliary space.
/* “I convert each date into its day number within the year. I then calculate the overlap by taking the later arrival date and the earlier departure date. 
    If the intervals do not overlap, I return zero; otherwise, I return the difference between the endpoints plus one because the interval is inclusive.” */

class Solution {
    // Number of days in each month of a non-leap year.
    private static final int[] DAYS = {
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    };
    public int countDaysTogether(
            String arriveAlice,
            String leaveAlice,
            String arriveBob,
            String leaveBob) {
        // Convert all dates into day numbers within the year.
        int aliceStart = toDayInYear(arriveAlice);
        int aliceEnd = toDayInYear(leaveAlice);
        int bobStart = toDayInYear(arriveBob);
        int bobEnd = toDayInYear(leaveBob);
        // If one person leaves before the other arrives, there is no overlap.
        if (aliceEnd < bobStart || bobEnd < aliceStart) {
            return 0;
        }
        // The overlap begins at the later arrival date.
        int startDay = Math.max(aliceStart, bobStart);
        // The overlap ends at the earlier departure date.
        int endDay = Math.min(aliceEnd, bobEnd);
        // Add 1 because both arrival and departure days are inclusive.
        return endDay - startDay + 1;
    }
    // Extract the month from an MM-DD date.
    private int parseMonth(String date) {
        return Integer.parseInt(date.substring(0, 2));
    }
    // Extract the day from an MM-DD date.
    private int parseDay(String date) {
        return Integer.parseInt(date.substring(3, 5));
    }
    // Convert MM-DD into a day number within the year.
    private int toDayInYear(String date) {
        int month = parseMonth(date);
        int day = parseDay(date);
        int totalDays = 0;
        // Add all days from the preceding months.
        for (int i = 0; i < month - 1; i++) {
            totalDays += DAYS[i];
        }
        // Add the day within the current month.
        return totalDays + day;
    }
}

// Time Complexity :- O(1).
// Space Complexity :- O(1).
