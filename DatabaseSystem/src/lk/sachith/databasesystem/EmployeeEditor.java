package lk.sachith.databasesystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.databasesystem.R;

public class EmployeeEditor extends Activity implements OnClickListener{
	private DatabaseManager manager;
	EditText etID;
	Button bDelete;
	TextView tvResult;
	final String URL = "http://10.0.2.2:80/dbProject/deleteEmployee.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_employee);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		tvResult = (TextView) findViewById(R.id.tvEMPDeleteResult);
		etID = (EditText) findViewById(R.id.etEMPID);
		bDelete = (Button) findViewById(R.id.bEMPDelete);
		bDelete.setOnClickListener(this);
		manager = new DatabaseManager(URL);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String empID = etID.getText().toString();
		if(!empID.equals(null)){
			if(manager.deleteEmployee(empID)){
				tvResult.setText("Success!!");
			}else{
				tvResult.setText("Employee Does not Exist");
				
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
