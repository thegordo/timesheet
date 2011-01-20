package timeSheet.util.ldap;

import timeSheet.util.TimeSheetException;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

/**
 * User: John Lawrence
 * Date: Oct 29, 2010
 * Time: 8:37:19 AM
 */
public class LDAPAuthenticate {
    private String serverName;
    private String port;
    private String domain;

    public LDAPAuthenticate(String serverName, String domain) {
        this.serverName = serverName;
        this.domain = domain;
        this.port = "389";
    }

    public void login(String userName, String password) throws TimeSheetException {
        // Setup the userName to use for logging in.
        if (userName.isEmpty()) {
            throw new TimeSheetException("Unable to authenticate with the credentials provided.  Please re-enter your credentials and try again.");
        }
        String user = userName + "@" + domain;
        // Setup the Domain Controller to connect to.
        String ldapURL = "ldap://" + serverName + ":" + port;

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION, "simple"); // Using plain text authentication.  We might have to change this for other people, don't know.
        env.put(Context.SECURITY_PRINCIPAL, user);
        env.put(Context.SECURITY_CREDENTIALS,password);
        env.put(Context.PROVIDER_URL,ldapURL);
        try {
            // This fails if login is incorrect.
            LdapContext ctx = new InitialLdapContext(env, null);
            ctx.close();
        } catch (AuthenticationException e) {
            throw new TimeSheetException("Unable to authenticate with the credentials provided.  Please re-enter your credentials and try again.", e);
        } catch (CommunicationException e) {
            throw new TimeSheetException("The server settings are incorrect. Please check your configuration and try again.", e);
        } catch (NamingException e) {
            throw new TimeSheetException(e);
        }
    }
}
