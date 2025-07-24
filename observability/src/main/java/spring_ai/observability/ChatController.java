package spring_ai.observability;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/short")
    public String shortChat() {
        return chatClient.prompt()
                .user("When was the universe created")
                .call()
                .content();
    }

    @GetMapping("/long")
    public String longChat() {
        return chatClient.prompt()
                .user("write a horror story in 1000 words")
                .call()
                .content();
    }

    @GetMapping
    public String chat(@RequestParam String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}