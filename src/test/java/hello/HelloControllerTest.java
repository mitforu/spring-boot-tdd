package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }


    @Test
    public void hello_endpoint_should_return_hello_world() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/hello")
                .param("name","mitesh")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_call_say_hello_service() throws Exception{


        SayHelloService mock = Mockito.mock(SayHelloService.class);

        HelloController controller = new HelloController(mock);

        controller.sayHello("mitesh");

        Mockito.verify(mock).sayHello("MITESH");
    }

    @Test
    public void it_should_return_response_returned_by_service(){


        SayHelloService mock = Mockito.mock(SayHelloService.class);

        Mockito.when(mock.sayHello(Mockito.anyString())).thenReturn("Hello How are you");

        HelloController controller = new HelloController(mock);

        String result = controller.sayHello("hitesh");

        assertEquals("Hello How are you",result);

    }
}
