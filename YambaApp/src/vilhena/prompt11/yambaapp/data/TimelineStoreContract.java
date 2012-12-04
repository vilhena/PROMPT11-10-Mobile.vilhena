package vilhena.prompt11.yambaapp.data;

import vilhena.prompt11.yambaapp.applications.YambaApplication;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public final class TimelineStoreContract {

	public static final String AUTHORITY = "yambaapp.timeline";
	public static final Uri CONTENT_URI = Uri.parse("content://yambaapp.timeline");
	
	public static final String ITEM_LIST_URI_FORMAT = "/user";
	public static final String ITEM_ID_URI_FORMAT = "/user/%s";

	
	public static final String TWEET_ID = "_id";
	public static final String TWEET_CREATED_AT = "CreatedAt";
	public static final String TWEET_USER_NAME = "User_Name";
	public static final String TWEET_TEXT = "Text";
	
	
	// Data types
	public static final String TIMELINE_ITEM_TYPE = "vnd.android.cursor.item/status";
	public static final String TIMELINE_LIST_TYPE = "vnd.android.cursor.dir/status";
	
	public static final String PUBLIC_TIMELINE_ITEM_TYPE = "vnd.android.cursor.item/status";
	public static final String PUBLIC_TIMELINE_LIST_TYPE = "vnd.android.cursor.dir/status";
	
	
	
	private static final String[] _ColumnNames = {TWEET_ID, TWEET_CREATED_AT, TWEET_USER_NAME, TWEET_TEXT};
	
	
	public static String[] getColumnNames(){
		return _ColumnNames;
	}
	
	public static Cursor adapt(Iterable<Twitter.Status> tweets){
		YambaApplication.Log("TimelineStoreContract.adapt");
		return adapt(tweets,_ColumnNames);
	}
	
	
	public static Cursor adapt(Iterable<Twitter.Status> tweets,
			String[] projection){
		YambaApplication.Log("TimelineStoreContract.adapt generic");
		
		MatrixCursor cursor = new MatrixCursor(projection);
		
		for (Status status : tweets) {
			MatrixCursor.RowBuilder builder = cursor.newRow();
			
			for (String selection : projection) {
				if(selection == TWEET_ID){
					builder.add(status.getId());
				}else if(selection == TWEET_CREATED_AT){
					builder.add(YambaApplication.dateToMessage(status.getCreatedAt()));
				}else if(selection == TWEET_USER_NAME){
					builder.add(status.getUser().getScreenName());
				}else if(selection == TWEET_TEXT){
					builder.add(status.getText());
				}else {
					return null;
				}
			}
			
		}
		
		YambaApplication.Log("TimelineStoreContract.adapt generic end");
		return cursor;
	}

	
}
