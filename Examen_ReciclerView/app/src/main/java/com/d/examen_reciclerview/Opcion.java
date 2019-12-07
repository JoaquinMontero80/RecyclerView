package com.d.examen_reciclerview;

import android.widget.CheckBox;

// Clase que se usa para definir las opciones del listado del ListActivity
public class Opcion
{
	// Cada opción tiene un checkbox, un título y un icono
    private boolean checkBox;
	private String titulo;
	private int icono;

	public Opcion(Boolean checkBox, String titulo, int icono){
		this.checkBox = checkBox;
	    this.setTitulo(titulo);
		this.setIcono(icono);
	}

    // Definimos los getters y setters de la clase
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isCheckBox() {
		return checkBox;
	}

	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	public int getIcono() {
		return icono;
	}

	public void setIcono(int icono) {
		this.icono = icono;
	}

	}
