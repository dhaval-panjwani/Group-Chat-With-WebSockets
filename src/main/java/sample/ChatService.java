package sample;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	private SimpMessagingTemplate simpMessagingTemplate;
	private Map<String, String> usersToTopic = new HashMap<>();
	private static final String WS_MESSAGE_TRANSFER_DESTINATION = "/topic/messages";

	ChatService(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	// this will send messages to only those users subscribed to this topic
	public void subscibeOrsendMessagesByTopic(String topic, Message message, String sessionId) {
		addUserName(sessionId, topic);
		if (message.getText().isEmpty())
			return;
		OutputMessage output = new OutputMessage(message.getFrom(), message.getText(), topic);
		for (Entry<String, String> entry : usersToTopic.entrySet()) {
			if (entry.getValue().equals(topic) || entry.getValue().equals("")) {
				System.out.println("Key " + entry.getKey() + " value " + entry.getValue());
				simpMessagingTemplate.convertAndSendToUser(entry.getKey(), WS_MESSAGE_TRANSFER_DESTINATION, output);
			}
		}
	}

	public void addUserName(String username, String topic) {
		System.out.println("User " + username + " added to topic " + topic);
		this.usersToTopic.put(username, topic);
		System.out.println("Number of users are " + usersToTopic.size());
	}

}
