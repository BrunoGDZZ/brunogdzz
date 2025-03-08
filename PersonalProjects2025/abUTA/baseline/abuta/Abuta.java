package abuta;
import account.Account;
import message.Group;
import message.Message;
import message.Post;
import menu.Menu;
import menu.MenuItem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


public class Abuta {
    private List<Account> accounts;
    private List<Group> groups;
    private Message message;
    private Menu menu; 
    private String output;
    private boolean running = true;

    private void endApp(){
    running = false;
    }

    public Abuta(){
        accounts = new ArrayList<>();
        groups = new ArrayList<>();

        accounts.add(new Account("Reorge Gice"));
        accounts.add(new Account("Gdvitya Aarg"));
        accounts.add(new Account("Druno BeSouza"));
        accounts.add(new Account("Batman"));
        accounts.add(new Account("Mr. Garbuio De Souza"));

        groups.add(new Group("Justice League"));
        groups.add(new Group("Xbox Lovers"));
        groups.add(new Group("Playstation Lovers"));
        groups.add(new Group("Smart Fellas Association"));
        groups.add(new Group("Creeper Squad"));

        message = new Post(accounts.get(0), groups.get(0), null, "WHATS UPPPPPPPPPPPPPPP?");

        menu = new Menu();
        menu.addMenuItem(new MenuItem("Exit\n",        () -> endApp()));
        menu.addMenuItem(new MenuItem("Show reply\n",        () -> showReply()));
        menu.addMenuItem(new MenuItem("Show replied to\n",        () -> showRepliedTo()));
        menu.addMenuItem(new MenuItem("Add Reply\n",        () -> reply()));
        menu.addMenuItem(new MenuItem("Add Account[BONUS] \n",        () -> addAccount()));
        menu.addMenuItem(new MenuItem("Add Group[BONUS] \n",        () -> addGroup()));
        menu.addMenuItem(new MenuItem("New abUTA \n",        () -> newAbuta()));
        menu.addMenuItem(new MenuItem("Save \n",        () -> save()));
        menu.addMenuItem(new MenuItem("Save As \n",        () -> saveAs()));
        menu.addMenuItem(new MenuItem("Open \n",        () -> addGroup()));

        
    
    }
    
    public void showReply(){
    int numReplies = message.getNumReplies();  
    
    switch (numReplies) {
        case 0:  output = "This message has no replies."; break;
        case 1: message = message.getReply(0); break;
        case 2: 
        for (int i = 0; i < numReplies; i++){
            System.out.println(i + ": " + message.getReply(i).toString());
        }
        
        int choice = Menu.getInt("Which reply would you like? ");
        //Check if the choice is valid and call getReply().
        if (choice >= 0 && choice < numReplies) {
            message = message.getReply(choice);
        } else {
            output = "Invalid choice.";
        }   
            break;    }}

        /* [INSTRUCTIONS] 
        This method switches the active message (referenced by the message field) to the current message's
        repliedTo field.
        However, the first message created is not in reply to any message, so in that case, use the output field to
        report that the current message was not in reply to any other message and do NOT change the message field
        */ 
        private void showRepliedTo() {
            if (message.getRepliedTo() == null) {
                output = "This message is not in reply to any other message.";
            } else {
                message = message.getRepliedTo();
            }
        }    

    private void reply(){
        char temp = Character.toUpperCase((menu.getChar("P for Post, D for Direct Messsage ")));        
        int FromWhoIndex = Menu.selectItemFromList("From who? ", accounts);
        int group = Menu.selectItemFromList("Which group? ", groups);
        Account FromWho = accounts.get(FromWhoIndex);

        //TODO: Check if TypeOfMessage.equals (P) or (D)

        //if P statement
        if (temp == 'P'){
            System.out.println("Post from " + FromWho + "(" + FromWhoIndex + ")" + "in group " + group);
            String bodyofmessage = Menu.getString("");
            System.out.println("Post from " + FromWho + "(" + FromWhoIndex + ")" + "in group " + group);

        }

        //if D statement
        if (temp == 'D'){
            System.out.println("Post from" + FromWho + "(" + FromWhoIndex + ")" + "to user: " + FromWho);
            String bodyofmessage = Menu.getString("");
            System.out.println("Post from" + FromWho + "(" + FromWhoIndex + ")" + "to user: " + FromWho);

        }

    }
    
    private void addAccount() {
    String newAccountName = Menu.getString("Enter a new account name: ");
    accounts.add(new Account(newAccountName));
    }

    private void addGroup() {
        String newGroupName = Menu.getString("Enter a new group name: ");
        accounts.add(new Account(newGroupName));
    }

    public void mdi(){

        while (running){
            //TODO: Print the menu
            System.out.println(" ");
            System.out.println("------ Welcome to the abUTA Chat Menu! ------");
            System.out.println("-------------- Version 1.0 --------------");
            System.out.println(menu);
            //TODO: Print the output
            System.out.println(output);

            //Clear the screen
            //System.out.println("\n".repeat(20));

            //TODO: Get the users selection:
            int choice = Menu.getInt("Select an option: ");
            

            //TODO: Use that selection with the given menu.run
            menu.run(choice);

        }

    }

private void newAbuta(){
    new Abuta();
}

private void save(){
    String NameofFile = "WorkingSave.abuta";
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(NameofFile))){
        Message root = this.message;
        while (root.getRepliedTo() != null){
            root = root.getRepliedTo();
        } root.save(bw);
        System.out.println("Message saved to " + NameofFile);
    } catch(IOException e){
        e.printStackTrace();
    }
}
private String NameofFile = "WorkingSave.abuta";

private void saveAs(){
    String newFileName= Menu.getString("Enter the desired file name please: ");
    if ((newFileName) != null){
        NameofFile = newFileName;
        save();
    }


}

private void open(){
    String openFileName= Menu.getString("Enter the desired file name please: ");
    if ((openFileName) == null){
        NameofFile = openFileName;
        save();
    }

    
    try (BufferedReader br = new BufferedReader(new FileReader(openFileName))){
        Message newPost = new Post(br, null);
    } catch(IOException e){
        output = "ERROR Exception: " + e;
        e.printStackTrace();
    }

}

    public static void main (String[] args)
    {
        Abuta abuuta = new Abuta();
        abuuta.mdi();
    } 
}

