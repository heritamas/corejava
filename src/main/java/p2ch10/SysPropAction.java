package p2ch10;

import java.security.PrivilegedAction;

public class SysPropAction implements PrivilegedAction<String>
{
    private String propertyName;
    /**
    Constructs an action for looking up a given property.
    @param propertyName the property name (such as "user.home")
    */
    public SysPropAction(String propertyName)
    {
    this.propertyName = propertyName;
    }
    
    public String run()
    {
    return System.getProperty(propertyName);
    }
}
