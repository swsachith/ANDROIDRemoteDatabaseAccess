package lk.sachith.databasesystem;

import java.util.List;

import com.example.databasesystem.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Collaborators extends Activity implements OnClickListener,
		OnItemSelectedListener {
	private Spinner spinnerProjects, spinnerAdd, spinnerRemove,spinnerSups;
	private Button addColab, removeColab,addSupervisor;
	private String URL, prId, addEmpId, removeEmpId,supID;
	private DatabaseManager dbmanager;
	private TextView resultTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collaborators);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		spinnerProjects = (Spinner) findViewById(R.id.spCOLLABProjects);
		spinnerAdd = (Spinner) findViewById(R.id.spCOLLABAdd);
		spinnerRemove = (Spinner) findViewById(R.id.spCOLLABRemove);
		spinnerSups = (Spinner) findViewById(R.id.spCOLLABSups);
		addProjectsToSpinner();
		addIEmpsToSpinner();
		addColabsToSpinner();
		addColab = (Button) findViewById(R.id.bCOLLABAdd);
		removeColab = (Button) findViewById(R.id.bCOLLABRemove);
		addSupervisor = (Button) findViewById(R.id.bCOLLABSup);
		addColab.setOnClickListener(this);
		removeColab.setOnClickListener(this);
		addSupervisor.setOnClickListener(this);

		// resultTv = (TextView) findViewById(R.id.tvDEPTeditResult);
		spinnerProjects.setOnItemSelectedListener(this);
		spinnerAdd.setOnItemSelectedListener(this);
	    spinnerRemove.setOnItemSelectedListener(this);
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

	public void addIEmpsToSpinner() {
		URL = "http://10.0.2.2:80/dbProject/viewEmployees.php";
		dbmanager = new DatabaseManager(URL);
		List<String> list = dbmanager.getEmployees();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerAdd.setAdapter(dataAdapter);
		spinnerSups.setAdapter(dataAdapter);
	}

	public void addColabsToSpinner() {
		URL = "http://10.0.2.2:80/dbProject/getCollaborators.php";
		dbmanager = new DatabaseManager(URL);
		List<String> list = dbmanager.getCollaborators(prId);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerRemove.setAdapter(dataAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bCOLLABAdd:
			URL = "http://10.0.2.2:80/dbProject/addCollaborator.php";
			dbmanager = new DatabaseManager(URL);
			dbmanager.addCollaborator(addEmpId, prId);
			addColabsToSpinner();
			break;
		case R.id.bCOLLABRemove:
			URL = "http://10.0.2.2:80/dbProject/removeCollaborator.php";
			dbmanager = new DatabaseManager(URL);
			dbmanager.removeCollaborator(removeEmpId, prId);
			addColabsToSpinner();
			break;
		case R.id.bCOLLABSup:
			URL = "http://10.0.2.2:80/dbProject/getSupervisor.php";
			dbmanager = new DatabaseManager(URL);
			if(dbmanager.getSupervisor(supID).equals(null)){
				URL = "http://10.0.2.2:80/dbProject/updateSupervisor.php";
				DatabaseManager tempManager = new DatabaseManager(URL);
				tempManager.updateSupervisor(supID, prId);
			}else{
				URL = "http://10.0.2.2:80/dbProject/addSupervisor.php";
				DatabaseManager tempManager = new DatabaseManager(URL);
				tempManager.updateSupervisor(supID, prId);
			}
			break;
		}
		// addIEmpsToSpinner();

	}

	// get the ids of the selected Items
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		String[] project = spinnerProjects.getSelectedItem().toString()
				.split(" ");
		prId = project[project.length - 1];

		String[] addEmployee = spinnerAdd.getSelectedItem().toString()
				.split(" ");
		addEmpId = addEmployee[addEmployee.length - 1];

		if (!(spinnerRemove.getSelectedItem()==null)) {
			String[] removeEmployee = spinnerRemove.getSelectedItem()
					.toString().split(" ");
			removeEmpId = removeEmployee[removeEmployee.length - 1];

			
		}
		if (!(spinnerSups.getSelectedItem()==null)) {
			String[] sups = spinnerSups.getSelectedItem()
					.toString().split(" ");
			supID = sups[sups.length - 1];

			
		}
		addColabsToSpinner();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
