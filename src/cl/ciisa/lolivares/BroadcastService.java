package cl.ciisa.lolivares;


import java.util.List;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;


public class BroadcastService extends Service 
{
	private static final String TAG = "BroadcastService";
	private final Handler handler = new Handler();
	private Intent intent;
	private DBHelper dh;
	private List<String> lista;
	private Random indexAleatorio;
	public static final String BROADCAST_ACTION = "cl.ciisa.lolivares.displayevent";
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.dh = new DBHelper(this);
		this.lista = this.dh.selectAll();
		this.indexAleatorio = new Random();
		
    	intent = new Intent(BROADCAST_ACTION);	
	}
	
    @Override
    public void onStart(Intent intent, int startId) {
    	Log.d(TAG, "Broadcast: onStart");
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 10);//1 second
	}

    private Runnable sendUpdatesToUI = new Runnable() {
    	public void run() {
    		Log.d(TAG, "Broadcast: sendUpdatesToUI");
    		DisplaySecondsRemaining();    		
    	    handler.postDelayed(this, 10); // 1 second
    	}
    };    
    
    private void DisplaySecondsRemaining() {
    	intent.putExtra("texto",this.lista.get((int)(this.indexAleatorio.nextDouble() * (double)this.lista.size())));
    	sendBroadcast(intent);
    }
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {		
        handler.removeCallbacks(sendUpdatesToUI);		
		super.onDestroy();
	}
}
