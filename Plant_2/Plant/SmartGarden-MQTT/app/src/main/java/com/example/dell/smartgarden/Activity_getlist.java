package com.example.dell.smartgarden;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Activity_getlist extends AppCompatActivity {
    ListView list_data;
    String jsonResult,mainU;
    SimpleAdapter sAdap;
    JSONObject c;
    int index=0;
    ArrayList<String> List_Name =new ArrayList<String>();
    ArrayList<String> List_Description =new ArrayList<String>();
    ArrayList<String> List_ID =new ArrayList<String>();
    ArrayList<String>List_Type =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        list_data = (ListView) findViewById(R.id.list_of_plants);


     /*   if (Activity_GlobalVariable.SELECTTYPE.equals("1")){
            getSupportActionBar().setTitle("Plants");
        }else {
            getSupportActionBar().setTitle("Vegetables");
        }

*/

        accessWebService();

    }

    @SuppressLint("NewApi")
    private class JsonReadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "error..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            return answer;
        }

        @Override
        protected void onPostExecute(String result) {
            getData(); // we will create it later
        }
    }

    public void accessWebService() {
        // InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //   imm.hideSoftInputFromWindow(editTextBarcodeForpromotionChange.getWindowToken(), 0);
        Log.i("Heloo", "4");

        if (Activity_GlobalVariable.SELECTTYPE.equals("1")){
            mainU = "http://funapp-programming.com/PlantsDetail/clsSelect.php?f=tblPlantsSelectAll";

        }else {
            mainU = "http://funapp-programming.com/PlantsDetail/clsSelect.php?f=tblVegitablesSelectAll";
        }


        Log.i("promotion", mainU);


        JsonReadTask task = new JsonReadTask();
        task.execute(new String[]{mainU});
    }

    public void getData() {
        try {
            JSONObject jsonResonse = new JSONObject(jsonResult.substring(jsonResult.indexOf("{"), jsonResult.lastIndexOf("}") + 1));
            JSONArray jsonMainNode = jsonResonse.optJSONArray("member");

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();


            HashMap<String, String> map;

            for (int i = 0; i < jsonMainNode.length(); i++) {
                c = jsonMainNode.getJSONObject(i);
                map = new HashMap<String, String>();
                index++;
                map.put("intId", c.getString("intId"));
                map.put("Name", c.getString("Name"));
                map.put("Description", c.getString("Description"));
                map.put("Type", c.getString("Type"));
                map.put("index", index + " .");
                //*********************************************************************
                List_Name.add(c.getString("Name"));
                List_Description.add(c.getString("Description"));
                List_ID.add(c.getString("intId"));
                List_Type.add(c.getString("Type"));

                MyArrList.add(map);


                sAdap = new SimpleAdapter(Activity_getlist.this, MyArrList, R.layout.activity_column_data, new String[]{"index","Name"}, new int[]{R.id.tv_index, R.id.tv_name});

                list_data.setAdapter(sAdap);
            }

            list_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Activity_GlobalVariable.NAME=List_Name.get(position).toString();
                    Activity_GlobalVariable.DESCRIPTION=List_Description.get(position).toString();
                    Activity_GlobalVariable.TYPE=List_Type.get(position).toString();
                    Activity_GlobalVariable.ID=List_ID.get(position).toString();

                    Intent intent =new Intent(Activity_getlist.this,Activity_Details.class);
                    startActivity(intent);
                }
            });

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), " error ..." + e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

