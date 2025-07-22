package springai.ai_demo.tools.datetime;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;

public class DateTimeTools {

    @Tool(description = "Get the current date and time in the user's timezone")
    public String getCurrentTimeAndDate(){
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }
}
