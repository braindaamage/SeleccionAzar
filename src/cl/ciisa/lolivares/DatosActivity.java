package cl.ciisa.lolivares;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DatosActivity extends Activity{
	
	protected LinearLayout mainLayout;
	protected LinearLayout datosLayout;
	protected Button btnAgregar;
	protected Button btnBorrar;
	protected EditText textoAgregar;
	protected DBHelper dh;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos);
        
        this.mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        this.datosLayout = (LinearLayout) findViewById(R.id.datosLayout);
        this.btnAgregar = (Button) findViewById(R.id.btnAgregar);
        this.btnBorrar = (Button) findViewById(R.id.btnEliminar);
        this.textoAgregar = (EditText) findViewById(R.id.editTextIngreso);
        this.dh = new DBHelper(this); 
        
        initListeners();
    }
	
	public void onResume() {
    	super.onResume();
    	if (!this.dh.selectAll().isEmpty()) {
    		List<String> lista = this.dh.selectAll();
    		for(String dato: lista) {
    			TextView tv = new TextView(this);
    			tv.setText(dato);
    			this.datosLayout.addView(tv);
    		}
    	}
    }
	
	private void initListeners(){
		
		this.btnBorrar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				dh.deleteAll();
				datosLayout.removeAllViews();
				
				InputMethodManager imm = (InputMethodManager)
				getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(textoAgregar.getWindowToken(), 0);
			}
			
		});
		
		this.btnAgregar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				agregarDato(textoAgregar.getText().toString());
				
				InputMethodManager imm = (InputMethodManager)
				getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(textoAgregar.getWindowToken(), 0);
			}
			
		});
		
		this.textoAgregar.setOnKeyListener(new View.OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
			            (keyCode == KeyEvent.KEYCODE_ENTER)) {
					agregarDato(textoAgregar.getText().toString());
					
					InputMethodManager imm = (InputMethodManager)
					getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(textoAgregar.getWindowToken(), 0);
					
					return true;
				}
				
				return false;
			}
		});
	}
	
	private void agregarDato(String texto) {
		this.dh.insert(textoAgregar.getText().toString());
		TextView tv = new TextView(this);
		tv.setText(texto);
		this.datosLayout.addView(tv);
		this.textoAgregar.setText("");
	}
}
