package vilhena.prompt11.yambaapp;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public final class SendUpdateIntentService extends IntentService {

	private static String SERVICE_KEY ="SendUpdateIntentService";
	
	public SendUpdateIntentService() {
		super(SERVICE_KEY);
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		Bundle data = arg0.getBundleExtra("XPTO");
		
		String text = data.getString("text");
        String username = data.getString("username");
        String password = data.getString("password");
        String url = data.getString("url");
        
        
        Log.i("VILHENA",text + " " + username + " " + password + " " + url);
        
        Twitter t =  new Twitter(
				username
				,password);
		
    	t.setAPIRootUrl(url);
    	
    	Boolean ok = false;
    	
    	while(!ok){
			try {
				t.updateStatus(text);
				ok = true;
				
			} catch (TwitterException e) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
    	}	
	}

}
