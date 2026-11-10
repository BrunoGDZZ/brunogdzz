package message;
import message.Group;

public class TestGroup {
    public static void main(String[] args) {
    
        Group Group1 = new Group("Bruno's Squad");
        if (Group1.toString().isEmpty()) {
            System.err.println("\nERROR: Constructor and ToString Error.");
        } else{  
            System.err.println("The Constructor and ToString Works!");
        }

        if (Group1.IsActive())
        {
            System.err.println("The group is active by default!");
        } else {
            System.err.println("The group isn't active by default!");
        }
        
        //Check if disable works
        Group1.disable();
        if (Group1.IsActive())
        {
            System.err.println("After disabling it's Active");
        } else {
            System.err.println("After disabling it's Inactive");
        }
        
        Group1.disable();
        StringBuilder s1 = new StringBuilder(Group1.toString());
        if (s1.substring((s1.length() - 10), s1.length()).equals(" [inactive]"))
        {
            System.err.println("This group reports inactive.");
        }else{

            System.err.println("This group reports active");
        }

        Group1.enable();
        if (Group1.IsActive())
        {
            System.err.println("After enabling it's Active");
        } else {
            System.err.println("After enabling it's Inactive");
        }
        
    
    }
}
