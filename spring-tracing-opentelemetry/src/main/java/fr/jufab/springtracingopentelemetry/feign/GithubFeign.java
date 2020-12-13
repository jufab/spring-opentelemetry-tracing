package fr.jufab.springtracingopentelemetry.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jufab
 * @version 1.0
 */
@FeignClient(name = "github", url = "https://api.github.com", configuration = FeignTracingConfiguration.class)
public interface GithubFeign {
  @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/contributors", produces = "application/json")
  List<Contributor> contributors(@PathVariable("owner") String owner,
      @PathVariable("repo") String repo);
}
