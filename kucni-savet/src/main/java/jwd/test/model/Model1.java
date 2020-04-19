package jwd.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Model1 {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Model2 model2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Model2 getModel2() {
		return model2;
	}

	public void setModel2(Model2 model2) {
		this.model2 = model2;
		if (!model2.getModel1s().contains(this)) {
			model2.getModel1s().add(this);
		}
	}

}