package springai.ai_demo.multimodal.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageDetection {

    private final ChatClient chatClient;

    @Value("classpath:/images/demo.jpg")
    Resource sampleImage;

    public ImageDetection(
          @Qualifier("openAIChartClient") ChatClient chatClient
    ){
        this.chatClient=chatClient;
    }

    @GetMapping("/image-to-text")
    public String imageToText(){
        return chatClient.prompt()
                .user(
                        u->{
                            u.text("Can describe the give image");
                            u.media(MimeTypeUtils.IMAGE_JPEG,sampleImage);
                        }
                )
                .call()
                .content();
    }

}
