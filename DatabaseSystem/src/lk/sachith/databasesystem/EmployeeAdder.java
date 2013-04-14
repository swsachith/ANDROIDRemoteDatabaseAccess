package lk.sachith.databasesystem;


import java.util.ArrayList;
import java.util.List;

import com.example.databasesystem.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EmployeeAdder extends Activity implements OnItemSelectedListener,OnClickListener{
	private Spinner spinnerDepts,spinnerGender;
	private DatabaseManager dbmanager;
	private String URL;
	private EditText etName,etAddress,etBirthday,etHiredDate;	
	private Button submit;
	private TextView validity;
	
	private String name,department,address,gender,birthday,hiredDay,deptID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_employee);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		etName = (EditText) findViewById(R.id.etEMPADDName);
		etAddress = (EditText) findViewById(R.id.etEMPADDAddress);
		etBirthday = (EditText) findViewById(R.id.etEMPADDBday);
		etHiredDate = (EditText) findViewById(R.id.etEMPADDHired);
		submit = (Button) findViewById(R.id.bEMPADDSubmit);
		spinnerDepts = (Spinner) findViewById(R.id.spDepts);
		spinnerGender = (Spinner) findViewById(R.id.spEMPADDGender);
		validity = (TextView) findViewById(R.id.tvEMPADDInvalid);
		
		submit.setOnClickListener(this);		
		addItemsToSpinner();
		spinnerDepts.setOnItemSelectedListener(this);
		spinnerGender.setOnItemSelectedListener(this);
	}
	//fill the department names
	public void addItemsToSpinner() {
		URL = "http://10.0.2.2:80/dbProject/viewDepartments.php";
		dbmanager = new DatabaseManager(URL);
		List<String> list = dbmanager.getDepartments();
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDepts.setAdapter(dataAdapter);
	  }
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		department = spinnerDepts.getSelectedItem().toString();			
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		name = etName.getText().toString();
		department = spinnerDepts.getSelectedItem().toString();
		gender = spinnerGender.getSelectedItem().toString();
		address = etAddress.getText().toString();
		birthday = etBirthday.getText().toString();
		hiredDay = etHiredDate.getText().toString();
		getDeptId(department);
		
		if(!(name.equals(null)||address.equals(null)||birthday.equals(null)||hiredDay.equals(null))){
			URL = "http://10.0.2.2:80/dbProject/addEmployee.php";
			dbmanager = new DatabaseManager(URL);
			dbmanager.addEmployee(name, deptID, address, gender, birthday, hiredDay);
			validity.setText("Success!!");
			finish();
		}else{
			validity.setTextColor(Color.RED);
			validity.setText("You have not entered all the details!");
		}
	}public void getDeptId(String deptName) {
		URL = "http://10.0.2.2:80/dbProject/viewDepartments.php";
		dbmanager = new DatabaseManager(URL);
		deptID = dbmanager.getDepartmentId(deptName);
	  }

}
