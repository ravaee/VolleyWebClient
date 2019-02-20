package ir.ravasa.testc.WebClient;
import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import ir.ravasa.testc.App.AppController;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class WebClient
{
    public interface OnDataReceivedListener
    {
        void onSuccessDataReceived(Object response);
        void onErrorDataReceived(Object response);
    }

    private OnDataReceivedListener dataListener;

    public void setDataReceivedListener(OnDataReceivedListener listener)
    {
        this.dataListener = listener;
    }

    private static Context context;

    public WebClient(Context _context)
    {
        context = _context;
        this.dataListener = null;
    }
    public void postData(String url, final Map<String, String> params, final String bearerToken)
    {
        // Success Listener
        Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    dataListener.onSuccessDataReceived(response);
                    Log.d("---------->FCM", "Response Body: " + response);
                } catch (Exception e)
                {
                    dataListener.onErrorDataReceived("UnknownError");
                    Log.d("---------->FCM", "Exception: " + e);
                }
            }
        };

        // Error Listener
        Response.ErrorListener errorListener = new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("---------->FCM", "error" + error.toString());

                try
                {
                    if(error.networkResponse == null)
                        dataListener.onErrorDataReceived("Empty");
                    else if (error.networkResponse.data != null)
                    {
                        String body = new String(error.networkResponse.data, "UTF-8");
                        dataListener.onErrorDataReceived(body);
                        Log.d("---------->FCM", "body: " + body);
                    }
                    else
                        dataListener.onErrorDataReceived("UnknownError");
                } catch (Exception e)
                {
                    dataListener.onErrorDataReceived("UnknownError");
                    Log.d("---------->FCM", "Exception Error Data 2");
                }
            }
        };

        // Create Request
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener)
        {
            @Override
            public  Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                if(bearerToken != null)
                {
                    headers.put("authorization", " bearer " + bearerToken);
                }
                return headers;
            }

            @Override
            protected Map<String, String> getParams()
            {
                Log.d("---------->", "params" + params.toString());
                return params;
            }
        };

        try
        {
            Log.d("---------->headers", String.valueOf(request.getHeaders()));
        } catch (AuthFailureError authFailureError)
        {
            Log.d("---------->headers", "Error");
        }
        Log.d("---------->", "getBodyCon: " + request.getBodyContentType());

        // timeout and add to queue
        request.setRetryPolicy(new DefaultRetryPolicy(8000, 1, 1));
        AppController.getInstance().addToRequestQueue(request);
    }

    public void getData(String url, final String bearerToken)
    {

        // Success
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d("---------->Success", response.toString());
                dataListener.onSuccessDataReceived(response);
            }
        };

        // Error
        Response.ErrorListener errorListener = new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("---------->Error1", error.toString());
                try
                {
                    if(error.networkResponse == null)
                        dataListener.onErrorDataReceived("Empty");
                    else if (error.networkResponse.data != null)
                    {
                        Log.d("---------->", "networkResponse" + error.networkResponse.toString());
                        String body = new String(error.networkResponse.data, "UTF-8");
                        dataListener.onErrorDataReceived(body);
                        Log.d("---------->", "body: " + body);
                    }
                    else
                        dataListener.onErrorDataReceived("UnknownError");
                } catch (Exception e)
                {
                    dataListener.onErrorDataReceived("UnknownError");
                    Log.d("---------->", "Exception Error in onErrorResponse");
                }

            }
        };

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener)
        {
            @Override
            public  Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                if(bearerToken != null)
                {
                    headers.put("authorization", " bearer " + bearerToken);
                }
                return headers;
            }
        };

        try
        {
            Log.d("---------->headers", String.valueOf(request.getHeaders()));
        } catch (AuthFailureError authFailureError)
        {
            Log.d("---------->headers", "Error");
        }
        Log.d("---------->", "getBodyCon: " + request.getBodyContentType());

        // timeout and add to queue
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, 1));
        AppController.getInstance().addToRequestQueue(request);
    }

    public void postJson(String url, final String bearerToken,JSONObject jsonObject){
        // Success
        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d("---------->Success", response.toString());
                dataListener.onSuccessDataReceived(response);
            }
        };

        // Error
        Response.ErrorListener errorListener = new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("---------->Error1", error.toString());
                try
                {
                    if(error.networkResponse == null)
                        dataListener.onErrorDataReceived("Empty");
                    else if (error.networkResponse.data != null)
                    {
                        Log.d("---------->", "networkResponse" + error.networkResponse.toString());
                        String body = new String(error.networkResponse.data, "UTF-8");
                        dataListener.onErrorDataReceived(body);
                        Log.d("---------->", "body: " + body);
                    }
                    else
                        dataListener.onErrorDataReceived("UnknownError");
                } catch (Exception e)
                {
                    dataListener.onErrorDataReceived("UnknownError");
                    Log.d("---------->", "Exception Error in onErrorResponse");
                }

            }
        };
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, listener, errorListener)
        {
            @Override
            public  Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                if(bearerToken != null)
                {
                    headers.put("authorization", " bearer " + bearerToken);
                }
                return headers;
            }
        };

        try
        {
            Log.d("---------->headers", String.valueOf(request.getHeaders()));
        } catch (AuthFailureError authFailureError)
        {
            Log.d("---------->headers", "Error");
        }
        Log.d("---------->", "getBodyCon: " + request.getBodyContentType());

        // timeout and add to queue
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, 1));
        AppController.getInstance().addToRequestQueue(request);
    }

}



