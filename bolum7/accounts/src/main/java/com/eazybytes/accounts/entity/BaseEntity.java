package com.eazybytes.accounts.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.AuditingBeanDefinitionParser;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter@Setter@ToString
public class BaseEntity {

	@CreatedDate
	@Column(updatable=false)
	private LocalDateTime createdAt;
	
	@CreatedBy
	@Column(updatable=false)
	private String createdBy;
	
	@LastModifiedDate
	@Column(insertable=false)
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	@Column(insertable=false)
	private String updatedBy;
	
//	@PrePersist
//	    public void prePersist() {
//	        this.createdAt = LocalDateTime.now();  // Şu anki zamanı ayarlar
//	        this.createdBy = "anaonin";     // Kullanıcı ismini ayarlamak için
//	    }
//
//	    // Güncelleme yapılmadan önce updatedAt ve updatedBy alanlarını ayarlamak için
//	    @PreUpdate
//	    public void preUpdate() {
//	        this.updatedAt = LocalDateTime.now();  // Şu anki zamanı ayarlar
//	        this.updatedBy ="anonoim";     // Kullanıcı ismini ayarlamak için
//	    }

	
	
	
}
