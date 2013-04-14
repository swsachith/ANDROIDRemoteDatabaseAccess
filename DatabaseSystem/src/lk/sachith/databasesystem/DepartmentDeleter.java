package lk.sachith.databasesystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.databasesystem.R;

public class DepartmentDeleter extends Activity implements OnClickListener{
	private DatabaseManager manager;
	EditText etDeptName;
	Button bDelete;
	TextView tvResult;
	final String URL = "http://10.0.2.2:80/dbProject/deleteDepartment.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_department);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		tvResult = (TextView) findViewById(R.id.tvDeptDeleteResult);
		etDeptName = (EditText) findViewById(R.id.etDeptViewByName);
		bDelete = (Button) findViewById(R.id.bDeptDelete);
		bDelete.setOnClickListener(this);
		manager = new DatabaseManager(URL);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String deptName = etDeptName.getText().toString();
		if(!deptName.equals(null)){
			if(manager.deleteDepartment(deptName)){
				tvResult.setText("Success!!");
			}else{
				tvResult.setText("Department Does not Exist");
				
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
