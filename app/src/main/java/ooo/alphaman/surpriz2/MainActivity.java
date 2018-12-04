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
                    /*loginUser(pojoJSON);*/
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    String url = "http://35.232.92.229:8080/surprise/auth";
                    System.out.println("My Url==============>" + url);
                    JsonObjectRequest jsObjRequest = new JsonObjectRequest
                            (Request.Method.POST, url, pojoJSON, new Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    System.out.println("Res==============>" + response.toString());
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
