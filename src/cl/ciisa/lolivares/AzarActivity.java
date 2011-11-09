package cl.ciisa.lolivares;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AzarActivity extends Activity{
	
	protected ToggleButton btnOnOff;
	protected TextView texto;
	private Intent intent;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.azar);
        
        this.btnOnOff = (ToggleButton) findViewById(R.id.tbntOnOff);
        this.texto = (TextView) findViewById(R.id.textViewMostrar);
        intent = new Intent(this, BroadcastService.class);
        
        this.btnOnOff.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btnOnOff.isChecked()) {
					startService(intent);
					registerReceiver(broadcastReceiver, new IntentFilter(BroadcastService.BROADCAST_ACTION));
				} else {
					unregisterReceiver(broadcastReceiver);
					stopService(intent); 
				}
			}
        	
        });
    }
	
	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			updateUI(intent);       
		}
	}; 
	
	@Override
	public void onResume() {
		super.onResume();		
		//startService(intent);
		//registerReceiver(broadcastReceiver, new IntentFilter(BroadcastService.BROADCAST_ACTION));
	}
		
	@Override
	public void onPause() {
		super.onPause();
		//unregisterReceiver(broadcastReceiver);
		//stopService(intent); 		
	}	
		    
	private void updateUI(Intent intent) {
    	String texto = intent.getStringExtra("texto");
    	this.texto.setText(texto);
    }

}
