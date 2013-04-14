package lk.sachith.databasesystem;

import com.example.databasesystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity implements OnClickListener{
	
	Button employee_button,projects_button,dept_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		initialize();
	}
	public void initialize(){
		employee_button = (Button) findViewById(R.id.bMenuEmployee);
		projects_button = (Button) findViewById(R.id.bMenuProjects);
		dept_button = (Button) findViewById(R.id.bMenuDepartment);
		employee_button.setOnClickListener(this);
		projects_button.setOnClickListener(this);
		dept_button.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bMenuEmployee:
			Intent a = new Intent("android.intent.action.EMPLOYEE");
			startActivity(a);
			break;
		case R.id.bMenuProjects:
			Intent k = new Intent("android.intent.action.PROJS");
			startActivity(k);
			break;
		case R.id.bMenuDepartment:
			Intent i = new Intent("android.intent.action.DEPT");
			startActivity(i);
			break;
		}
	}
	
}
