package message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import account.AccountStatus;
import account.Account;

import java.util.Date; // The class Date represents a specific instant in time, with millisecond precision.
import java.util.ArrayList; // Resizable-array implementation of the LIST interface.

/**
 * Represents a message in a messaging system.
 * Each message contains information about the sender, timestamp, message body,
 * and potential replies to other messages.
 * 
 * @author Bruno Garbuio De Souza
 * @version abUTA sprint 2 Version 1
 * @since Version 1
 */
public class Message {
    private Account from;
    private Date date;
    private Message repliedTo;
    private ArrayList<Message> replies;
    private String body;

    /**
     * Constructs a Message object.
     *
     * @param from      The account that sent the message.
     * @param repliedTo The message this one is replying to (if any).
     * @param body      The content of the message.
     * @since 1.0
     */
    public Message(Account from, Message repliedTo, String body) {
        this.from = from;
        this.repliedTo = repliedTo;
        this.body = body;
        
        if (repliedTo != null) {
            repliedTo.addReply(this);
        }
        replies = new ArrayList<>();
    }

    public Message (BufferedReader br, Message repliedTo) throws IOException{
        this.from = new Account(br);
        this.date = new Date(Long.parseLong(br.readLine()));
        this.body = br.readLine();

        int arraysize = Integer.parseInt(br.readLine());
        for (int i = 0; i < arraysize; i++) {
            String name = br.readLine();
            Message msg;
            if (name.equals("message.Post")){
                msg = new Post(from, null, repliedTo, body);
            }else {
                msg = new DirectMessage(from, null, repliedTo, body);
            }replies.add(msg);
            }

            if (this.repliedTo != null) {
                repliedTo.addReply(this);
            }
        }


    public void save (BufferedWriter bw) throws IOException{
        bw.write("" + from + "\n");
        bw.write("" + date + "\n");
        bw.write("" + body + "\n");

        for (Message reply: replies){
            bw.write("" + reply.getClass().getName() + "\n");
            reply.save(bw);
        }
    }

    public String getTitle() {
        return "From " + from; // + " on " + date;
    }

    public int getNumReplies() {
        return replies.size();
    }
    
    /**
     * Adds a reply to this message.
     *
     * @param message The message being added as a reply.
     * @since 1.0
     */
    private void addReply(Message message) {
        replies.add(message);
    }

    /**
     * Retrieves a reply to this message based on its index.
     *
     * @param index The index of the reply.
     * @return The reply at the given index, or null if out of bounds.
     * @since 1.0
     */
    public Message getReply(int index) {
        if (index >= replies.size() || index < 0) {
            return null;
        }
        return replies.get(index);
    }

    /**
     * Retrieves the message this one is replying to.
     *
     * @return The original message being replied to, or null if none.
     * @since 1.0
     */
    public Message getRepliedTo() {
        return repliedTo;
    }

    /**
     * Returns a string representation of the message, including sender, timestamp,
     * replies, and message body.
     *
     * @return A formatted string representation of the message.@
     * @since 1.0
     */
    @Override
    public String toString() {
        int index = replies.indexOf(this);
        String result = "Date: " + "<" + date + ">" + "\n" + "From: " + index + "<" + from + ">" + "\n";

        if (repliedTo != null) {
            result += "In reply to: " + repliedTo.from + "\n";
        }

        if (replies != null) {
            for (Message reply : replies) {
                result += reply.from;
            }
            result += "In reply to: " + repliedTo.from + "\n";
        }
        
        result += body;
        return result;
    }
}