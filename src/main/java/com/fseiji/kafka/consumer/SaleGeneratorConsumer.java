package com.fseiji.kafka.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.fseiji.kafka.deserializer.SaleDeserializer;
import com.fseiji.kafka.model.Sale;

public class SaleGeneratorConsumer {

	public static void main(String args[]) throws InterruptedException {

		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SaleDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-ticket");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");

		try (KafkaConsumer<String, Sale> consumer = new KafkaConsumer<>(properties)) {
			consumer.subscribe(Arrays.asList("topic-ticket"));

			while (true) {
				ConsumerRecords<String, Sale> records = consumer.poll(Duration.ofMillis(200));
				for (ConsumerRecord<String, Sale> record : records) {
					Sale sale = record.value();

					if (new Random().nextBoolean()) {
						sale.setStatus("APPROVED");
					} else {
						sale.setStatus("DISAPPROVED");
					}
					Thread.sleep(500);
					System.out.println(sale);
				}
			}
		}
	}

}
