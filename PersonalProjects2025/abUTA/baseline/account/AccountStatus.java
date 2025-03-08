package account;

/**
 * Represents the different statuses an account can have in the system.
 * These statuses determine the level of access a user has.
 * 
 * @author Bruno Garbuio De Souza
 * @version abUTA sprint 2 Version 1
 * @since Version 1
 */
public enum AccountStatus {
    /**
     * An account with no restrictions.
     */
    Normal,
    
    /**
     * A muted account can read other posts but cannot post at all.
     */
    Muted,
    
    /**
     * A blocked account cannot log in at all.
     */
    Blocked;
}