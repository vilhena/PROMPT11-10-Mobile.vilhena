package vilhena.prompt11.yambaapp.receivers;

import vilhena.prompt11.yambaapp.applications.YambaApplication;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class ConnectivityBroadcastReceiver extends BroadcastReceiver {

	public ConnectivityBroadcastReceiver() {
		
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		YambaApplication.Log("ConectivityBroadCastReceiver.onReceive");
		boolean isNetworkDown = intent.getBooleanExtra( ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
		YambaApplication.Log("ConectivityBroadCastReceiver Network is down " + String.valueOf(isNetworkDown));
		
		//TODO: do some code
	}
	
	

}