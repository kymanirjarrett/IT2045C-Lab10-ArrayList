import java.util.ArrayList;
import java.util.List;

/**
 * A sorted list of strings that always maintains
 * ascending lexicographic order.
 */
public class SortedList {

    private final ArrayList<String> data = new ArrayList<>();

    /**
     * Adds a value into the list in sorted order.
     *
     * @param value the string to add
     * @return the index where the value was inserted
     */
    public int add(String value) {
        int index = findInsertionIndex(value);
        data.add(index, value);
        return index;
    }

    /**
     * Searches for a value using binary search.
     *
     * @param value the value to search for
     * @return the index if found, otherwise -1
     */
    public int indexOf(String value) {
        int low = 0;
        int high = data.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = data.get(mid).compareToIgnoreCase(value);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Returns the index where a value would be inserted
     * to preserve sorted order.
     *
     * @param value the value to analyze
     * @return insertion position
     */
    public int findInsertionIndex(String value) {
        int low = 0;
        int high = data.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = data.get(mid).compareToIgnoreCase(value);

            if (comparison < 0) {
                low = mid + 1;
            } else if (comparison > 0) {
                high = mid - 1;
            } else {
                return mid; // exact match
            }
        }

        return low;
    }

    /**
     * Gets the contents of the list.
     *
     * @return a read-only list view
     */
    public List<String> getItems() {
        return List.copyOf(data);
    }

    /**
     * Returns the number of items in the list.
     *
     * @return list size
     */
    public int size() {
        return data.size();
    }

    /**
     * Returns a formatted list string.
     *
     * @return string representation of contents
     */
    @Override
    public String toString() {
        if (data.isEmpty()) {
            return "[empty]";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            sb.append(i).append(": ").append(data.get(i)).append("\n");
        }
        return sb.toString();
    }
}