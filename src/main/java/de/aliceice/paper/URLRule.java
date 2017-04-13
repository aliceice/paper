package de.aliceice.paper;

import java.net.MalformedURLException;
import java.net.URL;

public final class URLRule implements Rule {
    
    @Override
    public String getDescription() {
        return "URL";
    }
    
    @Override
    public Boolean isValid(String value) {
        try {
            new URL(value);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
