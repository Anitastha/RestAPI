package com.example.restapi.Interfaces;

import com.example.restapi.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://dummy.restapiexample.com/api/v1/";

    @GET("employees")
    Call<List<Employee>> getEmployees();
}
