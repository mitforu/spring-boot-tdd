package hello;

import org.junit.Test;
import org.springframework.stereotype.Service;

import static org.junit.Assert.*;

@Service
public class SayHelloServiceTest {

    @Test
    public void it_should_add_say_hello(){
        SayHelloService subject = new SayHelloService();

        String result = subject.sayHello("Mitesh");

        assertEquals("Hello Mitesh",result);
    }

}