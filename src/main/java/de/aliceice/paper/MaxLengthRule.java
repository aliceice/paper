package de.aliceice.paper;

public final class MaxLengthRule extends LocalizedRule {
    
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
    
    @Override
    protected Object[] getDescriptionArguments() {
        return new Object[] {this.length};
    }
    
    private final Integer length;
}
