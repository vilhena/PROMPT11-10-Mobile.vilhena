package vilhena.prompt11.yambaapp;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TweetDetail detail1 = new TweetDetail();
		detail1.setUsername("user1");
		detail1.setText("text1");
		
		TweetDetail detail2 = new TweetDetail();
		detail2.setUsername("user2");
		detail2.setText("text2");
		
		YambaApp.timeline.add(detail1);
		YambaApp.timeline.add(detail2);
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this
				,R.layout.timeline_item_view
				,R.id.textTimelineText
				,YambaApp.GetTweets(this.getApplicationContext())
				);
		
		setListAdapter(adapter);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}
	
}

