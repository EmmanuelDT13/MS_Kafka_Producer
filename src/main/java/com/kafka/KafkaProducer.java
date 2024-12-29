package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	private int counter=0;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Scheduled(fixedRate=30000)
	public void sendMessageToKafka() {
		
		String nombreDelTopic = "multiple-partitions";
		String mensaje = "Msg #"+ counter+": " + "Esta es una prueba de envío de mensaje";
		
		for (int i = 0; i < 100; i++) {
			String key = "key-" + i;
			
			this.kafkaTemplate.send(nombreDelTopic, key, mensaje);
			counter ++;
		}
	}
}
