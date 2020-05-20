package jwd.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Model2 {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@OneToMany(mappedBy = "model2", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Model1> model1s = new ArrayList<Model1>();

	public void addModel1(Model1 model1) {
		if (!model1.getModel2().equals(this)) {
			model1.setModel2(this);
		}
		this.model1s.add(model1);
	}

	public List<Model1> getModel1s() {
		return model1s;
	}

	public void setModel1s(List<Model1> model1s) {
		this.model1s = model1s;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}