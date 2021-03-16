package com.epam.dsc2019.cloud.discovery.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class BasicGreetingController {

  @Value("${app.service.name}")
  private String serviceName;

  @Value("${app.service.icon}")
  private int iconCode;

  /**
   * Returns a greeting message with an icon.
   *
   * @return greeting message
   */
  @GetMapping
  public String getGreeting() {
    final String greeting = new StringBuilder(new String(Character.toChars(iconCode)))
        .append(" ")
        .append("Hello from ")
        .append(serviceName)
        .toString();
    return greeting;
  }

  /**
   * Returns a greeting message with a given value.
   *
   * @param value message to show
   * @return greeting message
   */
  @GetMapping("/{value}")
  public String getGreeting(@PathVariable("value") String value) {
    final String greeting = new StringBuilder(new String(Character.toChars(iconCode)))
        .append(" ")
        .append("Hello from ")
        .append(serviceName)
        .append(", ")
        .append(value)
        .toString();
    return greeting;
  }
}
