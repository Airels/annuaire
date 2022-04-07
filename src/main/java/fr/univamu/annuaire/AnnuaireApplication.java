package fr.univamu.annuaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class AnnuaireApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AnnuaireApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AnnuaireApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("--- addResourceHandlers");
        registry.addResourceHandler("/webjars/**")//
                .addResourceLocations("/webjars/");
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        var r = new ReloadableResourceBundleMessageSource();
        r.setBasenames(
                "classpath:jsp_view",
                "classpath:person",
                "classpath:password_reset"
        );
        return r;
    }
}
