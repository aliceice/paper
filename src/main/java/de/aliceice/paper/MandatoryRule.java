package de.aliceice.paper;

public final class MandatoryRule implements Rule {
    
    @Override
    public String getDescription() {
        return "Mandatory";
    }
    
    @Override
    public Boolean isValid(String value) {
        return !value.isEmpty();
    }
    
}
