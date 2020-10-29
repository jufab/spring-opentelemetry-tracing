package fr.jufab.springtracing;

import fr.jufab.springtracing.github.feign.Contributor;
import fr.jufab.springtracing.github.feign.GithubFeign;
import java.util.List;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jufab
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/github")
public class GithubController {

  final GithubFeign githubFeign;

  public GithubController(GithubFeign githubFeign) {
    this.githubFeign = githubFeign;
  }

  @GetMapping(value = "/contributors", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Contributor> getContributors(@RequestParam String owner,
      @RequestParam String repository) {
    return this.githubFeign.contributors(owner, repository);
  }
}
