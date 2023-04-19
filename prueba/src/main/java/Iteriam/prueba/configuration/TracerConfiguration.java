package Iteriam.prueba.configuration;

import io.corp.calculator.TracerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracerConfiguration {
    @Bean
    public TracerImpl getTracer(){
        return new TracerImpl();
    }

}
