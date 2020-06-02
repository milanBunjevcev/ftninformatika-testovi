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
public class Stanje {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String ime;

	@OneToMany(mappedBy = "stanje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Zadatak> zadaci = new ArrayList<Zadatak>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void addZadatak(Zadatak zadatak) {
		if (!zadatak.getStanje().equals(this)) {
			zadatak.setStanje(this);
		}
		this.zadaci.add(zadatak);
	}

	public List<Zadatak> getZadaci() {
		return zadaci;
	}

	public void setZadaci(List<Zadatak> zadaci) {
		this.zadaci = zadaci;
	}

}