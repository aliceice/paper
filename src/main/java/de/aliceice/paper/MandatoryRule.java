package de.aliceice.paper;

public final class MandatoryRule extends LocalizedRule {
    
    @Override
    public Boolean isValid(String value) {
        return !value.isEmpty();
    }
    
}
