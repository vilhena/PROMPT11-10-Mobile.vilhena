package vilhena.prompt11.yambaapp;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class TimelinePull extends IntentService{

	private Timer timer = null;
	private TimerTask tTask = null;
	
	private static String KEY = "Intent.Servive";
	
	public TimelinePull() {
		super(KEY);
		//settings = getSharedPreferences("yamba_prefs", 0);
		
		//timer = new Timer();
		//tTask = new TimerTask() {
		//	@Override
		//	public void run() {
		//		pullTimeline();
		//	}
		//};
		
	}
	
	private void pullTimeline() {
		String username = "username";
        String password = "password";
        String url = "http://yamba.marakana.com/api";
        
        Log.i("timeline", username + " " + password + " " + url);
        
        Twitter t =  new Twitter(username,password);
		
    	t.setAPIRootUrl(url);
    	
		Boolean ok = false;
		List<Twitter.Status> timeline = null;
		while (!ok) {
			try {
				timeline = t.getPublicTimeline();
				if(timeline != null){
					for (Twitter.Status status : timeline) {
						Log.i("timeline", String.format("user: %s", status
								.getUser().getName()));
						Log.i("timeline",
								String.format("text: %s", status.getText()));
					}
				}
				ok = true;

			} catch (TwitterException e) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
				}
			}
		}
	
	}


	@Override
	protected void onHandleIntent(Intent intent) {
		pullTimeline();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		
		//try{
		//	timer.schedule(tTask, 2000, 2000);
		//}catch(Exception ex){}
	};
	
	@Override
	public boolean stopService(Intent name) {
		//timer.cancel();
		
		return true;
	};
	
	
}
