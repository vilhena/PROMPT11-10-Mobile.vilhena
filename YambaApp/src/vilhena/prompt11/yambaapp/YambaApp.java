package vilhena.prompt11.yambaapp;

import java.util.ArrayList;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.location.Address;
import android.view.View;
import android.widget.TextView;





public final class YambaApp {

	public static ArrayList<TweetDetail> timeline = new ArrayList<TweetDetail>();
	
	public static ArrayList<String> GetTweets(Context context){
		ArrayList<String> viewList = new ArrayList<String>(timeline.size());
		
		
		for (TweetDetail tweet : timeline) {
			viewList.add(tweet.getText());
		}
		
		return viewList;
	}
	
	
	public static void enableAutoSync(Context ctx){
		//AlarmManager alarm = (AlarmManager) ctx.getApplicationContext().getSystemService(ALARM_SERVICE);
		
		
		
		//PendingIntent operation = PendingIntent.getService(ctx, requestCode, intent,)
		
		//alarm.setInexactRepeating(0, 0, 3000, operation);
	}
	
	public static void disableAutoSync(){
		
	}
	
}
