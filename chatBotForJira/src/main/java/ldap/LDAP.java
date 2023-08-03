package ldap;

import ad.Connection;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

public class LDAP {
//    static LDAP ldp = new LDAP();

    DirContext ctx;
//    Connection connection= new Connection();



    public void newConnection() {
        String ldapUrl = "ldap://localhost:10389"; // Active Directory server address and  connection point
        String username = "uid=admin,ou=system"; // Username
        String password = "secret"; // Password

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapUrl);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, username);
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            // connection
            ctx = new InitialDirContext(env);
            System.out.println("Connection successful" + ctx);


        } catch (NamingException e) {
            e.printStackTrace();
        }

    }


    public static boolean authUser(String username, String password) {
        try {
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
            env.put(Context.SECURITY_PRINCIPAL, "cn=" + username + ",ou=users,ou=system");
            env.put(Context.SECURITY_CREDENTIALS, password);

            DirContext con = new InitialDirContext(env);
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public List<String> getAllUsers() throws NamingException {
        newConnection();
        List<String> str = new ArrayList<>();
        try {
            String searchFilter = "(objectClass=inetOrgPerson)";
            String[] reqAtt = {"cn"};
            SearchControls control = new SearchControls();
            control.setSearchScope(SearchControls.SUBTREE_SCOPE);
            control.setReturningAttributes(reqAtt);
            NamingEnumeration users = ctx.search("ou=users,ou=system", searchFilter, control);
            SearchResult result = null;
            while (users.hasMore()) {
                result = (SearchResult) users.next();
                Attributes attribute = result.getAttributes();
                String user = attribute.get("cn").toString();
                str.add(user.replaceAll("cn: ", "").trim().concat(" \uD83D\uDDA5"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;

    }

    public List<String> searchUser(String name) throws NamingException {
       newConnection();
        List<String> str = new ArrayList<>();
        try {
            String searchFilter = "(cn=" + name + ")";
            String[] reqAtt = {"cn", "sn", "homePhone", "mail"};
            SearchControls control = new SearchControls();
            control.setSearchScope(SearchControls.SUBTREE_SCOPE);
            control.setReturningAttributes(reqAtt);
            NamingEnumeration users = ctx.search("ou=users,ou=system", searchFilter, control);
            SearchResult result = null;
            while (users.hasMore()) {
                result = (SearchResult) users.next();
                Attributes attribute = result.getAttributes();
                for (int i = 0; i < reqAtt.length; i++) {

                    String user = attribute.get(reqAtt[i]).toString();
                    String newUser = user.replaceAll("cn: ", " ").trim();
                    newUser.replaceAll("sn: ", " ").trim();
                    System.out.println(newUser.replaceAll("sn: ", " ").trim());
                    str.add(newUser.replaceAll("sn: ", " ").trim());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;

    }

    public List<String> searchUserPs(String name, String password) throws NamingException {
        newConnection();
        List<String> str = new ArrayList<>();
        try {
            String searchFilter = "(cn=" + name + "homePhone=" + password + ")";
            String[] reqAtt = {"cn", "homePhone", "mail"};
            SearchControls control2 = new SearchControls();
            control2.setSearchScope(SearchControls.SUBTREE_SCOPE);
            control2.setReturningAttributes(reqAtt);
            NamingEnumeration users = ctx.search("ou=users,ou=system", searchFilter, control2);
            SearchResult result = null;
            while (users.hasMore()) {
                result = (SearchResult) users.next();
                Attributes attribute = result.getAttributes();
                for (int i = 0; i < reqAtt.length; i++) {
                    String user = attribute.get(reqAtt[i]).toString();
                    str.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;

    }

    public void addUser(String name) {
        newConnection();
        Attributes attributes = new BasicAttributes();
        Attribute attribute = new BasicAttribute("objectClass");
        attribute.add("inetOrgPerson");

        attributes.put(attribute);
        //
        attributes.put("sn", name);
        try {
            ctx.createSubcontext("cn=" + name + ",ou=users,ou=system", attributes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws NamingException {
        LDAP ldp = new LDAP();
       List<String> list= ldp.searchUser("HP");
        System.out.println(list);



    }


}


