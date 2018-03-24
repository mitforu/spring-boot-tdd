package hello;

import org.springframework.stereotype.Service;

@Service
public class SayHelloService {
    public String sayHello(String name){
        return "Hello "+name;
    }
}
