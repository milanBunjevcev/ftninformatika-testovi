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
public class Banka {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String naziv;
	
	@Column
	private double sredstva;

	@OneToMany(mappedBy = "banka", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Racun> racuni = new ArrayList<Racun>();
	
	@OneToMany(mappedBy = "banka", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TipRacuna> tipoviRacuna = new ArrayList<TipRacuna>();

	public void addRacun(Racun racun) {
		if (!racun.getBanka().equals(this)) {
			racun.setBanka(this);
		}
		this.racuni.add(racun);
	}
	
	public void addTipRacun(TipRacuna tipRacuna) {
		if (!tipRacuna.getBanka().equals(this)) {
			tipRacuna.setBanka(this);
		}
		this.tipoviRacuna.add(tipRacuna);
	}

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuns) {
		this.racuni = racuns;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getSredstva() {
		return sredstva;
	}

	public void setSredstva(double sredstva) {
		this.sredstva = sredstva;
	}

	public List<TipRacuna> getTipoviRacuna() {
		return tipoviRacuna;
	}

	public void setTipoviRacuna(List<TipRacuna> tipoviRacuna) {
		this.tipoviRacuna = tipoviRacuna;
	}

}