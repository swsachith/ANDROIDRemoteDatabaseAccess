package lk.sachith.databasesystem;

import java.util.ArrayList;
import java.util.List;

import com.example.databasesystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditProject extends Activity implements OnClickListener,
		OnItemSelectedListener{
	private Spinner spinnerProjects,spinnerStatus;
	private String URL,prID;
	private List<String> data;
	private EditText etDuration,etTags,etStartDate,etEndDate,etDescription;
	private Button submit;
	private String name,duration,tags,startdate,enddate,description,status; 

	private DatabaseManager dbmanager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_project);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		spinnerProjects = (Spinner) findViewById(R.id.spPROJEDITList);
		spinnerStatus = (Spinner) findViewById(R.id.spPROJAvailable);
		addProjectsToSpinner();				
		spinnerProjects.setOnItemSelectedListener(this);
		
		etDuration = (EditText) findViewById(R.id.etPROJEDITDuration);
		etTags = (EditText) findViewById(R.id.etPROJEDITTags);
		etStartDate = (EditText) findViewById(R.id.etPROJEDITStartDate);
		etEndDate = (EditText) findViewById(R.id.etPROJEDITEndDate);
		etDescription = (EditText) findViewById(R.id.etPROJEDITDescription);
		
		submit = (Button) findViewById(R.id.bPROJEditSubmit);
		submit.setOnClickListener(this);
	}

	public void addProjectsToSpinner() {
		URL = "http://10.0.2.2:80/dbProject/viewProjects.php";
		dbmanager = new DatabaseManager(URL);
		List<String> list = dbmanager.getProjects();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerProjects.setAdapter(dataAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bPROJEditSubmit:
			URL = "http://10.0.2.2:80/dbProject/editProjects.php";
			dbmanager = new DatabaseManager(URL);
			getData();
			dbmanager.updateProject(name, prID, duration, description, tags, status, startdate, enddate);
			fillForm();
			break;
		}
		// addIEmpsToSpinner();

	}

	// get the ids of the selected Items
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		name="";
		// TODO Auto-generated method stub
		String[] project = spinnerProjects.getSelectedItem().toString()
				.split(" ");
		prID = project[project.length - 1];
		for(int i = 0;i<project.length-1;i++){
			name += project[i]+" ";			
		}
		fillForm();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	private void fillForm(){		
		URL = "http://10.0.2.2:80/dbProject/viewProjectsById.php";
		dbmanager = new DatabaseManager(URL);
		data = dbmanager.getProjectByID(prID);
		etDuration.setText(data.get(0));
		etTags.setText(data.get(1));
		etStartDate.setText(data.get(2));
		etEndDate.setText(data.get(3));
		etDescription.setText(data.get(4));
		
	}
	private void getData(){
		duration = etDuration.getText().toString();
		tags = etTags.getText().toString();
		startdate = etStartDate.getText().toString();
		enddate = etEndDate.getText().toString();
		description = etDescription.getText().toString();
		status = spinnerStatus.getSelectedItem().toString();
	}

}
