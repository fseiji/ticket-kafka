package com.fseiji.kafka.model;

import java.math.BigDecimal;

public class Sale {

	private Long operation;
	private Long clientId;
	private Integer quantityTicket;
	private BigDecimal totalValue;
	private String status;

	public Sale() {
	}

	public Sale(Long operation, Long clientId, Integer quantityTicket, BigDecimal totalValue) {
		super();
		this.operation = operation;
		this.clientId = clientId;
		this.quantityTicket = quantityTicket;
		this.totalValue = totalValue;
	}

	public Long getOperation() {
		return operation;
	}

	public void setOperation(Long operation) {
		this.operation = operation;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Integer getQuantityTicket() {
		return quantityTicket;
	}

	public void setQuantityTicket(Integer quantityTicket) {
		this.quantityTicket = quantityTicket;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sale [operation=");
		builder.append(operation);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append(", quantityTicket=");
		builder.append(quantityTicket);
		builder.append(", totalValue=");
		builder.append(totalValue);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
}
