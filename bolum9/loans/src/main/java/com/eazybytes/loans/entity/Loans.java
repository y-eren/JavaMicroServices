package com.eazybytes.loans.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Loans extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;
	
	private String mobileNumber;
	
	private String loanNumber;
	
	private String loanType;
	
	private int totalLoan;
	
	private int amountPaid;
	
	private int outstandingAmount;

	@PrePersist
	public void prePersist() {
		this.setCreatedAt(LocalDateTime.now());  // Şu anki zamanı ayarlar
		this.setCreatedBy("anaonin");     // Kullanıcı ismini ayarlamak için
	}

	// Güncelleme yapılmadan önce updatedAt ve updatedBy alanlarını ayarlamak için
	@PreUpdate
	public void preUpdate() {
		this.setUpdatedAt(LocalDateTime.now());  // Şu anki zamanı ayarlar
		this.setUpdatedBy("anonoim");     // Kullanıcı ismini ayarlamak için
	}
}
