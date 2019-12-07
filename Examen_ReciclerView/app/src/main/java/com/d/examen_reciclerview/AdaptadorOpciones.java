package com.d.examen_reciclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


// Definimos el Adaptador a partir de la clase RecyclerView.Adapter
// que dibuja la opciones del listado del tipo RecyclerView.
// Además, implementa el evento onClick del mismo
class AdaptadorOpciones extends RecyclerView.Adapter<OpcionViewHolder> implements View.OnClickListener
{
	// Matriz dinámica para guardar los datos
	private ArrayList<Opcion> datos;
	// Variable para guardar el puntero al método onClick para llamarlo cuando sea necesario
	private View.OnClickListener listener;

	// Contructor del adaptador usando una matriz dinámica del tipo Opcion
	AdaptadorOpciones(ArrayList<Opcion> datos) {
		// ¡OJO! NO es necesario invocar al constructor de la clase superior
		// Guardamos estas variables de la aplicación principal para usarlas luego
		this.datos = datos;
	}
	
	// Evento que se lanza cuando ya es necesario dibujar una opción del listado
	@Override
	public OpcionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
	{
		// Inflamos la opción con el layout correspondiente
		View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.opcion, viewGroup, false);
		// Establecemos el evento onClick de la opción
		itemView.setOnClickListener(this);
		// Definimos la nueva opción a partir del elemento anterior
		OpcionViewHolder ovh = new OpcionViewHolder(itemView);
		// Devolvemos ya la opción dentro la clase OpcionViewHolder
		return ovh;
	}
	
	// Método que se lanza cada vez que Android debe dibujar una opción en una determinada posición
	@Override
	public void onBindViewHolder(OpcionViewHolder viewHolder, int pos) {
		// Simplemente optenemos los datos en esa posición
		Opcion item = datos.get(pos);
		// Añadimos (bind=ligamos) al ViewHolder los datos
		viewHolder.bindOpcion(item);
	}

	// Devuelve el nº de elementos de la lista
	@Override
	public int getItemCount() {
		return datos.size();
	}
	
	// Método para establecer el evento onClick de la lista
	public void setOnClickListener(View.OnClickListener listener) {
		this.listener = listener;
	}
	
	// Método que se ejecuta cuando se invoca el evento onClick
	@Override
	public void onClick(View view) {
		// ¡Sólo se invoca si está establecido previamente!
		if(listener != null)
			listener.onClick(view);
	}

} // end class AdaptadorOpciones

// Clase que se usa para almacenar las 2 etiquetas de tipo TextView y un icono de tipo ImageView de una opción
class OpcionViewHolder extends RecyclerView.ViewHolder {
	private TextView titulo;
	private CheckBox checkBox;
	private ImageView icono;

	public OpcionViewHolder(View itemView) {
		super(itemView);

		titulo = (TextView)itemView.findViewById(R.id.LblTitulo);
		checkBox = (CheckBox) itemView.findViewById(R.id.checkbox1);
		icono = (ImageView) itemView.findViewById(R.id.icono);
	}

	public void bindOpcion(Opcion t) {
		titulo.setText(t.getTitulo());
		checkBox.setChecked(t.isCheckBox());
		icono.setImageResource(t.getIcono());
	}
}

