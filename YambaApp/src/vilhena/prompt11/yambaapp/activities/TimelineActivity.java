package vilhena.prompt11.yambaapp.activities;

import vilhena.prompt11.yambaapp.R;
import vilhena.prompt11.yambaapp.applications.YambaApplication;
import vilhena.prompt11.yambaapp.data.TimelineStoreContract;
import vilhena.prompt11.yambaapp.utils.TimelineAdapter;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TimelineActivity extends BaseActivity implements OnItemClickListener {
	
	
	private Cursor _cursor;
	private TimelineAdapter _adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		YambaApplication.Log("TimelineActivity.onCreate");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline_view);
		init();
	}
	
	

	private void init() {
		YambaApplication.Log("TimelineActivity.init");
		_application = (YambaApplication) getApplication();
		
		
		
		ListView listView = (ListView) findViewById(R.id.TimelinelistView);
		
		//TODO: remove just for testing
		_application.AddSomeSampleTweets(1000);
		
		//Get all items from timeline
		_cursor = getContentResolver().query(
				Uri.parse(TimelineStoreContract.CONTENT_URI.toString() + TimelineStoreContract.ITEM_LIST_URI_FORMAT), 
				new String[]{
					TimelineStoreContract.TWEET_ID,
					TimelineStoreContract.TWEET_CREATED_AT,
					TimelineStoreContract.TWEET_USER_NAME,
					TimelineStoreContract.TWEET_TEXT
				}, 
				null, 
				null,
				null
				);
		//_cursor = TimelineStoreContract.adapt(_application.getTimelineStore());
		
		_adapter = new TimelineAdapter(this,_cursor);
		
		startManagingCursor(_cursor);
		
		listView.setOnItemClickListener(this);
		listView.setAdapter(_adapter);
		
	}


	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		TimelineAdapter.RowData data = TimelineAdapter.getDataFromTag(arg1);
		YambaApplication.Log("clickon " + data.getId());
		
		_cursor = getContentResolver().query(
				Uri.parse(TimelineStoreContract.CONTENT_URI.toString() + String.format(TimelineStoreContract.ITEM_ID_URI_FORMAT,data.getId())), 
				new String[]{
					TimelineStoreContract.TWEET_ID,
					TimelineStoreContract.TWEET_CREATED_AT,
					TimelineStoreContract.TWEET_USER_NAME,
					TimelineStoreContract.TWEET_TEXT
				}, 
				null, 
				null,
				null
				);
		
		YambaApplication.Log("specific tweet find count" + _cursor.getCount());
		
		// direct intent
		Intent intent = new Intent(this, DetailActivity.class);
		Bundle bundle = new Bundle();
		
		//TODO: query on this
		bundle.putString(TimelineStoreContract.TWEET_ID, data.getId());
		bundle.putString(TimelineStoreContract.TWEET_CREATED_AT, data.getCreated().getText().toString());
		bundle.putString(TimelineStoreContract.TWEET_USER_NAME, data.getUser().getText().toString());
		bundle.putString(TimelineStoreContract.TWEET_TEXT, data.getText().getText().toString());
		
		intent.putExtra(DetailActivity.KEY_INTENT_DATA, bundle);
		
		startActivity(intent);
	}
}
