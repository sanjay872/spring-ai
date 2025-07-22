package springai.ai_demo.tools.action;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskManagementController {

    private final ChatClient chatClient;
    private final TaskManagementTools taskManagementTools;

    public TaskManagementController(
            @Qualifier("openAIChartClient") ChatClient chatClient,
            TaskManagementTools taskManagementTools
    ){
        this.chatClient=chatClient;
        this.taskManagementTools=taskManagementTools;
    }

    @GetMapping("/tasks")
    public String createTask(@RequestParam String message) {
        return chatClient.prompt()
                .tools(taskManagementTools)
                .user(message)
                .call()
                .content();
    }
}
