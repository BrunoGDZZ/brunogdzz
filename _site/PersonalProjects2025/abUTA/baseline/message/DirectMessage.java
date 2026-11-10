package message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import account.Account;

/**
 * Represents a direct message between two accounts.
 * A direct message is a private communication from one user to another.
 * 
 * @author Bruno Garbuio De Souza
 * @version abUTA sprint 2 Version 1
 * @since Version 1
 */
public class DirectMessage extends Message {
    private Account to;

    /**
     * Constructs a DirectMessage object.
     *
     * @param from      The account sending the message.
     * @param to        The account receiving the message.
     * @param repliedTo The message this direct message is replying to (if any).
     * @param body      The content of the message.
     */
    public DirectMessage(Account from, Account to, Message repliedTo, String body) {
        super(from, repliedTo, body);
        this.to = to;
    }

    public DirectMessage(BufferedReader br, Message repliedTo) throws IOException {
        super(br, repliedTo);
    }

    public void save (BufferedWriter bw) throws IOException{
        bw.write("" + to + "\n");
    }

    /**
     * Returns a string representation of the direct message, including the recipient
     * and the details from the parent message.
     *
     * @return A formatted string representation of the direct message.
     */
    @Override
    public String toString() {
        return "Message to " + to + super.toString();
    }
}