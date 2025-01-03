package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerApplication{
	
	//@Autowired
	//private com.kafka.producerExamples.CounterProducers CounterProducers;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		CounterProducers.sendMessage();
//	}
}
