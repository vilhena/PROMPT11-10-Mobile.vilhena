package vilhena.prompt11.yambaapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

public class ConectivityBroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("ConectivityBroadCastReceiver", "NetworkChanged");
		boolean isNetworkDown = intent.getBooleanExtra( ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
		Log.d("ConectivityBroadCastReceiver", "Network is down " + String.valueOf(isNetworkDown));
		
		
		context.startService(new Intent(context,TimelinePull.class));
		
		
	}
	
	

}
