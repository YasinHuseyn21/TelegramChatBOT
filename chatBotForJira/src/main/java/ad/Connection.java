package ad;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class Connection {
//
//    DirContext ctx;
//
//    public void newConnection() {
//        String ldapUrl = "ldap://localhost:10389"; // Active Directory server address and  connection point
//        String username = "uid=admin,ou=system"; // Username
//        String password = "secret"; // Password
//
//        Hashtable<String, String> env = new Hashtable<>();
//        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//        env.put(Context.PROVIDER_URL, ldapUrl);
//        env.put(Context.SECURITY_AUTHENTICATION, "simple");
//        env.put(Context.SECURITY_PRINCIPAL, username);
//        env.put(Context.SECURITY_CREDENTIALS, password);
//
//        try {
//            // connection
//            ctx = new InitialDirContext(env);
//            System.out.println("Connection successful" + ctx);
//
//
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//
//    }
}

