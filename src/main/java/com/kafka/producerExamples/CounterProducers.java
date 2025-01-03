package com.kafka.producerExamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
public class CounterProducers {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage() {
		for (int i = 0; i < 50; i++) {
			kafkaTemplate.send("counter-topic", "MessageSent: " + i);
		}
	}
	
}
