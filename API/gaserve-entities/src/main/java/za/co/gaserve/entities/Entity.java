package za.co.gaserve.entities;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

/***
 * 
 * @author sellotseka All subclasses must have the following methods. Just copy
 *         and paste to your class. RowMapper does not understand inheritance.
 * 
 * @DynamoDBHashKey(attributeName="id") public String getId() { return id; }
 * 
 * @Override public Boolean getActive() { return active; }
 * 
 * @Override public Date getCreatedDate() { return createdDate; }
 * 
 * @Override public Date getLastUpdate() { return lastUpdate; }
 * 
 */
public abstract class Entity {

	protected String id;
	protected Boolean active = Boolean.valueOf(true);
	protected Date lastUpdate;
	protected Date createdDate;

	public abstract String getId();

	public void setId(String id) {
		this.id = id;
	}

	public abstract Boolean getActive();

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void activate() {
		this.active = Boolean.valueOf(true);
	}

	public void deactivate() {
		this.active = Boolean.valueOf(false);
	}

	public abstract Date getCreatedDate();

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public abstract Date getLastUpdate();

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void prePersist() {

		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}

		if (this.active == null) {
			this.active = Boolean.TRUE;
		}

		if (this.createdDate == null) {
			this.createdDate = new Date(System.currentTimeMillis());
		}

		this.lastUpdate = new Date(System.currentTimeMillis());
	}

	protected <E extends Entity> void prePersist(Collection<E> es) {
		if (es != null) {
			for (Entity e : es) {
				e.prePersist();
			}
		}
	}

	public boolean equals(Object obj) {
		if ((obj != null) && ((obj instanceof Entity))) {
			return (this.id != null) && (this.id.equals(((Entity) obj).id));
		}
		return false;
	}

}