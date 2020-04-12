package sample;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class ChatController {
	@MessageMapping("/chat/{topic}")
	@SendTo("/topic/messages")
	public OutputMessage send(@DestinationVariable("topic") String topic, Message message) throws Exception {
		System.out.println("Message has been received from " + message.getFrom() + " on topic " + topic);
		System.out.println("Message is : " + message.getText());
		System.out.println("Sending out same message back with some additional field(s)");
		return new OutputMessage(message.getFrom(), message.getText(), topic);
	}
}
