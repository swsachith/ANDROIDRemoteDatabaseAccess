package lk.sachith.databasesystem;

import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Department extends ListActivity {

	private String actions[] = { "Add Department", "View All","Edit Management","Delete Department" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(Department.this,
				android.R.layout.simple_list_item_1, actions));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (position == 0) {
			Intent i = new Intent("android.intent.action.DEPTADD");
			startActivity(i);
		}else if(position == 1){
			Intent a = new Intent("android.intent.action.DEPTVIEW");
			startActivity(a);
		}else if(position == 2){
			Intent j = new Intent("android.intent.action.DEPTEDIT");
			startActivity(j);
		}
		else{
			Intent k = new Intent("android.intent.action.DEPTDELETE");
			startActivity(k);
		}
	}
}