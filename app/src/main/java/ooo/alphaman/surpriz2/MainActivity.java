package ooo.alphaman.surpriz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        tvResponse = findViewById(R.id.tvResponse);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("On Click-----------------------");

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                System.out.println("Username = " + username + " Password = " + password);
                /*LoginPojo pojo = new LoginPojo();
                pojo.setMobile(username);
                pojo.setPassword(password);*/
                JSONObject pojoJSON = new JSONObject();
                try {
                    pojoJSON.put("mobile", username);
                    pojoJSON.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    loginUser(pojoJSON);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void loginUser(JSONObject pojo) {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://35.232.92.229:8080/surprise/auth";
        System.out.println("My Url==============>"+url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, pojo, new Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Res==============>"+response.toString());
                        tvResponse.setText(response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        tvResponse.setText("Response: Error" + error);

                    }
                });
        System.out.println("Before queue");
        queue.add(jsObjRequest);
        System.out.println("AFTER QUEUE");
    }
}

/*        //Volley
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.198:9091/auth";

        JSONObject requestPojo=new JSONObject();
        requestPojo.put("mobile",pojo.getMobile());
        requestPojo.put("password",pojo.getPassword());

        StringRequest stringRequest = new StringRequest(POST, url,requestPojo, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

               System.out.println(jsonObject);

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                tvResponse.setText("That didnt work");
            }
        }) {
            //adding parameters to the request
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", pojo.getMobile());
                params.put("password", pojo.getPassword());
                return params;
            }
        };
        queue.add(stringRequest);
        }*/