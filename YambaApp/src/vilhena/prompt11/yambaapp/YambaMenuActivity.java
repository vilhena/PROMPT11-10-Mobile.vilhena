package vilhena.prompt11.yambaapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class YambaMenuActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.yamba_prefs);
	}
}
