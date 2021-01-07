package ctu.via.bonvoyage.configuration;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
public class AppConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean(name = "com.github.dozermapper.core.Mapper")
    public Mapper dozerBean(){

        List<String> mappingFiles = Arrays.asList("mapping/PlaceResponseMapping.xml",
                                                "mapping/RouteResponseMapping.xml",
                                                "mapping/TripResponseMapping.xml",
                                                "mapping/PlaceEntityMapping.xml");

        return DozerBeanMapperBuilder.create()
                .withMappingFiles(mappingFiles)
                .build();
    }

}
