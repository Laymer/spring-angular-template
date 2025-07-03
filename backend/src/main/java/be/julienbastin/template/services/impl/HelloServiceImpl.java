package be.julienbastin.template.services.impl;

import be.julienbastin.template.services.api.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "Hello";
    }
}
