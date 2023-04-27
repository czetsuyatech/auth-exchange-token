package com.czetsuyatech.oauth.web.controllers;

import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
@RequiredArgsConstructor
@Validated
public class HelloController {

  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(OK)
  public ResponseEntity<String> sayHello(Authentication principal) {
    return ResponseEntity.ok("Hello World");
  }
}