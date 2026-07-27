package uk.gov.hmrc.entity;

/**
 * A immutable data carrier representing a web hyperlink.
 * 
 * @param text The clickable display text for the anchor tag
 * @param url  The target destination address (href)
 */
public record LinkItem(String name, String url) {}
