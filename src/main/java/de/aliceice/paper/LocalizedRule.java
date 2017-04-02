package de.aliceice.paper;

import java.util.PropertyResourceBundle;

public abstract class LocalizedRule implements Rule {
    
    @Override
    public String getDescription() {
        return String.format(PropertyResourceBundle.getBundle("Rules")
                                                   .getString(getClass().getSimpleName()),
                             getDescriptionArguments());
    }
    
    protected Object[] getDescriptionArguments() {
        return new Object[] {};
    }
}
