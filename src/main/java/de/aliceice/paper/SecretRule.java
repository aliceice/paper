package de.aliceice.paper;

public final class SecretRule implements Rule {
    
    @Override
    public String getDescription() {
        return "Secret";
    }
    
    @Override
    public Boolean isValid(String value) {
        return true;
    }
}
