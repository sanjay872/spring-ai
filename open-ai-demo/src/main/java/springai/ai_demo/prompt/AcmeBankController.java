package springai.ai_demo.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
public class AcmeBankController {

    private final ChatClient chatClient;

    public AcmeBankController(
           @Qualifier("openAIChartClient") ChatClient chatClient
    ){
        this.chatClient=chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){
        var systemInstruction= """
                Only discuss about Anime related question.
                Give answers within 1-2 sentences.
                """;
        return chatClient.prompt()
                .user(message)
                .system(systemInstruction)
                .call()
                .content();
    }
}
