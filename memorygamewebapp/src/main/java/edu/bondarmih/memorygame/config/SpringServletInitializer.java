package edu.bondarmih.memorygame.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * Created by bondarm on 09.08.16.
 */

public class SpringServletInitializer extends AbstractDispatcherServletInitializer {

    public SpringServletInitializer() {
        super();
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        return context;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
