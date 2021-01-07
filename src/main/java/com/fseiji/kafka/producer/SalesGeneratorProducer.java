package com.fseiji.kafka.producer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.fseiji.kafka.model.Sale;
import com.fseiji.kafka.serializer.SaleSerializer;

public class SalesGeneratorProducer {

	private static Random rand = new Random();
	private static Long operation = 0L;
	private static BigDecimal ticketValue = BigDecimal.valueOf(500);

	public static void main(String args[]) throws InterruptedException {

		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SaleSerializer.class.getName());

		try (KafkaProducer<String, Sale> producer = new KafkaProducer<>(properties)) {
			while (true) {
				Sale sale = saleGenerator();
				ProducerRecord<String, Sale> record = new ProducerRecord<String, Sale>("topic-ticket", sale);
				producer.send(record);
				Thread.sleep(200);
			}
		}
	}

	private static Sale saleGenerator() {
		Long clientId = rand.nextLong();
		Integer quantityTicket = rand.nextInt(10);
		BigDecimal totalValue = ticketValue.multiply(BigDecimal.valueOf(quantityTicket));
		return new Sale(operation++, clientId, quantityTicket, totalValue);
	}

}
