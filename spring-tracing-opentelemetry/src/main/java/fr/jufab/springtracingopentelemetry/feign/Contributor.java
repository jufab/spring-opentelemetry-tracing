package fr.jufab.springtracingopentelemetry.feign;

/**
 * @author jufab
 * @version 1.0
 */
public class Contributor {
  private String login;
  private String url;

  public Contributor() {
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
