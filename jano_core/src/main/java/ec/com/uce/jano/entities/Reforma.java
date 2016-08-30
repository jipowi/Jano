package ec.com.uce.jano.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the reforma database table.
 * 
 */
@Entity
public class Reforma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reforma")
	private Integer idReforma;

	@Column(name="valor_reforma")
	private BigDecimal valorReforma;

	//bi-directional many-to-one association to Afectacion
	@ManyToOne
	@JoinColumn(name="id_afectacion")
	private Afectacion afectacion;

	//bi-directional many-to-one association to Partida
	@ManyToOne
	@JoinColumn(name="id_partida")
	private Partida partida;

	public Reforma() {
	}

	public Integer getIdReforma() {
		return this.idReforma;
	}

	public void setIdReforma(Integer idReforma) {
		this.idReforma = idReforma;
	}

	public BigDecimal getValorReforma() {
		return this.valorReforma;
	}

	public void setValorReforma(BigDecimal valorReforma) {
		this.valorReforma = valorReforma;
	}

	public Afectacion getAfectacion() {
		return this.afectacion;
	}

	public void setAfectacion(Afectacion afectacion) {
		this.afectacion = afectacion;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

}