package com.jmontanez.challenge.bcp.configuration.properties;

import com.jmontanez.challenge.bcp.common.utils.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "swagger")
@PropertySource(value = "classpath:exchange_rate_resources.yml", factory = YamlPropertySourceFactory.class)
public class SwaggerProperties {
    String title;
    String description;
    String version;
    String contactUrl;
    String contactEmail;
    String contactName;

}
