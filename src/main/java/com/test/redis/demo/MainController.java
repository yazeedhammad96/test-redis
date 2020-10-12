package com.test.redis.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class MainController {
  @RequestMapping(method = RequestMethod.GET)
  public String index(HttpServletRequest request){

    return "Hi " + request.getRemoteUser()+
        "<br>Session: "+ request.getSession()
        +"<br>Session Id: " +request.getRequestedSessionId();
  }
}

