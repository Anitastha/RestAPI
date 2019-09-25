package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restapi.Interfaces.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView result;
private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.Result);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<List<Employee>> call = api.getEmployees();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()){
                    result.setText("code: " +response.code());

                    return;
                }
                List<Employee> employeeList =  response.body();

                for(Employee employee: employeeList) {
                    String content ="";
                    content += "id: " + employee.getId() +"\n";
                    content += "employee_name: " + employee.getEmployee_name() +"\n";
                    content += "employee_salary: " + employee.getEmployee_salary() + "\n";
                    content += "employee_age: " + employee.getEmployee_age()+ "\n";
                    content += "profile_image: "+ employee.getProfile_image()+ "\n";
                    result.append(content);

                }



                }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
     result.setText(t.getMessage());
            }
        });


    }
}
