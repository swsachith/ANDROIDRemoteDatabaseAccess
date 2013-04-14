package lk.sachith.databasesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.widget.TextView;

public class DatabaseManager extends Activity {
	String URL;
	TextView tvDisplay;
	HttpClient httpclient;
	HttpPost httppost;

	public DatabaseManager(String url) {
		URL = url;
		httpclient = new DefaultHttpClient();
		httppost = new HttpPost(URL);
	}

	public boolean checkPassword(String name, String pwd) {

		String password;
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("username", name));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return false;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				password = json_data.getString("password");
				if (password.equals(pwd)) {
					return true;
				} else
					return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addDepartment(String deptName, String deptBuilding) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("deptName", deptName));
		nameValuePairs
				.add(new BasicNameValuePair("deptBuilding", deptBuilding));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private StringBuilder inputStreamToString(InputStream is) {
		String rLine = "";
		StringBuilder answer = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		try {
			while ((rLine = rd.readLine()) != null) {
				answer.append(rLine);
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return answer;
	}

	public String viewAllDepartments() {
		String result = "Dept_id\tDept_Name\tBuilding\n";
		try {

			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result += "" + Integer.toString(json_data.getInt("dep_id"))
						+ "\t" + json_data.getString("dep_name") + "\t"
						+ json_data.getString("dep_building") + "\n";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteDepartment(String deptName) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("deptName", deptName));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			InputStream ips = response.getEntity().getContent();
			BufferedReader buf = new BufferedReader(new InputStreamReader(ips,
					"UTF-8"));
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public String viewDeptByName(String deptName) {

		String result = "";
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("deptName", deptName));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();

			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(jsonResult);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result += "" + Integer.toString(json_data.getInt("dep_id"))
						+ "\t" + json_data.getString("dep_name") + "\t"
						+ json_data.getString("dep_building") + "\t"
						+ json_data.getString("emp_name") + "\n";
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<String> getDepartments() {
		List<String> result = new ArrayList<String>();
		try {

			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result.add(json_data.getString("dep_name"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getDepartmentId(String deptName) {
		String result = "";
		try {

			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				if (json_data.getString("dep_name").equals(deptName)) {
					return json_data.getString("dep_id");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addEmployee(String name, String deptID, String address,
			String gender, String bday, String hday) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", name));
		nameValuePairs.add(new BasicNameValuePair("depID", deptID));
		nameValuePairs.add(new BasicNameValuePair("address", address));
		nameValuePairs.add(new BasicNameValuePair("gender", gender));
		nameValuePairs.add(new BasicNameValuePair("bday", bday));
		nameValuePairs.add(new BasicNameValuePair("hday", hday));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String viewAllEmployees() {
		String result = "ID\t\tname\t\tdepartment\t\n";
		try {

			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result += "" + Integer.toString(json_data.getInt("emp_id"))
						+ "\t\t" + json_data.getString("emp_name") + "\t\t"
						+ json_data.getString("dep_name") + "\t\t\n";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String viewEmployeeByName(String empName) {

		String result = "";
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("empName", empName));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();

			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(jsonResult);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result += "" + Integer.toString(json_data.getInt("emp_id"))
						+ "\t" + json_data.getString("emp_name") + "\t\t"
						+ json_data.getString("dep_name") + "\t\t" + "\n";
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteEmployee(String empID) {

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("empId", empID));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			InputStream ips = response.getEntity().getContent();
			BufferedReader buf = new BufferedReader(new InputStreamReader(ips,
					"UTF-8"));
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public List<String> getEmployees() {
		List<String> result = new ArrayList<String>();
		try {

			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result.add(json_data.getString("emp_name") + " "
						+ json_data.getInt("emp_id"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateManager(String empID, String depId) {

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("empId", empID));
		nameValuePairs.add(new BasicNameValuePair("depId", depId));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			InputStream ips = response.getEntity().getContent();
			BufferedReader buf = new BufferedReader(new InputStreamReader(ips,
					"UTF-8"));
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public boolean addProject(String name, String duration, String desc,
			String tags) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", name));
		nameValuePairs.add(new BasicNameValuePair("duration", duration));
		nameValuePairs.add(new BasicNameValuePair("description", desc));
		nameValuePairs.add(new BasicNameValuePair("tags", tags));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String viewAllProjects() {
		String result = "\n";
		try {

			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result += "" + Integer.toString(json_data.getInt("pr_id"))
						+ "\t\t" + json_data.getString("pr_name") + "\t\t"
						+ json_data.getString("pr_status") + "\t\t"
						+ json_data.getString("pr_description") + "\t\t"
						+ json_data.getString("pr_tags") + "\n\n";
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String viewProjectByName(String prName) {

		String result = "";
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("prName", prName));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();

			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(jsonResult);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result += "" + Integer.toString(json_data.getInt("pr_id"))
						+ "\t\t" + json_data.getString("pr_name") + "\t\t"
						+ json_data.getString("pr_status") + "\t\t"
						+ json_data.getString("pr_description") + "\t\t"
						+ json_data.getString("pr_tags") + "\n\n";
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String viewProjectByTag(String tag) {

		String result = "";
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("tag", tag));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();

			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(jsonResult);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result += "" + Integer.toString(json_data.getInt("pr_id"))
						+ "\t\t" + json_data.getString("pr_name") + "\t\t"
						+ json_data.getString("pr_status") + "\t\t"
						+ json_data.getString("pr_description") + "\t\t"
						+ json_data.getString("pr_tags") + "\n\n";
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteProject(String prID) {

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("prID", prID));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			InputStream ips = response.getEntity().getContent();
			BufferedReader buf = new BufferedReader(new InputStreamReader(ips,
					"UTF-8"));
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public List<String> getProjects() {
		List<String> result = new ArrayList<String>();
		try {

			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result.add(json_data.getString("pr_name") + " "
						+ json_data.getString("pr_id"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean addCollaborator(String empId, String prId) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("empID", empId));
		nameValuePairs.add(new BasicNameValuePair("prID", prId));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<String> getCollaborators(String prId) {
		List<String> result = new ArrayList<String>();
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("prID", prId));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result.add(json_data.getString("emp_name") + " "
						+ json_data.getInt("emp_id"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean removeCollaborator(String empId, String prId) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("empID", empId));
		nameValuePairs.add(new BasicNameValuePair("prID", prId));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String getSupervisor(String prId) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();		
		nameValuePairs.add(new BasicNameValuePair("prID", prId));
		String result = "";
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();
			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(tokener);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result = json_data.getString("emp_id");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}public boolean updateSupervisor(String empId, String prId) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("empID", empId));
		nameValuePairs.add(new BasicNameValuePair("prID", prId));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<String> getProjectByID(String id) {

		List<String> result = new ArrayList<String>(); 
		try {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("prID", id));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			String jsonResult = inputStreamToString(
					response.getEntity().getContent()).toString();

			if (jsonResult.equals(null))
				return null;
			JSONTokener tokener = new JSONTokener(jsonResult.toString());
			JSONArray jArray = new JSONArray(jsonResult);

			JSONObject json_data;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				result.add(json_data.getString("pr_duration"));
				result.add(json_data.getString("pr_tags"));
				result.add(json_data.getString("pr_startdate"));
				result.add(json_data.getString("pr_enddate"));
				result.add(json_data.getString("pr_description"));				
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean updateProject(String name,String pID, String duration, String desc,
			String tags,String status,String start,String end) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", name));
		nameValuePairs.add(new BasicNameValuePair("duration", duration));
		nameValuePairs.add(new BasicNameValuePair("description", desc));
		nameValuePairs.add(new BasicNameValuePair("prID", pID));
		nameValuePairs.add(new BasicNameValuePair("startdate", start));
		nameValuePairs.add(new BasicNameValuePair("enddate", end));
		nameValuePairs.add(new BasicNameValuePair("tags", tags));
		nameValuePairs.add(new BasicNameValuePair("status", status));		

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
