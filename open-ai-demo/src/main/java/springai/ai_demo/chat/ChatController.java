package springai.ai_demo.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;

//    public ChatController(
//            ChatClient.Builder builder
//    ){
//        this.chatClient=builder.build(); // creates chat client
//    }

    public ChatController(
            @Qualifier("openAIChartClient") ChatClient chatClient
    ){
        this.chatClient=chatClient;
    }

    @GetMapping()
    public String chat(){
        return chatClient.prompt()
                .user("Tell me a interesting fact about one piece")
                .call()
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> chatStream(){
        return chatClient.prompt()
                .user("Tell me a interesting fact about Solo Leveling")
                .stream()
                .content();
    }

    @GetMapping("/res")
    public ChatResponse jokeStream(){
        return chatClient.prompt()
                .user("Tell me 10 programming jokes")
                .call().chatResponse();
    }
}
