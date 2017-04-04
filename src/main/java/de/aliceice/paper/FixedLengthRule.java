package de.aliceice.paper;

public final class FixedLengthRule extends LocalizedRule {
    
    @Override
    public Boolean isValid(String value) {
        return this.length == value.length();
    }
    
    public FixedLengthRule(Integer length) {
        this.length = length;
    }
    
    @Override
    protected Object[] getDescriptionArguments() {
        return new Object[] {this.length};
    }
    
    private final Integer length;
}
