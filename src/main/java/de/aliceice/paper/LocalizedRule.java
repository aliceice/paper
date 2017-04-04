package de.aliceice.paper;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public abstract class LocalizedRule implements Rule {
    
    @Override
    public String getDescription() {
        return String.format(this.resourceBundle.getString(getClass().getSimpleName()),
                             getDescriptionArguments());
    }
    
    protected Object[] getDescriptionArguments() {
        return new Object[] {};
    }
    
    protected LocalizedRule() {
        this(PropertyResourceBundle.getBundle("Rules"));
    }
    
    protected LocalizedRule(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
    
    private final ResourceBundle resourceBundle;
}
