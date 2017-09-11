package de.aliceice.paper;

import java.util.Locale;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

public final class EnglishLocaleExtension implements BeforeAllCallback, AfterAllCallback {
    
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        getStore(context).put("previousLocale", Locale.getDefault());
        Locale.setDefault(Locale.ENGLISH);
    }
    
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Locale.setDefault(getStore(context).get("previousLocale", Locale.class));
    }
    
    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(Namespace.create("de.aliceice.paper", getClass().getName()));
    }
    
}