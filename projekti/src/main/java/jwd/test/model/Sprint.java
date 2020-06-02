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
public class Sprint {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String ime;

	@Column
	private String ukupnoBodova;

	@OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Zadatak> zadaci = new ArrayList<Zadatak>();

	public void racunajBodove() {
		int bodoviSprint = 0;
		for (Zadatak z : zadaci) {
			bodoviSprint += z.getBodovi();
		}
		this.setUkupnoBodova("" + bodoviSprint);
	}

	public void addZadatak(Zadatak zadatak) {
		if (!zadatak.getSprint().equals(this)) {
			zadatak.setSprint(this);
		}
		this.zadaci.add(zadatak);
	}

	public List<Zadatak> getZadaci() {
		return zadaci;
	}

	public void setZadaci(List<Zadatak> zadaci) {
		this.zadaci = zadaci;
	}

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

	public String getUkupnoBodova() {
		return ukupnoBodova;
	}

	public void setUkupnoBodova(String ukupnoBodova) {
		this.ukupnoBodova = ukupnoBodova;
	}

}