package com.kafka.producerExamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.entity.CarLocation;

@Service
public class FilterCarLocationProducer {
	
	private CarLocation car1;
	private CarLocation car2;
	private CarLocation car3;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	public void sendCarLocation(CarLocation carLocation) throws JsonProcessingException {
		System.out.println("Sending Car Location: " + carLocation);
		String jsonCarLocation = objectMapper.writeValueAsString(carLocation);
		kafkaTemplate.send("t-filtering", carLocation.getIdCar(), jsonCarLocation);
	}
	
	public void generateCarLocations(){
		Long now = System.currentTimeMillis();
		this.car1 = new CarLocation("car-one", now, 0);
		this.car2 = new CarLocation("car-two", now, 95);
		this.car3 = new CarLocation("car-three", now, 100);
	}
	
	@Scheduled(fixedRate=10000)
	public void scheduledSendCarLocation() {
		
		if (this.car1 == null) this.generateCarLocations();
		
		Long now = System.currentTimeMillis();
		this.car1.setTimestamp(now);
		this.car2.setTimestamp(now);
		this.car3.setTimestamp(now);
		
		this.car1.setDistance(this.car1.getDistance()+1);
		this.car2.setDistance(this.car2.getDistance()+1);
		this.car3.setDistance(this.car3.getDistance()-1);

		try {
			this.sendCarLocation(car1);
			this.sendCarLocation(car2);
			this.sendCarLocation(car3);
		} catch (JsonProcessingException e) {
			System.out.println("Car Locations couldn't be sent.");
			e.printStackTrace();
		}
	}
}
