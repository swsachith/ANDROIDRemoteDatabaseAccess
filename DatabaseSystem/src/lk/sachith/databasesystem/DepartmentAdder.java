package lk.sachith.databasesystem;

import com.example.databasesystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DepartmentAdder extends Activity implements OnClickListener{
	EditText etName, etBuilding;
	Button submit;
	String deptName,deptBuilding;
	private DatabaseManager manager;
	private final String URL = "http://10.0.2.2:80/dbProject/addDepartment.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_department);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		manager = new DatabaseManager(URL);
		etName = (EditText) findViewById(R.id.etDeptAddName);
		etBuilding = (EditText) findViewById(R.id.etDeptAddBuil);
		submit = (Button) findViewById(R.id.bDptAddSubmit);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		deptName = etName.getText().toString();
		deptBuilding = etBuilding.getText().toString();
		if(deptName != null && deptBuilding != null){
			manager.addDepartment(deptName, deptBuilding);
			finish();
		}
	}

}
