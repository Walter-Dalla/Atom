package br.com.cotil.aton.configuration.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {

  @RequestMapping("/docs")
  public String swagger() {
    return "redirect:/swagger-ui.html";
  }

}

