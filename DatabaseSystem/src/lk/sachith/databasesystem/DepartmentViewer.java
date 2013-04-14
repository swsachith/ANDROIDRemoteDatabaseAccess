package lk.sachith.databasesystem;

import com.example.databasesystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DepartmentViewer extends Activity implements OnClickListener {

	Button bViewByName, bViewAll;
	TextView display;
	private DatabaseManager manager;
	String URL;
	EditText etName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_departments);
		initialize();

	}

	private void initialize() {
		// TODO Auto-generated method stub
		bViewByName = (Button) findViewById(R.id.bDeptViewByName);
		bViewAll = (Button) findViewById(R.id.bDeptViewAll);
		bViewAll.setOnClickListener(this);
		bViewByName.setOnClickListener(this);
		
		etName = (EditText) findViewById(R.id.etDeptViewByName);
		display = (TextView) findViewById(R.id.tvDeptView);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.bDeptViewAll:
			URL = "http://10.0.2.2:80/dbProject/viewDepartments.php";
			manager= new DatabaseManager(URL);
			String response = manager.viewAllDepartments();
			if(!response.equals(null))
				display.setText(response);
			else
				display.setText("Something Wrong");
			break;
		case R.id.bDeptViewByName:
			String deptName = etName.getText().toString();
			URL = "http://10.0.2.2:80/dbProject/viewDeptByName.php";
			manager= new DatabaseManager(URL);
			response = manager.viewDeptByName(deptName);
			if(response.equals(null)){
				display.setText("Enter a valid department name");
			}else
				display.setText(response);
			break;
		}

	}
	

}
