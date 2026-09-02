package message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import account.Account;

/**
 * Represents a post in a messaging system.
 * A post is a specialized message that is associated with a group.
 * 
 * @author Bruno Garbuio De Souza
 * @version abUTA sprint 2 Version 1
 * @since Version 1
 */
public class Post extends Message {
    private Group group;

    /**
     * Constructs a Post object.
     *
     * @param from      The account that created the post.
     * @param group     The group where the post is shared.
     * @param repliedTo The message this post is replying to (if any).
     * @param body      The content of the post.
     * @since 1.0
     */
    public Post(Account from, Group group, Message repliedTo, String body) {
        super(from, repliedTo, body);
        this.group = group;
    }

    public Post(BufferedReader br, Message repliedTo) throws IOException {
        super(br, repliedTo);
    }

    public void save (BufferedWriter bw) throws IOException{
        bw.write("" + group + "\n");
    }

    /**
     * Returns a string representation of the post, including the group it was posted in
     * and the details from the parent message.
     *
     * @return A formatted string representation of the post.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "Posted in " + group + super.toString();
    }
}
