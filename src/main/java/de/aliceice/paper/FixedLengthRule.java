package de.aliceice.paper;

public final class FixedLengthRule implements Rule {
    
    @Override
    public String getDescription() {
        return String.format("Fixed length %d", this.length);
    }
    
    @Override
    public Boolean isValid(String value) {
        return this.length == value.length();
    }
    
    public FixedLengthRule(Integer length) {
        this.length = length;
    }
    
    private final Integer length;
}
