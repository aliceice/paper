package de.aliceice.paper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Rules {
    
    public String getDescription() {
        return this.rules.stream()
                         .map(Rule::getDescription)
                         .collect(Collectors.joining(System.lineSeparator()));
    }
    
    public Boolean areNotValid(String value) {
        return this.rules.stream()
                         .anyMatch(rule -> rule.isNotValid(value));
    }
    
    public Rules(Rule[] rules) {
        this.rules = Arrays.asList(rules);
    }
    
    private final List<Rule> rules;
}
