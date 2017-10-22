package de.uwepost;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.LastModifiedDate;


@MappedSuperclass
public abstract class BaseEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@Version
	public long version=0;

	public BaseEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Customer && ((Customer)obj).id==id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public long getVersion() {
		return version;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}
	
}