package lk.sachith.databasesystem;

import java.util.List;

import com.example.databasesystem.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class DepartmentEditor extends Activity implements OnClickListener,OnItemSelectedListener{
	private Spinner spinnerDepts,spinnerNames;
	private Button submit;
	private String URL,deptId;
	private DatabaseManager dbmanager;
	private TextView resultTv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_department);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		spinnerDepts = (Spinner) findViewById(R.id.spDEPTEditDept);
		spinnerNames = (Spinner) findViewById(R.id.spDEPTEditEmp);
		addIDeptsToSpinner();
		addIEmpsToSpinner();
		submit = (Button) findViewById(R.id.bDPTEditSubmit);
		submit.setOnClickListener(this);
		resultTv = (TextView) findViewById(R.id.tvDEPTeditResult);
		spinnerDepts.setOnItemSelectedListener(this);
		spinnerNames.setOnItemSelectedListener(this);
		
	}
	public void addIDeptsToSpinner() {
		URL = "http://10.0.2.2:80/dbProject/viewDepartments.php";
		dbmanager = new DatabaseManager(URL);
		List<String> list = dbmanager.getDepartments();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDepts.setAdapter(dataAdapter);
	  }
	public void addIEmpsToSpinner() {
		URL = "http://10.0.2.2:80/dbProject/viewEmployees.php";
		dbmanager = new DatabaseManager(URL);
		List<String> list = dbmanager.getEmployees();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerNames.setAdapter(dataAdapter);
	  }

	@Override
	public void onClick(View v) {
		URL = "http://10.0.2.2:80/dbProject/updateManager.php";
		dbmanager = new DatabaseManager(URL);		
		String name = spinnerNames.getSelectedItem().toString();
		String[] empResult = name.split(" ");		
		String empid = empResult[empResult.length-1];
		if(!dbmanager.updateManager(empid, deptId)){
			resultTv.setTextColor(Color.RED);
			resultTv.setText("Choose an available person");
		}else
			resultTv.setText("Success!!");
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		URL = "http://10.0.2.2:80/dbProject/viewDepartments.php";
		dbmanager = new DatabaseManager(URL);
		String department = spinnerDepts.getSelectedItem().toString();
		deptId = dbmanager.getDepartmentId(department);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
