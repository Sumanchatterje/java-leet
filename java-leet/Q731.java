import java.util.ArrayList;
import java.util.List;
class Q731 {

    private List<int[]> calendar;
    private List<int[]> overlaps;
    public Q731() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }
    public boolean book(int start, int end) {
        // Check for triple bookings by looking for overlap with double-booked intervals
        for (int[] o : overlaps) {
            if (start < o[1] && end > o[0]) {
                return false;
            }
        }
        // Add new overlaps based on the current booking and previously booked intervals
        for (int[] c : calendar) {
            if (start < c[1] && end > c[0]) {
                overlaps.add(new int[] { Math.max(start, c[0]), Math.min(end, c[1]) });
            }
        }
        // Add the new event to the calendar
        calendar.add(new int[] { start, end });
        return true;
    }
}
/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */