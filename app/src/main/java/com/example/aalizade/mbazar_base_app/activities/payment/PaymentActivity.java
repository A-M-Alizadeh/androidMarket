package com.example.aalizade.mbazar_base_app.activities.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.orders.OrderViewActivity;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CheckoutAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.payment.RedirectModel;
import com.example.aalizade.mbazar_base_app.network.models.payment.TokenRedirectModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sbayatani on 4/18/2018.
 */

public class PaymentActivity extends AppCompatActivity {

    CheckoutAPIInterface checkoutAPI;
    String redirectURL;
    String token;
    LinearLayout progressWrapper;
    RedirectModel redirectModel;
    WebView webView;
    String currentURL;
    Toast toast;
    Boolean isResultGotton = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toast != null){
            toast.cancel();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_web_view_activity);
        redirectModel = (RedirectModel) getIntent().getSerializableExtra("redirectURL");
        Log.v("REDIRECT",redirectModel.toString());

//        HashMap<String,String> paymentSystemHashMap = new HashMap<>();
////        paymentSystemHashMap.put("1","0");
////        paymentSystemHashMap.put("2","0");
////        paymentSystemHashMap.put("3","0");
////        paymentSystemHashMap.put("25","0");
////        paymentSystemHashMap.put("35","0");
////        paymentSystemHashMap.put("85","0");
////        paymentSystemHashMap.put("86","0");
////        paymentSystemHashMap.put("89","0");
//        paymentSystemHashMap.put("28","0");
//        paymentSystemHashMap.put("136","0");
//        redirectModel = new RedirectModel("809","40980",paymentSystemHashMap,"0");
        checkoutAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(),findViewById(R.id.masterPage_mother_drawer_layout)).create(CheckoutAPIInterface.class);

        progressWrapper = (LinearLayout)findViewById(R.id.progress_wrapper_id);
        webView = (WebView) findViewById(R.id.payment_web_view);
//        webView.getSettings().
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl("http://www.mbazar.org/w/shaparak/payment?Token="+token+"&RedirectURL="+redirectURL);
//                Log.v("webView URL:",webView.getUrl());
//                currentURL = view.getUrl();
//                Log.v("webViewCurrent URL:",currentURL+" C");
                return false;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
//                view.getSettings().
                currentURL = view.getUrl();
                Log.v("webViewCurrent URL:",currentURL+" Cc");
                if(currentURL.contains("create") && !isResultGotton){
                    isResultGotton = true;
                    //TODO success payment do something
//                    Toast.makeText(getApplicationContext(),"پرداخت با موفقیت انجام شد",Toast.LENGTH_SHORT).show();
//                    toast.makeText(getApplicationContext(),"پرداخت با موفقیت انجام شد",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(PaymentActivity.this, OrderViewActivity.class);
                    intent.putExtra("info","پرداخت با موفقیت انجام شد");
                    startActivity(intent);//should start?
                }else if(currentURL.contains("error") && !isResultGotton){
                    isResultGotton = true;
                    //TODO failed payment do something
//                    Toast.makeText(getApplicationContext(),"پرداخت موفق نبود",Toast.LENGTH_SHORT).show();
//                    toast.makeText(getApplicationContext(),"پرداخت موفق نبود",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PaymentActivity.this, OrderViewActivity.class);
                    intent.putExtra("info","پرداخت موفق نبود");
                    finish();
                    startActivity(intent);//should start?

                }
            }
        });

//        webView.get

//        webView.postUrl(,"{\"vitrinId\":809,\"clientRevision\":43039,\"paymentSystemHashMap\":{\"1\":0,\"2\":0,\"3\":0,\"25\":0,\"35\":0,\"85\":0,\"86\":0,\"89\":0,\"91\":0,\"135\":0},\"paymentSystemIdForCarrier\":0}\n".getBytes());
//        "http://www.mbazar.org/w/shaparak/payment?Token="++"&RedirectURL="+;
        getToken(redirectModel);
        Log.v("webViewOrigin URL:",webView.getOriginalUrl()+" O");
        Log.v("webView URL:",webView.getUrl()+" U");
        Log.v("webViewTitle URL:",webView.getTitle()+" T");
        Log.v("webViewCurrent URL:",currentURL+" C");

    }

    public void getToken(RedirectModel redirectModel){
        Call<TokenRedirectModel> call = checkoutAPI.getTokenRedirect(redirectModel);
        call.enqueue(new CallbackWithRetry<TokenRedirectModel>(call,this,progressWrapper) {
            @Override
            public void onResponse(Call<TokenRedirectModel> call, Response<TokenRedirectModel> response) {
                Log.v("response:",response.code()+"");
//                Log.v("response error:",response.errorBody().toString()+"");
//                try {
//                    Log.v("response error:",response.errorBody().string()+"");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if(response.isSuccessful()){
                    redirectURL = response.body().getRedirectURL();
                    token = response.body().getToken();
                    Log.v("token URL:",token);
                    Log.v("redirectURL URL:",redirectURL);
                    webView.loadUrl("http://www.mbazar.net/w/shaparak/payment?Token="+token+"&RedirectURL="+redirectURL);
                    Log.v("webView URL:",webView.getUrl());
                }
            }
        });
    }
}
