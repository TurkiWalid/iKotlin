package com.androidprojects.esprit.ikotlin.webservices;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidprojects.esprit.ikotlin.handlers.DataBaseHandler;
import com.androidprojects.esprit.ikotlin.models.User;
import com.androidprojects.esprit.ikotlin.utils.AppSingleton;
import com.androidprojects.esprit.ikotlin.utils.Configuration;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Odil on 19/11/2017.
 */

public class UserProfileServices {

    public static String IP;
    public static final String URL_RGISTER= "user/register";
    public static final String URL_LOGIN= "user/authentification";

    /** Constructeur privé */
    private UserProfileServices()
    {
        IP= Configuration.IP;
    }

    /** Instance unique pré-initialisée */
    private static UserProfileServices INSTANCE = new UserProfileServices();

    /** Point d'accès pour l'instance unique du singleton */
    public static UserProfileServices getInstance()
    {	return INSTANCE;
    }

    public void registerUserWebService(String id, String username, String email,Context context ,final ServerCallbacks serverCallbacks){
        Map<String, String> m = new HashMap<String, String>();
        m.put("id", id);
        m.put("username", username);
        m.put("email", email);
        final JSONObject jsonBody = new JSONObject(m);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, IP + URL_RGISTER, jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        if (!response.has("Error")){
                            //ok
                            serverCallbacks.onSuccess(response);
                        }
                        else{
                            //wrong entries
                            serverCallbacks.onWrong(response);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                            //connection problem
                            serverCallbacks.onError(error);
                    }
                });
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,//timeout
                3,//retry
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppSingleton.getInstance(context).addToRequestQueue(jsObjRequest, "Register");
    }

    public void markLoggedUserWebService(String id,Context context ,final ServerCallbacks serverCallbacks){
        final JSONObject jsonBody = new JSONObject();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, IP + URL_LOGIN+"?id="+id, jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        if (!response.has("Error")){
                            //ok
                            serverCallbacks.onSuccess(response);
                        }
                        else{
                            //wrong entries
                            serverCallbacks.onWrong(response);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //connection problem
                        serverCallbacks.onError(error);
                    }
                });
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,//timeout
                3,//retry
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppSingleton.getInstance(context).addToRequestQueue(jsObjRequest, "Register");
    }

    public void logout(Context context){
        DataBaseHandler.getInstance(context).deleteUser();
    }

    public User get_user_from_json(JSONObject json){
        //datetimeparser
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        Calendar cal = Calendar.getInstance();

        User user = new User();
        try {
            JSONObject uj=json.getJSONObject("user");
            user.setId(uj.getString("id"));
            user.setUsername(uj.getString("username"));
            user.setEmail(uj.getString("email"));
            try {
                cal.setTime(sdf.parse(uj.getString("created")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setCreated(cal);

            try {
                cal.setTime(sdf.parse(uj.getString("lastlogged")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setLast_loggued(cal);
            user.setSkill_learner(uj.getInt("skill_learner"));
            user.setSkill_challenger(uj.getInt("skill_challenger"));
            user.setSkill_coder(uj.getInt("skill_coder"));

            user.setPictureUrl(uj.getString("picture"));
            user.setConfirmed(uj.getInt("confirmed")!=0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
       return user;
    }

}
