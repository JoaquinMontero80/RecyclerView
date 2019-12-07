
package com.d.examen_reciclerview;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.widget.Toast.makeText;


public class RecyclerViewActivity extends Activity 
{
	// Usamos esta matriz dinámica (ArrayList) para guardar los datos de las 32 opciones del listado
	private ArrayList<Opcion> datos;

	// Lista del tipo RecyclerView
	private RecyclerView recView;

	// Botones con las nuevas opciones
	private Button btnAceptar;
	private CheckBox checkBox;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
		// Buscamos en el layout la lista RecyclerView
		recView = (RecyclerView) findViewById(R.id.RecView);
	
		// Indicamos que el tamaño del RecyclerView no cambia
        recView.setHasFixedSize(true);
		
		datos = new ArrayList<Opcion>();

		// Definimos las Opciones del listado en la matriz
		datos.add(new Opcion(false, "Televisión", R.drawable.tv));
		datos.add(new Opcion(false, "Teléfono móvil", R.drawable.smartphone));
		datos.add(new Opcion(false, "Tableta", R.drawable.tablet));
		datos.add(new Opcion(false, "Ordenador fijo", R.drawable.ordenador_fijo));
		datos.add(new Opcion(false, "Consola", R.drawable.consola));
		datos.add(new Opcion(false, "Ordenador portátil", R.drawable.ordenador_portatil));
		datos.add(new Opcion(false, "Reloj", R.drawable.reloj));



		// Creamos el Adaptador que se usa para mostrar las opciones del listado
		final AdaptadorOpciones adaptador = new AdaptadorOpciones(datos);
		

		// Asignamos el adaptador al RecyclerView para que sepa cómo dibujar el listado de opciones
		recView.setAdapter(adaptador);
		
		// Muy importante indicar el tipo de Layout. ¡Obligatorio!!!
		recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		
		// Animador de la lista
		recView.setItemAnimator(new DefaultItemAnimator());

		// Ahora definimos los eventos onClick de los botones
		btnAceptar = (Button)findViewById(R.id.btnAceptar);
		checkBox = (CheckBox)findViewById(R.id.checkbox);


		// Definimos el evento onClick del adaptador
		adaptador.setOnClickListener(v ->
		{

			if(!datos.get(recView.getChildAdapterPosition(v)).isCheckBox())
			{
				datos.get(recView.getChildAdapterPosition(v)).setCheckBox(true);
				Toast.makeText(getBaseContext(), "Has hecho click en  '" + datos.get(recView.getChildAdapterPosition(v)).getTitulo()
						+ "'", Toast.LENGTH_SHORT).show();
			}
			else
			{
				datos.get(recView.getChildAdapterPosition(v)).setCheckBox(false);
			}

			adaptador.notifyItemChanged(recView.getChildAdapterPosition(v));


		});

		// Definimos el evento del botón
		btnAceptar.setOnClickListener(v ->
		{

			// Recoge posicion seleccionada del checkBox
			ArrayList<Opcion> checked = new ArrayList<>();

			// Recorre el arrayList
			for (int i=0; i<datos.size(); i++)
			{
				// Si hay algún checkBox seleccionado
				if(datos.get(i).isCheckBox())
					checked.add(datos.get(i)); // Recoge la posicion seleccionada
			}

			// Imprime mensaje si no ha seleccionado nada || seleccionando 1 checkBox || o varios checkBox
			if(checked.size()==0) // Si no hay checkBox seleccionado
				Toast.makeText(getBaseContext(), "No utilizas ningun dispositivo para navegar por internet.", Toast.LENGTH_SHORT).show();
			else if(checked.size()==1)
			{
				if(checked.get(0).getTitulo().equalsIgnoreCase("Televisión") || checked.get(0).getTitulo().equalsIgnoreCase("Consola")|| checked.get(0).getTitulo().equalsIgnoreCase("Tableta"))
				{
					Toast.makeText(getBaseContext(), "Para navegar en internet solamente utilizas la "+checked.get(0).getTitulo()+ ".", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(getBaseContext(), "Para navegar en internet solamente utilizas el "+checked.get(0).getTitulo()+ ".", Toast.LENGTH_SHORT).show();
			}
			else
			{
				String mensaje = "";

				// Recorre el ArrayList y si no es el final imprime ",", si es final "."
				for(int a=0; a<checked.size(); a++)
				{
					// Si no es el final imprime "," si es final "."
					if(!(checked.size()-1 == a))
					{
						if(checked.get(0).getTitulo().equalsIgnoreCase("Televisión") || checked.get(0).getTitulo().equalsIgnoreCase("Consola")|| checked.get(0).getTitulo().equalsIgnoreCase("Tableta"))
							mensaje += "la " + checked.get(a).getTitulo() + ", ";
						else
							mensaje += "el " + checked.get(a).getTitulo() + ", ";
					}
					else
					{

						if(checked.get(0).getTitulo().equalsIgnoreCase("Televisión") || checked.get(0).getTitulo().equalsIgnoreCase("Consola")|| checked.get(0).getTitulo().equalsIgnoreCase("Tableta"))
							mensaje += "y la " + checked.get(a).getTitulo() + ".";
						else
							mensaje += "y el " + checked.get(a).getTitulo() + ".";

					}

				}

				Toast.makeText(getBaseContext(), "Para navegar en internet utilizas " + mensaje, Toast.LENGTH_SHORT).show();
			}



		});

	} // end onCreate

} // end class