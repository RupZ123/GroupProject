package com.tcs.pricerestapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "priceService_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long priceId;
	private long productId;
	private float priceValue;
}
