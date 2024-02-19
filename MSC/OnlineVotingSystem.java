
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class OnlineVotingSystem extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, voteButton, viewResultsButton;
    private JTextArea outputArea;
    private Map<String, String> users;
    private Map<String, Integer> votes;

    public OnlineVotingSystem() {
        setTitle("OnlineVotingSystem");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);
        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        panel.add(registerButton);

        voteButton = new JButton("Vote");
        voteButton.addActionListener(this);
        panel.add(voteButton);

        viewResultsButton = new JButton("View Results");
        viewResultsButton.addActionListener(this);
        panel.add(viewResultsButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);

        add(panel);
        setVisible(true);

        // Initialize users and votes
        users = new HashMap<>();
        votes = new HashMap<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (users.containsKey(username) && users.get(username).equals(password)) {
                outputArea.append("Login successful!\n");
            } else {
                outputArea.append("Invalid username or password.\n");
            }
        } else if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            users.put(username, password);
            outputArea.append("User registered successfully!\n");
        } else if (e.getSource() == voteButton) {
            String username = usernameField.getText();
            if (!users.containsKey(username)) {
                outputArea.append("User not found. Please register.\n");
                return;
            }
            // Simulate voting process
            String selectedCandidate = JOptionPane.showInputDialog(this, "Enter candidate name:");
            if (votes.containsKey(selectedCandidate)) {
                votes.put(selectedCandidate, votes.get(selectedCandidate) + 1);
            } else {
                votes.put(selectedCandidate, 1);
            }
            outputArea.append("Vote casted for " + selectedCandidate + "\n");
        } else if (e.getSource() == viewResultsButton) {
            outputArea.append("Election Results:\n");
            for (Map.Entry<String, Integer> entry : votes.entrySet()) {
                outputArea.append(entry.getKey() + ": " + entry.getValue() + " votes\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OnlineVotingSystem());
    }
}
