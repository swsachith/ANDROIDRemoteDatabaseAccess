package lk.sachith.databasesystem;

import com.example.databasesystem.R;

import android.os.Bundle;
import android.renderscript.Int2;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	TextView tvValid;
	Button bSubmit;
	EditText name, pass;
	private DatabaseManager dbManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}

	public void initialize() {
		tvValid = (TextView) findViewById(R.id.tvLoginValidity);
		bSubmit = (Button) findViewById(R.id.bLoginSubmit);
		bSubmit.setOnClickListener(this);
		name = (EditText) findViewById(R.id.etLoginName);
		pass = (EditText) findViewById(R.id.etLoginPass);
		dbManager = new DatabaseManager("http://10.0.2.2:80/dbProject/login.php");
		
	}

	@Override
	public void onClick(View v) {
		if(name ==null || pass == null)
			return;
		else{
			if(dbManager.checkPassword(name.getText().toString(), pass.getText().toString())){
				tvValid.setText("Correct!!");
				Intent i  = new Intent("android.intent.action.MENU");
				startActivity(i);
			}else{
				tvValid.setText("Invalid Username or Password");
				
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
