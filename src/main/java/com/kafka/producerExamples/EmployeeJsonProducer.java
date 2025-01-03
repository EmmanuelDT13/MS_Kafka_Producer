package com.kafka.producerExamples;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.entity.Employee;

//@Service
public class EmployeeJsonProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Scheduled(fixedRate=10000)
	public void sendMessage() {
		
		Employee employee = new Employee(String.valueOf(Math.random()), "Emmanuel", LocalDate.of(2024, Month.JANUARY, 18).atStartOfDay());
		
		try {
			String jsonEmployee = objectMapper.writeValueAsString(employee);
			kafkaTemplate.send("t-employee", jsonEmployee);
			System.out.println("The employee has been sent to kafka t-topic.");
		} catch (JsonProcessingException e) {
			System.err.println("The employee couldn't be sent to kafka topic.");
			e.printStackTrace();
		}
		
	}
	
}
