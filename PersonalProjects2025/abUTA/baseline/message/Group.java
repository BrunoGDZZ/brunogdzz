package message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
     * Group creates a name associated with the group and gets the status of that group
     * @author Bruno Garbuio De Souza
     * @version Version 1 - Based off of Sprint #2
     * @since 1.0
     * @licence.agreement MIT
     */

public class Group {
    private String name;
    private boolean active;

    /**
     * Initializes the String Name and Boolean active values
     * 
     * 
     * @param name sets the name of the group.
     * @since 1.0
     * @throws IllegalArgumentException
     * 
     */

    public Group(String name){
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("PUT SOMETHING HERE!");
        }
        this.name = name;
        this.active = true;
    }


    public Group(BufferedReader br) throws IOException{
        this.name = br.readLine();
        this.active = Boolean.parseBoolean(br.readLine());
    }

    public void save(BufferedWriter bw) throws IOException{
        bw.write("" + name + "\n");
        bw.write("" + active + "\n");
    }
/**
 * 
 * @return Getter for returning the boolean value for IsActive()
 * @since 1.0
 */
    public boolean IsActive(){
        return active;
    }
/**
 * Sets active to false
 * @since 1.0
 */
    public void disable(){
        active = false;
    }
/**
 * Sets active to true
 * @since 1.0
 */
    public void enable(){
        active = true;
    }

    /**
     * @return returns the name or the name plus the string ' [inactive]'
     * @since 1.0
     */
    @Override
    public String toString(){
        if (active = true) {
            return name;
        }
        else {
            return name + " [inactive]";
        }
    }
}
    
    
