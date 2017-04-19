package de.aliceice.paper;

public final class BooleanRule implements Rule {
    
    @Override
    public String getDescription() {
        return "Yes/No";
    }
    
    @Override
    public Boolean isValid(String value) {
        return "Yes".equals(value) || "No".equals(value);
    }
}
