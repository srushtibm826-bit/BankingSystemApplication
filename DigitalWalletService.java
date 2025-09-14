import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A service class to manage the digital wallet's back-end logic.
 * This class simulates a database with in-memory maps for demonstration purposes.
 * In a real-world application, this would be backed by a persistent database like PostgreSQL or MongoDB.
 */
public class DigitalWalletService {

    // Simulating a database for user accounts.
    // Key: userId, Value: User object
    private final Map<String, User> users = new HashMap<>();

    // For generating unique transaction IDs.
    private final AtomicLong transactionIdCounter = new AtomicLong(0);

    /**
     * Represents a user in the system.
     */
    private static class User {
        String username;
        double balance;
        // In a real application, this would contain a list of transactions.
        // For this example, we'll focus on the core logic.
    }

    /**
     * Represents a transaction.
     */
    private static class Transaction {
        String transactionId;
        String type; // e.g., "transfer", "deposit", "withdraw"
        double amount;
        String senderId;
        String recipientId;
    }

    /**
     * Creates a new user with a unique ID and a starting balance.
     * @param username The desired username for the new user.
     * @return The newly created User object.
     */
    public User createUser(String username) {
        // Generate a simple unique ID. In a real system, a UUID would be used.
        String userId = "user-" + (users.size() + 1);
        User newUser = new User();
        newUser.username = username;
        newUser.balance = 1000.00; // Starting balance
        users.put(userId, newUser);
        System.out.println("New user created: " + username + " with ID " + userId);
        return newUser;
    }

    /**
     * Gets a user's current balance.
     * @param userId The ID of the user.
     * @return The user's balance, or -1 if the user is not found.
     */
    public double getBalance(String userId) {
        User user = users.get(userId);
        return user != null ? user.balance : -1.0;
    }

    /**
     * Processes a fund transfer from one user to another.
     * @param senderId The ID of the sender.
     * @param recipientId The ID of the recipient.
     * @param amount The amount to transfer.
     * @return A Transaction object if successful, or null if the transaction fails.
     */
    public Transaction transferFunds(String senderId, String recipientId, double amount) {
        User sender = users.get(senderId);
        User recipient = users.get(recipientId);

        if (sender == null || recipient == null) {
            System.err.println("Error: Sender or recipient not found.");
            return null;
        }

        if (sender.balance < amount) {
            System.err.println("Error: Insufficient funds for transfer.");
            return null;
        }

        if (amount <= 0) {
            System.err.println("Error: Amount must be positive.");
            return null;
        }

        // Process the transaction
        sender.balance -= amount;
        recipient.balance += amount;

        // Create and return a transaction record
        Transaction tx = new Transaction();
        tx.transactionId = String.valueOf(transactionIdCounter.incrementAndGet());
        tx.type = "transfer";
        tx.amount = amount;
        tx.senderId = senderId;
        tx.recipientId = recipientId;

        System.out.println("Transfer successful: " + senderId + " sent $" + amount + " to " + recipientId);
        return tx;
    }

    /**
     * Processes a deposit to a user's account.
     * @param userId The ID of the user.
     * @param amount The amount to deposit.
     * @return A Transaction object if successful, or null if the deposit fails.
     */
    public Transaction deposit(String userId, double amount) {
        User user = users.get(userId);
        if (user == null) {
            System.err.println("Error: User not found.");
            return null;
        }

        if (amount <= 0) {
            System.err.println("Error: Amount must be positive.");
            return null;
        }

        user.balance += amount;

        Transaction tx = new Transaction();
        tx.transactionId = String.valueOf(transactionIdCounter.incrementAndGet());
        tx.type = "deposit";
        tx.amount = amount;
        tx.recipientId = userId;
        System.out.println("Deposit successful: $" + amount + " to " + userId);
        return tx;
    }

    /**
     * Processes a withdrawal from a user's account.
     * @param userId The ID of the user.
     * @param amount The amount to withdraw.
     * @return A Transaction object if successful, or null if the withdrawal fails.
     */
    public Transaction withdraw(String userId, double amount) {
        User user = users.get(userId);
        if (user == null) {
            System.err.println("Error: User not found.");
            return null;
        }

        if (user.balance < amount) {
            System.err.println("Error: Insufficient funds for withdrawal.");
            return null;
        }

        if (amount <= 0) {
            System.err.println("Error: Amount must be positive.");
            return null;
        }

        user.balance -= amount;

        Transaction tx = new Transaction();
        tx.transactionId = String.valueOf(transactionIdCounter.incrementAndGet());
        tx.type = "withdraw";
        tx.amount = amount;
        tx.senderId = userId;
        System.out.println("Withdrawal successful: $" + amount + " from " + userId);
        return tx;
    }

    /**
     * Main method for testing the service logic.
     */
    public static void main(String[] args) {
        DigitalWalletService walletService = new DigitalWalletService();

        // Create two users
        User userA = walletService.createUser("Alice");
        User userB = walletService.createUser("Bob");

        // Perform a transfer
        walletService.transferFunds("user-1", "user-2", 50.00);

        // Check balances
        System.out.println("Alice's balance: $" + walletService.getBalance("user-1"));
        System.out.println("Bob's balance: $" + walletService.getBalance("user-2"));

        // Test a deposit
        walletService.deposit("user-1", 100.00);
        System.out.println("Alice's new balance after deposit: $" + walletService.getBalance("user-1"));

        // Test an insufficient funds scenario
        walletService.transferFunds("user-2", "user-1", 1000.00); // This should fail
    }
}

