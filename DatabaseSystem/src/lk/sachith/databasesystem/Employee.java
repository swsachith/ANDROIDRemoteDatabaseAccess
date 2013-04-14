package lk.sachith.databasesystem;

import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Employee extends ListActivity {

	private String actions[] = { "Add Employee", "View Employee Details","Edit Employees" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(Employee.this,
				android.R.layout.simple_list_item_1, actions));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (position == 0) {
			Intent i = new Intent("android.intent.action.EMPLOYEEADD");
			startActivity(i);
		}else if(position == 1){
			Intent a = new Intent("android.intent.action.EMPLOYEEVIEW");
			startActivity(a);
		}
		else{
			Intent k = new Intent("android.intent.action.EMPLOYEEEDIT");
			startActivity(k);
		}
	}
}