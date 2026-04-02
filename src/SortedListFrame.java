import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SortedListFrame extends JFrame {

    private final SortedList sortedList = new SortedList();

    private final JTextField addField = new JTextField(20);
    private final JTextField searchField = new JTextField(20);

    private final JButton addButton = new JButton("Add");
    private final JButton searchButton = new JButton("Search");
    private final JButton clearButton = new JButton("Clear");
    private final JButton quitButton = new JButton("Quit");

    private final JTextArea outputArea = new JTextArea(20, 45);

    public SortedListFrame() {
        setTitle("Sorted List (Binary Search)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        add(topPanel(), BorderLayout.NORTH);
        add(centerPanel(), BorderLayout.CENTER);
        add(bottomPanel(), BorderLayout.SOUTH);

        addButton.addActionListener(e -> addString());
        searchButton.addActionListener(e -> searchString());
        clearButton.addActionListener(e -> clearDisplay());
        quitButton.addActionListener(e -> System.exit(0));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel topPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addPanel.setBorder(new TitledBorder("Add String"));
        addPanel.add(new JLabel("String:"));
        addPanel.add(addField);
        addPanel.add(addButton);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(new TitledBorder("Search String"));
        searchPanel.add(new JLabel("String:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(addPanel);
        panel.add(searchPanel);

        return panel;
    }

    private JPanel centerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(new TitledBorder("Operations / List Contents"));

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel bottomPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(clearButton);
        panel.add(quitButton);
        return panel;
    }

    private void addString() {
        String value = addField.getText().trim();

        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a string to add.",
                    "Missing Input",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int insertedAt = sortedList.add(value);

        outputArea.append("Added: \"" + value + "\" at index " + insertedAt + "\n");
        outputArea.append("Current Sorted List:\n");
        outputArea.append(sortedList.toString());
        outputArea.append("----------------------------------------\n");

        addField.setText("");
        addField.requestFocus();
    }

    private void searchString() {
        String value = searchField.getText().trim();

        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a string to search for.",
                    "Missing Input",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int foundIndex = sortedList.indexOf(value);

        if (foundIndex >= 0) {
            outputArea.append("Search: \"" + value + "\" FOUND at index " + foundIndex + "\n");
        } else {
            int insertionIndex = sortedList.findInsertionIndex(value);
            outputArea.append("Search: \"" + value + "\" NOT FOUND.\n");
            outputArea.append("It would be inserted at index " + insertionIndex + "\n");
        }

        outputArea.append("----------------------------------------\n");

        searchField.setText("");
        searchField.requestFocus();
    }

    private void clearDisplay() {
        addField.setText("");
        searchField.setText("");
        outputArea.setText("");
        addField.requestFocus();
    }
}