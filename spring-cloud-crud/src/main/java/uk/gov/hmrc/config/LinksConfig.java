package uk.gov.hmrc.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

import uk.gov.hmrc.entity.LinkItem;

@ConfigurationProperties(prefix = "app")
public record LinksConfig(String pageTitle, List<LinkItem> links) {}
