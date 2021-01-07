package com.fseiji.kafka.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fseiji.kafka.model.Sale;

public class SaleDeserializer implements Deserializer<Sale> {

	@Override
	public Sale deserialize(String topic, byte[] sale) {
		try {
			return new ObjectMapper().readValue(sale, Sale.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
