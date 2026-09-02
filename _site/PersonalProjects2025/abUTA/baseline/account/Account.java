package account;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Represents a user account in the system.
 * Each account has a unique ID, a name, and an account status.
 * 
 * @author Bruno Garbuio De Souza
 * @version abUTA sprint 2 Version 1
 * @since Version 1
 */
public class Account {
    private String name;
    private int id;
    private static int nextID = 1; // Start the first user as ID number 1
    private AccountStatus status;

    /**
     * Constructs an Account object with the specified name.
     *
     * @param name The name of the account holder.
     * @throws IllegalArgumentException if the name is empty.
     * @since 1.0
     */
    public Account(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.id = nextID++;
        this.status = AccountStatus.Normal;
    }

    public Account(BufferedReader br) throws IOException{
        this.id = Integer.parseInt(br.readLine());
        this.name = br.readLine();
        this.status = AccountStatus.valueOf(br.readLine());
        this.nextID = Integer.parseInt(br.readLine());
    }

    public void save(BufferedWriter bw) throws IOException{
        bw.write("" + name + "\n");
        bw.write("" + id + "\n");
        bw.write("" + nextID + "\n");
        bw.write("" + status.name() + "\n");

    }

    /**
     * Sets the status of the account.
     *
     * @param status The new account status.
     * @since 1.0
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    /**
     * Checks if the account is muted.
     *
     * @return true if the account is muted, false otherwise.
     * @since 1.0
     */
    public boolean isMuted() {
        return status != AccountStatus.Normal;
    }

    /**
     * Checks if the account is blocked.
     *
     * @return true if the account is blocked, false otherwise.
     * @since 1.0
     */
    public boolean isBlocked() {
        return status == AccountStatus.Blocked;
    }

    /**
     * Returns a string representation of the account, including name, ID, and status if applicable.
     *
     * @return A formatted string representing the account.
     * @since 1.0
     */
    @Override
    public String toString() {
        String printedID = name + " (" + id + ")";
        if (isMuted()) {
            printedID += " [" + status + "]";
        }
        return printedID;
    }
}