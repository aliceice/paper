package de.aliceice.paper;

public final class FixedValueRule extends LocalizedRule {
    
    @Override
    public Boolean isValid(String value) {
        return value.equals(this.value);
    }
    
    public FixedValueRule(String value) {
        this.value = value;
    }
    
    @Override
    protected Object[] getDescriptionArguments() {
        return new Object[] {this.value};
    }
    
    private final String value;
}
