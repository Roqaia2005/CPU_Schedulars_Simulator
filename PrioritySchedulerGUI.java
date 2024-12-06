import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PrioritySchedulerGUI {
    private JFrame frame;
    private JTable processTable;
    private JPanel chartPanel;
    private JTextArea statsArea;
    private ArrayList<Process> processes;

    public PrioritySchedulerGUI() {
        processes = new ArrayList<>();
        setupGUI();
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);


        SwingUtilities.invokeLater(PrioritySchedulerGUI::new);
    }

    private void setupGUI() {
        frame = new JFrame("Priority Scheduler - GUI Representation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Process Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel nameLabel = new JLabel("Process Name:");
        JTextField nameField = new JTextField();
        JLabel burstTimeLabel = new JLabel("Burst Time:");
        JTextField burstTimeField = new JTextField();
        JLabel priorityLabel = new JLabel("Priority:");
        JTextField priorityField = new JTextField();
        JButton addButton = new JButton("Add Process");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(burstTimeLabel);
        inputPanel.add(burstTimeField);
        inputPanel.add(priorityLabel);
        inputPanel.add(priorityField);
        inputPanel.add(addButton);

        // Process Table
        processTable = new JTable(new DefaultTableModel(
                new Object[]{"ID", "Name", "Burst Time", "Priority"}, 0
        ));
        JScrollPane tableScrollPane = new JScrollPane(processTable);

        // Chart Panel
        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawChart(g);
            }
        };
        chartPanel.setPreferredSize(new Dimension(600, 300));
        chartPanel.setBackground(Color.WHITE);

        // Statistics Area
        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane statsScrollPane = new JScrollPane(statsArea);

        // Execute Button
        JButton executeButton = new JButton("Execute Scheduling");

        // Add Listeners
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String burstTimeText = burstTimeField.getText();
            String priorityText = priorityField.getText();

            if (name.isEmpty() || burstTimeText.isEmpty() || priorityText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int burstTime = Integer.parseInt(burstTimeText);
                int priority = Integer.parseInt(priorityText);
                processes.add(new Process(name, burstTime, priority));
                updateProcessTable();
                nameField.setText("");
                burstTimeField.setText("");
                priorityField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Burst Time and Priority must be integers!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        executeButton.addActionListener(e -> {
            if (processes.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No processes to schedule!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Sort and execute scheduling
            executePriorityScheduling();
            chartPanel.repaint();
        });

        // Layout
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.WEST);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.add(statsScrollPane, BorderLayout.SOUTH);
        frame.add(executeButton, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void updateProcessTable() {
        DefaultTableModel model = (DefaultTableModel) processTable.getModel();
        model.setRowCount(0);
        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            model.addRow(new Object[]{i + 1, process.getName(), process.getBurstTime(), process.getPriority()});
        }
    }

    private void executePriorityScheduling() {
        // Sort processes by priority
        Collections.sort(processes, Comparator.comparingInt(Process::getPriority));

        // Calculate statistics
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Process process : processes) {
            process.setWaitingTime(currentTime);
            totalWaitingTime += process.getWaitingTime();
            currentTime += process.getBurstTime();
            process.setTurnAroundTime(currentTime);
            totalTurnaroundTime += process.getTurnAroundTime();
        }

        int n = processes.size();
        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;

        // Update statistics area
        statsArea.setText(String.format("Average Waiting Time (AWT): %.2f\n", avgWaitingTime));
        statsArea.append(String.format("Average Turnaround Time (ATAT): %.2f\n", avgTurnaroundTime));
    }

    private void drawChart(Graphics g) {
        if (processes.isEmpty()) return;

        int x = 20; // Starting x-coordinate
        int y = 50; // y-coordinate for the bar
        int barHeight = 30;
        int scale = 20; // Scale factor for burst time

        for (Process process : processes) {
            int barWidth = process.getBurstTime() * scale;

            // Set a random color for each process
            g.setColor(new Color((int) (Math.random() * 0xFFFFFF)));
            g.fillRect(x, y, barWidth, barHeight);

            // Draw process name inside the bar
            g.setColor(Color.BLACK);
            g.drawString(process.getName(), x + barWidth / 2 - 10, y + barHeight / 2);

            x += barWidth + 10; // Move to the next bar
        }
    }
}
