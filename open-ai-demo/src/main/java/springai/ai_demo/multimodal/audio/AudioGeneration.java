package springai.ai_demo.multimodal.audio;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AudioGeneration {
    private final OpenAiAudioSpeechModel speechModel;

    public AudioGeneration(
            OpenAiAudioSpeechModel speechModel
    ){
        this.speechModel=speechModel;
    }

    @GetMapping("/generate-audio")
    public ResponseEntity<byte[]> generateAudio(
            @RequestParam(defaultValue = "It's a great time to be a Java & Spring Developer, Have a good one!") String text
    ){
        var options= OpenAiAudioSpeechOptions.builder()
                .model("tts-1-hd")
                .voice(OpenAiAudioApi.SpeechRequest.Voice.SAGE)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(1.0f) // 0.25 to 4.0
                .build();

        SpeechPrompt speechPrompt=new SpeechPrompt(text,options);
        SpeechResponse speechResponse=speechModel.call(speechPrompt);

        byte[] audio=speechResponse.getResult().getOutput();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,"audio/mpeg")
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=speech.mp3")
                .body(audio);
    }
}
