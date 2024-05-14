package com.example.sdf;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginFragment3 extends Fragment {
    EditText tvId ;
    EditText tvPwd;
    Button btnLogin;
    SharedPreferences preferences;
    FragmentManager fragmentManager;


    //volley
    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        tvId = (EditText) view.findViewById(R.id.tvId);
        tvPwd = (EditText) view.findViewById(R.id.tvPwd);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp_id= tvId.getText().toString();
                String tmp_pw= tvPwd.getText().toString();
                //String url = "http://172.121.219.237:8085/android/login_db1.jsp?id=kim&pwd=1234";
                String url = "http://192.168.56.1:8080/android/login_db1.jsp?id="+tmp_id+"&pwd="+tmp_pw;
                requestQueue = Volley.newRequestQueue(getContext());
                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getContext(), "로그인 : "+response, Toast.LENGTH_SHORT).show();
                                preferences = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("name", response);
                                editor.commit();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "error: "+error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                );
                requestQueue.add(stringRequest);

/*                    preferences = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name", tvId.getText().toString());
                    editor.commit();
                    Toast.makeText(getContext(), "로그인성공"+tvId.getText().toString(), Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        return view;
    }
}