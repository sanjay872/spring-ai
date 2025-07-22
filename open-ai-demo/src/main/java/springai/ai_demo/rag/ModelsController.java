package springai.ai_demo.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelsController {

    private final ChatClient chatClient;

    public  ModelsController(
           @Qualifier("openAiRagModel") ChatClient chatClient
    ){
        this.chatClient=chatClient;
    }

    @GetMapping("/rag/models")
    public Models faq(@RequestParam(value = "message", defaultValue = "Give me a list of all the models from OpenAI along with their context window.") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(Models.class);
    }

}
