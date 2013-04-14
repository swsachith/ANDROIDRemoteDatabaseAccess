package lk.sachith.databasesystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.databasesystem.R;

public class ProjectsDeleter extends Activity implements OnClickListener{
	private DatabaseManager manager;
	private EditText etID;
	private Button bDelete;
	private TextView tvResult;
	final String URL = "http://10.0.2.2:80/dbProject/deleteProject.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_project);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		tvResult = (TextView) findViewById(R.id.tvPROJDeleteResult);
		etID = (EditText) findViewById(R.id.etPROJDELETEId);
		bDelete = (Button) findViewById(R.id.bPROJDelete);
		bDelete.setOnClickListener(this);
		manager = new DatabaseManager(URL);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String id = etID.getText().toString();
		if(!id.equals(null)){
			if(manager.deleteProject(id)){
				tvResult.setText("Success!!");
			}else{
				tvResult.setText("Department Does not Exist");
				
			}
				
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}
