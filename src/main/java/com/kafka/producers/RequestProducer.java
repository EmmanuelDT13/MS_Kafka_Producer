package com.kafka.producers;

import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kafka.entity.Request;

@Service
public class RequestProducer {

	@Autowired
	private KafkaTemplate<String, Request> kafkaTemplate;
	
	@Scheduled(fixedRate=10000)
	private void sendRequest() {
		Request request = new Request(UUID.randomUUID(), 31L, 10L);
		ProducerRecord<String, Request> producerRecord = new ProducerRecord<String, Request>("request-topic", 1, request.getRequestId().toString(), request);
		kafkaTemplate.send(producerRecord);
	}
	
	@Scheduled(fixedRate=10000)
	private void sendRequest2() {
		Request request = new Request(UUID.randomUUID(), 21L, 0L);
		ProducerRecord<String, Request> producerRecord = new ProducerRecord<String, Request>("request-topic", 1, request.getRequestId().toString(), request);
		kafkaTemplate.send(producerRecord);
	}
	
	@Scheduled(fixedRate=10000)
	private void sendRequest3() {
		Request request = new Request(UUID.randomUUID(), 12L, 1L);
		ProducerRecord<String, Request> producerRecord = new ProducerRecord<String, Request>("request-topic", 1, request.getRequestId().toString(), request);
		kafkaTemplate.send(producerRecord);
	}
	
}
