package vilhena.prompt11.yambaapp.data;

import vilhena.prompt11.yambaapp.activities.BaseActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class DatabaseTimelineStore implements ITimelineStore{

	private DbHelper _dbHelper;
	
	private static final int VERSION = 1;
	
	private static final String DATABASE_NAME = "yambaappdb";
	private static final String TABLE = "timeline";
	
	
	public DatabaseTimelineStore(Context context) {
		// TODO Auto-generated constructor stub
		_dbHelper = new DbHelper(context);
	}
	
	private static class DbHelper extends SQLiteOpenHelper{

		private SQLiteDatabase _db;
		
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, VERSION);
			_db = context.openOrCreateDatabase(DATABASE_NAME, 0, null);
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			initDatabase();
		}

		private void initDatabase() {
			// TODO Auto-generated method stub
			_db.execSQL("create table " + TABLE + " (" + getColumnsForCreate() +");");
		}
		
		private String getColumnsForCreate(){
			StringBuilder sb = new StringBuilder();
			
			sb.append(TimelineStoreContract.TWEET_ID).append(" integer primary key");
			sb.append(",").append(TimelineStoreContract.TWEET_USER_NAME).append(" string ");
			sb.append(",").append(TimelineStoreContract.TWEET_CREATED_AT).append(" string ");
			sb.append(",").append(TimelineStoreContract.TWEET_TEXT).append(" string ");
			
			return sb.toString();
		}

		
		
		
		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			_db.execSQL("truncate table " + TABLE);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see vilhena.prompt11.yambaapp.data.ITimelineStore#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
	 */
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteDatabase database =  _dbHelper.getReadableDatabase();
		return database.query(TABLE, TimelineStoreContract.getColumnNames(), selection, selectionArgs, null, null, sortOrder);
	}
	
	/* (non-Javadoc)
	 * @see vilhena.prompt11.yambaapp.data.ITimelineStore#insert(android.content.ContentValues)
	 */
	public int insert(ContentValues values){
		int count = 0;
	
		return count;
	}
	
}
