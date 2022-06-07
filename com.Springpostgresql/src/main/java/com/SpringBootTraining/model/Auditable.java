package com.SpringBootTraining.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class Auditable {

	@CreatedBy
	@Column(name = "created_by",updatable = false)
	protected String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	protected Date createdTs;
	
	@LastModifiedBy
	protected String modifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	protected Date modifiedTs;
}
