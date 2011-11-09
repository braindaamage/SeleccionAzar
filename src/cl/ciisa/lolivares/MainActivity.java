package cl.ciisa.lolivares;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	protected Button btnDatos;
	protected Button btnAzar;
	protected DBHelper dh;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnDatos = (Button) findViewById(R.id.btnDatos);
        btnAzar = (Button) findViewById(R.id.btnSeleccion);
        
        initListeners();
    }
    
    public void onResume() {
    	super.onResume();
    	this.dh = new DBHelper(this);
    	if (this.dh.selectAll().isEmpty()) {
    		btnAzar.setEnabled(false);
    	} else {
    		btnAzar.setEnabled(true);
    	}
    }
    
    private void initListeners(){
    	
    	this.btnDatos.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intento = new Intent(v.getContext(), DatosActivity.class);
				startActivity(intento);
			}
    		
    	});
    	
    	this.btnAzar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intento = new Intent(v.getContext(), AzarActivity.class);
				startActivity(intento);
			}
    		
    	});
    }
}