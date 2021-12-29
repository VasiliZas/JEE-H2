package web.vasilizas.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{new EncodingFilter(), new LoginFilter(), new WorkPageFilter(),
//                new AuthenticationAdminFilter(), new AuthenticationTeacherFilter()};
//    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MySpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
