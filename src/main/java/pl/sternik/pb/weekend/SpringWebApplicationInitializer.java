package pl.sternik.pb.weekend;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import pl.sternik.pb.weekend.web.SpringWebConfig;



public class SpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public SpringWebApplicationInitializer() {
        super();
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringWebConfig.class };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringBusinessConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
