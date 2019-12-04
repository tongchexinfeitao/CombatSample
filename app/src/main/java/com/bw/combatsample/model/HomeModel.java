package com.bw.combatsample.model;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bw.combatsample.App;
import com.bw.combatsample.contract.IHomeContract;
import com.bw.combatsample.model.bean.Lawyer;
import com.bw.combatsample.util.NetUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class HomeModel implements IHomeContract.IModel {

    @Override
    public void getHomeData(final IModelCallback iModelCallback) {
    }
}
