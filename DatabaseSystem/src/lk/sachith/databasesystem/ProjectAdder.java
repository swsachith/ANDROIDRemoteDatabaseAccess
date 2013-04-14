package lk.sachith.databasesystem;

import com.example.databasesystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProjectAdder extends Activity implements OnClickListener{
	private EditText etName,etDuration,etTags,etDescription;
	private Button submit;
	private final String URL = "http://10.0.2.2:80/dbProject/addProject.php";
	private DatabaseManager dbManager;
	private String name,duration,tags,description;
	private TextView validity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_project);
		initialize();
		dbManager = new DatabaseManager(URL);
	}
	private void initialize() {
		// TODO Auto-generated method stub
		validity = (TextView) findViewById(R.id.tvPROJADDInvalid);
		etName = (EditText) findViewById(R.id.etPROJADDName);
		etDuration = (EditText) findViewById(R.id.etPROJADDDuration);
		etTags = (EditText) findViewById(R.id.etPROJADDTags);
		etDescription = (EditText) findViewById(R.id.etPROJADDDescrip);
		submit = (Button) findViewById(R.id.bPROJADDSubmit);
		submit.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		name = etName.getText().toString();
		duration = etDuration.getText().toString();
		tags = etTags.getText().toString();
		description = etDescription.getText().toString();
		if(name != null && duration != null && tags != null && description != null){
			dbManager.addProject(name, duration, description, tags);
			validity.setText("Success!!");
		}else{
			validity.setText("Input is missing");
		}
	}
	

}
