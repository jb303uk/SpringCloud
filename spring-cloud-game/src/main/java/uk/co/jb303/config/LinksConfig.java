package uk.co.jb303.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

import uk.co.jb303.entity.LinkItem;

@ConfigurationProperties(prefix = "app")
public record LinksConfig(String pageTitle, List<LinkItem> links) {}
