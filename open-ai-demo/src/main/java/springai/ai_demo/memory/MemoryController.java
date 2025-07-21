package springai.ai_demo.memory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoryController {

    private final ChatClient chatClient;
    private final ChatClient chatClientMemory;

    public MemoryController(
            @Qualifier("openAIChartClient") ChatClient chatClient,
            @Qualifier("openAIChartClientMemory") ChatClient chatClientMemory
    ){
        this.chatClient=chatClient;
        this.chatClientMemory=chatClientMemory;
    }

    @GetMapping("/without-memory")
    public String withoutMemory(@RequestParam String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/with-memory")
    public String withMemory(@RequestParam String message){
        return chatClientMemory.prompt()
                .user(message)
                .call()
                .content();
    }
}
