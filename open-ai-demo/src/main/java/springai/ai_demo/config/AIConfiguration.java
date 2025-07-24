package springai.ai_demo.config;

import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfiguration {

    @Bean
    public ChatClient openAIChartClient(OpenAiChatModel openAiChatModel){
        return ChatClient.create(openAiChatModel);
    }

    @Bean
    public ChatClient anthropicAIChartClient(AnthropicChatModel anthropicChatModel){
        return ChatClient.create(anthropicChatModel);
    }

    @Bean
    public ChatClient openAIChartClientMemory(OpenAiChatModel openAiChatModel, ChatMemory chatMemory){
      return  ChatClient.builder(openAiChatModel)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    @Bean
    public ChatClient openAIEval(OpenAiChatModel openAiChatModel){
        return ChatClient.builder(openAiChatModel).defaultOptions(OpenAiChatOptions.builder().temperature(0.1d).build())
                .build();
    }
}
