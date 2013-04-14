package lk.sachith.databasesystem;

import com.example.databasesystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmployeeViewer extends Activity implements OnClickListener {

	private Button bViewByName, bViewAll;
	private TextView display;
	private DatabaseManager manager;
	private String URL;
	private EditText etName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_employee);
		initialize();

	}

	private void initialize() {
		// TODO Auto-generated method stub
		bViewByName = (Button) findViewById(R.id.bEMPViewByName);
		bViewAll = (Button) findViewById(R.id.bEMPViewAll);
		bViewAll.setOnClickListener(this);
		bViewByName.setOnClickListener(this);
		
		etName = (EditText) findViewById(R.id.etEMPViewByName);
		display = (TextView) findViewById(R.id.tvEMPView);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.bEMPViewAll:
			URL = "http://10.0.2.2:80/dbProject/viewEmployees.php";
			manager= new DatabaseManager(URL);
			String response = manager.viewAllEmployees();
			if(!response.equals(null))
				display.setText(response);
			else
				display.setText("Something's Wrong");
			break;
		case R.id.bEMPViewByName:
			String empName = etName.getText().toString();
			URL = "http://10.0.2.2:80/dbProject/viewEmployeeByName.php";
			manager= new DatabaseManager(URL);
			response = manager.viewEmployeeByName(empName);
			if(response.trim().equals(null)){
				display.setText("No Such Employee!!");
			}else
				display.setText(response);
			break;
		}

	}
	

}
