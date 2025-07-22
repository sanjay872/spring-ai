package springai.ai_demo.tools.datetime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateTimeController {

    private final ChatClient chatClient;

    public DateTimeController(
        @Qualifier("openAIChartClient") ChatClient chatClient
    ){
        this.chatClient=chatClient;
    }

    @GetMapping("/tools")
    public String tools(){
        return chatClient.prompt()
                .user("what is tomorrow's date?")
                .tools(new DateTimeTools())
                .call()
                .content();
    }
}
