package springai.ai_demo.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    private final ChatClient chatClient;

    public VacationController(
            @Qualifier("openAIChartClient") ChatClient chatClient
    ){
        this.chatClient=chatClient;
    }

    @GetMapping("/unstructured")
    public String vacationUnstructured() {
        return chatClient.prompt()
                .user("What's a good vacation plan while I'm in Montreal CA for 4 days?")
                .call()
                .content();
    }

    @GetMapping("/structured")
    public Itinerary structured(){
        return chatClient.prompt()
                .user("I want to plan a trip to Hawaii. give me a list of things to do.")
                .call()
                .entity(Itinerary.class);
    }


}
