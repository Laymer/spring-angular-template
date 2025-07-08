package be.julienbastin.template.controllers;

import be.julienbastin.template.generated.controllers.HelloController;
import be.julienbastin.template.generated.dtos.HelloWorldDto;
import be.julienbastin.template.services.api.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerImpl implements HelloController {

    private final HelloService helloService;

    public HelloControllerImpl(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public ResponseEntity<HelloWorldDto> helloWorld(String key) throws Exception {
        return ResponseEntity.ok(new HelloWorldDto(helloService.hello(key)));
    }
}
