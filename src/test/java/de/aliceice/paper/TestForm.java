package de.aliceice.paper;

public final class TestForm extends Form {
    
    public TestForm() {
        super("Test Form",
              "Test Description",
              new Fields(new Field("Test Field", new MandatoryRule())));
    }
}
