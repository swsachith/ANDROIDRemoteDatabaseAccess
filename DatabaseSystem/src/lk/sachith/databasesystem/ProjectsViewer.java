package lk.sachith.databasesystem;

import com.example.databasesystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProjectsViewer extends Activity implements OnClickListener {

	private Button bViewByName, bViewAll,bViewByTags;
	private TextView display;
	private DatabaseManager manager;
	private String URL,query;
	private EditText etName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_projects);
		initialize();

	}

	private void initialize() {
		// TODO Auto-generated method stub
		bViewByName = (Button) findViewById(R.id.bPROJViewByName);
		bViewByTags = (Button) findViewById(R.id.bPROJViewByTag);
		bViewAll = (Button) findViewById(R.id.bPROJViewAll);
		bViewAll.setOnClickListener(this);
		bViewByName.setOnClickListener(this);
		bViewByTags.setOnClickListener(this);
		
		etName = (EditText) findViewById(R.id.etPROJViewByName);
		display = (TextView) findViewById(R.id.tvPROJView);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		query = etName.getText().toString();
		switch(v.getId()){
		case R.id.bPROJViewAll:
			URL = "http://10.0.2.2:80/dbProject/viewProjects.php";
			manager= new DatabaseManager(URL);
			String response = manager.viewAllProjects();
			if(!response.equals(null))
				display.setText(response);
			else
				display.setText("Something's Wrong");
			break;
		case R.id.bPROJViewByName:			
			URL = "http://10.0.2.2:80/dbProject/viewProjectsByName.php";
			manager= new DatabaseManager(URL);
			response = manager.viewProjectByName(query);
			if(response.trim().equals(null)){
				display.setText("No Such Project!!");
			}else
				display.setText(response);
			break;
		case R.id.bPROJViewByTag:
			URL = "http://10.0.2.2:80/dbProject/viewProjectsByTag.php";
			manager= new DatabaseManager(URL);
			response = manager.viewProjectByTag(query);
			if(response.trim().equals(null)){
				display.setText("No Such Project!!");
			}else
				display.setText(response);
			break;
		}

	}
	

}
