package de.aliceice.paper;

import java.util.regex.Pattern;

public final class RegexRule extends LocalizedRule {
    
    @Override
    public Boolean isValid(String value) {
        return this.pattern.matcher(value).matches();
    }
    
    public RegexRule(String regex, String example) {
        this.pattern = Pattern.compile(regex, Pattern.DOTALL);
        this.example = example;
    }
    
    @Override
    protected Object[] getDescriptionArguments() {
        return new Object[] {this.example, this.pattern};
    }
    
    private final Pattern pattern;
    private final String  example;
}
