package mypoc.euh.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MappedSuperclass
public class BaseEntity {

	@Transient
	private final static Logger logger = LoggerFactory.getLogger(BaseEntity.class);

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false)
	private Date updated;

	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}
	
	public String toString() {
		StringBuffer object = new StringBuffer();
		List<Field> fields = new ArrayList<Field>();
		fields = getAllFields(fields, this.getClass());
		for (Field field : fields) {
		    field.setAccessible(true);
		    String name = field.getName();
		    Object value;
			try {
				value = field.get(this);
				object.append(name).append("=").append(value).append("&");
			} catch(Exception e) {
				logger.error("Error while parsing object "+name, e);
			}
		}
		return object.toString();
	}
	
	
	public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
	    fields.addAll(Arrays.asList(type.getDeclaredFields()));

	    if (type.getSuperclass() != null) {
	        getAllFields(fields, type.getSuperclass());
	    }

	    return fields;
	}
	
}
