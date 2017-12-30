package de.klingbeil.swaglist.wishlist.api.model;

public class UrlContent {

  private String title;
  private String description;
  private String url;
  private String raw;
  private String finalUrl;
  private boolean successful;
  private String term;
  
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getRaw() {
    return raw;
  }
  public void setRaw(String raw) {
    this.raw = raw;
  }
  public String getFinalUrl() {
    return finalUrl;
  }
  public void setFinalUrl(String finalUrl) {
    this.finalUrl = finalUrl;
  }
  public boolean isSuccessful() {
    return successful;
  }
  public void setSuccessful(boolean successful) {
    this.successful = successful;
  }
  public void setTerm(String term) {
    this.term = term;
  }
  public String getTerm() {
    return term;
  }
}
