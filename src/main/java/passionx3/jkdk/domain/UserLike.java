package passionx3.jkdk.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"itemId","userId"}))
public class UserLike implements Serializable{
	@Id
	@SequenceGenerator(name="LIKEID_SEQ_GEN", sequenceName="LIKEID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIKEID_SEQ_GEN")
	private int likeId;
	private int itemId;
	private String userId;
	@Column(name="isClicked")
	private boolean clicked;
	
	public UserLike() {}

	public UserLike(int itemId, String userId, boolean clicked) {
		this.itemId = itemId;
		this.userId = userId;
		this.clicked = clicked;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
}
