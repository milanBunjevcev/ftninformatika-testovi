package jwd.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Racun {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false)
	private String imePrezime;

	@Column
	private String jmbg;

	@Column
	private String brojRacuna;

	@Column
	double stanjeRacuna;

	@ManyToOne(fetch = FetchType.EAGER)
	private TipRacuna tipRacuna;

	@ManyToOne(fetch = FetchType.EAGER)
	private Banka banka;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
		if (!banka.getRacuni().contains(this)) {
			banka.getRacuni().add(this);
		}
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public double getStanjeRacuna() {
		return stanjeRacuna;
	}

	public void setStanjeRacuna(double stanjeRacuna) {
		this.stanjeRacuna = stanjeRacuna;
	}

	public TipRacuna getTipRacuna() {
		return tipRacuna;
	}

	public void setTipRacuna(TipRacuna tipRacuna) {
		this.tipRacuna = tipRacuna;
		if (!tipRacuna.getRacuni().contains(this)) {
			tipRacuna.getRacuni().add(this);
		}
	}

}