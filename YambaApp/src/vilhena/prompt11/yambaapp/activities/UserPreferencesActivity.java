package vilhena.prompt11.yambaapp.activities;

import vilhena.prompt11.yambaapp.R;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class UserPreferencesActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.yamba_prefs);
	}
}
