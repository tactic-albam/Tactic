package com.tacticlogistics.integrador.etl.model.etl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringEscapeUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(catalog = "etl", name = "tipos_archivo")
@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class TipoArchivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_archivo")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_grupo_integracion")
	@NotNull
	private GrupoIntegracion grupoInteracion;

	@Column(length = 50, nullable = false)
	@NotNull
	private String codigo;

	@Column(length = 100, nullable = false)
	@NotNull
	private String nombre;

	@Column(length = 200, nullable = false)
	@NotNull
	private String descripcion;

	@Column(length = 4, nullable = false)
	@NotNull
	private String separadorRegistros;

	@Column(length = 4, nullable = false)
	@NotNull
	private String separadorCampos;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_tipo_archivo", nullable = false)
	private List<Campo> campos;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tipos_archivos_campos_llave", catalog = "etl", joinColumns = @JoinColumn(name = "id_tipo_archivo", referencedColumnName = "id_tipo_archivo", nullable = false))
	private Set<CampoLlave> camposLlave;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tipos_archivos_configuraciones", catalog = "etl", joinColumns = @JoinColumn(name = "id_tipo_archivo", referencedColumnName = "id_tipo_archivo", nullable = false))
	private Set<TipoArchivoConfiguracion> configuraciones;

	private int ordinal;
	private boolean activo;

	@Column(nullable = false)
	@NotNull
	private LocalDateTime fechaActualizacion;

	@Column(length = 50, nullable = false)
	@NotNull
	private String usuarioActualizacion;

	public String getUnescapedSeparadorRegistros() {
		String result = StringEscapeUtils.unescapeJava(this.separadorRegistros);
		return result;
	}

	public String getUnescapedSeparadorCampos() {
		String result = StringEscapeUtils.unescapeJava(this.separadorCampos);
		return result;
	}

	public String getRegExpSeparadorRegistros() {
		if (separadorRegistros.startsWith("\\")) {
			return "[" + separadorRegistros + "]";
		}
		return separadorRegistros;
	}

	public String getRegExpSeparadorCampos() {
		if (separadorCampos.startsWith("\\")) {
			return "[" + separadorCampos + "]";
		}
		return separadorCampos;
	}

	public List<Campo> getCamposNoIgnorados() {
		// @formatter:off
		List<Campo> result = this.getCampos()
				.stream()
				.filter(a -> !a.isIgnorar())
				.collect(Collectors.toList());
		// @formatter:on

		return result;
	}

	public List<Campo> getCamposNoIncluidos() {
		// @formatter:off
		List<Campo> result = this.getCampos()
				.stream()
				.filter(a -> !a.isIncluir())
				.sorted((a, b) -> Integer.compare(a.getOrdinal(), b.getOrdinal()))
				.collect(Collectors.toList());
		// @formatter:on
		return result;
	}

	public List<String> getLlaves() {
		// @formatter:off
		List<String> result = this
				.getCamposLlave()
				.stream()
				.map(a -> a.getLlave())
				.distinct()
				.collect(Collectors.toList());
		// @formatter:on

		return result;
	}

	public List<String> getCodigosDeCamposPorLlave(String llave) {
		final List<String> result = new ArrayList<>();
		// @formatter:off
		List<Long> campos = this
				.getCamposLlave()
				.stream()
				.filter( a -> a.getLlave().equals(llave))
				.map(a -> a.getIdCampo())
				.distinct()
				.collect(Collectors.toList());
		// @formatter:on

		this.getCampos().forEach(a -> {
			if (campos.contains(a.getId())) {
				result.add(a.getCodigo());
			}
		});

		return result;
	}

	public Optional<Campo> getCampoPorCodigo(String codigo) {
		final Optional<Campo> result;
		// @formatter:off
		result = this
				.getCampos()
				.stream()
				.filter( a -> a.getCodigo().equals(codigo))
				.findFirst();
		// @formatter:on

		return result;
	}

}
