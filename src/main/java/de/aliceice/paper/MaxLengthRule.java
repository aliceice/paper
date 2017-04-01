package de.aliceice.paper;

public final class MaxLengthRule implements Rule {
    
    @Override
    public String getDescription() {
        return String.format("Max length %d", this.length);
    }
    
    @Override
    public Boolean isValid(String value) {
        return this.length >= value.length();
    }
    
    @Override
    public Boolean isNotValid(String value) {
        return this.length < value.length();
    }
    
    public MaxLengthRule(Integer length) {
        this.length = length;
    }
    
    private final Integer length;
}
