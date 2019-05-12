package com.example.aalizade.mbazar_base_app.activities.user;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.requestform_adapter.ContactInfoRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.network.RequestServices.AutoCompleteCitiesRequestService;
import com.example.aalizade.mbazar_base_app.utility.CutomAutoCompleteAdapter;
import com.example.aalizade.mbazar_base_app.utility.MobileNumberValidator;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.ContactRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.contact.UserContactModel;
import com.example.aalizade.mbazar_base_app.network.models.contact.UserContactModelUpdate;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyContactInfoActivity extends AppCompatActivity {
    List<UserContactModelUpdate> contactInfosList;
    RecyclerView contactInfoRecycler;
    ContactInfoRecyclerAdapter contactInfoRecyclerAdapter;
    FloatingActionButton floatingActionButton;
    AlertDialog contactInfoDialog;
    AutoCompleteTextView contactCity_AutoTiet;
    TextInputLayout dialogAddressTietLayout, dialogpostalCodeTietLayout, dialogmobileNoTietLayout, dialogphoneNoTietLayout, dialogemailTietLayout;
    TextInputEditText dialogAddressTiet, dialogpostalCodeTiet, dialogmobileNoTiet, dialogphoneNoTiett, dialogemailTiet;
    View contactInfoAlertLayout, contactInfoAlertLayoutTitle;
    Button addContactInfoBtn, canceladdContactBtn;
    CutomAutoCompleteAdapter cityAdapter;
    private static List<String> initialArray;
    private static List<AutoCompleteModel> autoCities;
    AutoCompleteModel contactCity;
    ContactRetrofitAPIInterface contactRetrofitAPIInterface;
    GeneralRetrofitAPIInterface generalAPIInterface;

    LinearLayout progressWrapper;
    FrameLayout motherLayout;
    AutoCompleteCitiesRequestService autoCompleteCitiesRequestService;
    MobileNumberValidator mobileNumberValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contact_info);

        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        motherLayout = (FrameLayout) findViewById(R.id.mycontactinfo_mother_layout_id);

        InitialAPI_RequestService();

        contactRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), motherLayout).create(ContactRetrofitAPIInterface.class);
        generalAPIInterface = RetrofitClient.getclient(motherLayout).create(GeneralRetrofitAPIInterface.class);
        contactInfosList = new ArrayList<>();
        getContactInfos(contactRetrofitAPIInterface);

    }

    public void getContactInfos(ContactRetrofitAPIInterface contactRetrofitAPIInterface) {
        HashMap<String, String> postParam = new HashMap<String, String>();
        postParam.put("param", "pm1");
        Call<List<UserContactModelUpdate>> call = contactRetrofitAPIInterface.getContactInfos(postParam);
        ProgressBarShower.StartMyProgressBar(MyContactInfoActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<List<UserContactModelUpdate>>(call, MyContactInfoActivity.this, progressWrapper) {
            @Override
            public void onResponse(Call<List<UserContactModelUpdate>> call, Response<List<UserContactModelUpdate>> response) {
                ProgressBarShower.StopMyProgressBar(MyContactInfoActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    contactInfosList = response.body();
                    drawPageStuff();
                } else {
                    try {
                        Log.d("Contact", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "اطلاعات کاربر دریافت نشد", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void populateCities(GeneralRetrofitAPIInterface getCities, String city) {
        autoCompleteCitiesRequestService.populateCities(generalAPIInterface, city, new IResponseHandler() {
            @Override
            public void HandleAfterResponse(Object o) {
                List<AutoCompleteModel> responseModel = (List<AutoCompleteModel>) o;
                initialArray.clear();
                autoCities.clear();
                for (AutoCompleteModel temp : responseModel) {
                    System.out.println(temp.toString());
                    autoCities.add(temp);
                    cityAdapter.add(temp.getText() + " " + temp.getElementStr());
                }
                cityAdapter.notifyDataSetChanged();
            }
        });
    }


    public void drawPageStuff() {
        //fields
        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_mycontactinfo_info_fab_id);
        LayoutInflater inflater = getLayoutInflater();
        contactInfoAlertLayout = inflater.inflate(R.layout.add_contact_info_alert_dialog_layout, null);
        contactInfoAlertLayoutTitle = inflater.inflate(R.layout.add_contact_info_dialog_title, null);

        //fields
        initialArray = new ArrayList<>();
        contactCity = new AutoCompleteModel();
        autoCities = new ArrayList<>();
        //degree listview adapter
        contactInfoRecycler = (RecyclerView) findViewById(R.id.mycontactInfo_recycler);
        contactInfoRecyclerAdapter = new ContactInfoRecyclerAdapter(this, contactInfosList, progressWrapper);

        LinearLayoutManager mylayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        contactInfoRecycler.setLayoutManager(mylayoutManager);
        contactInfoRecycler.setAdapter(contactInfoRecyclerAdapter);
        contactInfoRecycler.setHasFixedSize(true);
        contactInfoRecycler.setItemViewCacheSize(20);
        contactInfoRecycler.setDrawingCacheEnabled(true);
        contactInfoRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        contactInfoRecycler.setNestedScrollingEnabled(false);
        //degree listview adapter


        //dialog
        initialContactInfoDialog();
        //dialog

        //fab and dialog
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactInfoDialog.show();
            }
        });
        //fab and dialog
    }

    public void createaContact(final ContactRetrofitAPIInterface contactRetrofitAPIInterface, UserContactModelUpdate userContactModelUpdate) {
        UserContactModel test = new UserContactModel();
        test.setCity(userContactModelUpdate.getCity());
        test.setAddress(userContactModelUpdate.getAddress());
        test.setDefaultContact(false);
        test.setEmailAddress(userContactModelUpdate.getEmailAddress());
        test.setPhoneNo(userContactModelUpdate.getPhoneNo());
        test.setPostalCode(userContactModelUpdate.getPostalCode());
        test.setMobileNo(userContactModelUpdate.getMobileNo());

        Call<ResponseBody> call = contactRetrofitAPIInterface.updateContactListafterAdd(test);
        ProgressBarShower.StartMyProgressBar(MyContactInfoActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<ResponseBody>(call, MyContactInfoActivity.this, progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressBarShower.StopMyProgressBar(MyContactInfoActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    Log.d("WOOOWWWWW", response.body().toString());
                    Log.d("WOOOWWWWW==>", response.body().toString());
                    contactInfosList.clear();
                    contactInfoRecyclerAdapter.notifyDataSetChanged();
                    getContactInfos(contactRetrofitAPIInterface);
                    Toast.makeText(getApplicationContext(), "با موفقیت به لیست اضافه شد", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Log.d("WOOOWWWWW", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "اطلاعات کاربر دریافت نشد", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Boolean formIsValid() {
        Boolean result = true;
        if (contactCity_AutoTiet.getText().toString().trim().matches("") || contactCity == null) {
            contactCity_AutoTiet.setError("این فیلد باید پر شود");
            result = false;
        }

        if (dialogmobileNoTiet.getText().toString().trim().matches("")) {
            dialogmobileNoTiet.setError("این فیلد باید پر شود");
            result = false;
        }

        if (!dialogpostalCodeTiet.getText().toString().trim().matches("") && dialogpostalCodeTiet.getText().toString().trim().length() != 10) {
            dialogpostalCodeTiet.setError("این فیلد باید دقیقا 10 رقم باشد");
            result = false;
        }

        if (!dialogphoneNoTiett.getText().toString().trim().matches("") && dialogphoneNoTiett.getText().toString().trim().length() != 8) {
            dialogphoneNoTiett.setError("این فیلد باید دقیقا 8 رقم باشد");
            result = false;
        }

        if (!mobileNumberValidator.validate(dialogmobileNoTiet.getText().toString().trim())) {
            dialogmobileNoTiet.setError("شماره موبایل نامعتبر است");
            result = false;
        }
        return result;
    }

    public void updateContactsList(ContactRetrofitAPIInterface contactRetrofitAPIInterface) {
        HashMap<String, String> postParam = new HashMap<String, String>();
        postParam.put("param", "pm1");
        Call<List<UserContactModelUpdate>> call = contactRetrofitAPIInterface.getContactInfos(postParam);
        ProgressBarShower.StartMyProgressBar(MyContactInfoActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<List<UserContactModelUpdate>>(call, MyContactInfoActivity.this, progressWrapper) {
            @Override
            public void onResponse(Call<List<UserContactModelUpdate>> call, Response<List<UserContactModelUpdate>> response) {
                ProgressBarShower.StopMyProgressBar(MyContactInfoActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    autoCities.clear();
                    contactInfosList.clear();
                    contactInfosList = response.body();
                    cityAdapter.notifyDataSetChanged();
                    contactInfoRecyclerAdapter.notifyDataSetChanged();
                    Log.d("WOOOWWWWW", response.body().toString());
                } else {
                    try {
                        Log.d("WOOOWWWWW", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "لیست به روز رسانی نشد نشد !", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void initialContactInfoDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCustomTitle(contactInfoAlertLayoutTitle);
        alert.setView(contactInfoAlertLayout);
        alert.setCancelable(false);
        contactInfoDialog = alert.create();

        //dialog ETs fonts
        addContactInfoBtn = (Button) contactInfoAlertLayout.findViewById(R.id.add_contactInfo_Btn);
        canceladdContactBtn = (Button) contactInfoAlertLayout.findViewById(R.id.cancel_contactInfAdd_Btn);

        dialogAddressTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_address_tiet_layout_id);
        dialogpostalCodeTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_postalCode_tiet_layout_id);
        dialogmobileNoTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_mobileNo_tiet_layout_id);
        dialogphoneNoTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_phoneNo_tiet_layout_id);
        dialogemailTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_email_tiet_layout_id);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        dialogAddressTietLayout.setTypeface(custom_font);
        dialogpostalCodeTietLayout.setTypeface(custom_font);
        dialogmobileNoTietLayout.setTypeface(custom_font);
        dialogphoneNoTietLayout.setTypeface(custom_font);
        dialogemailTietLayout.setTypeface(custom_font);
        ((TextView) contactInfoAlertLayoutTitle.findViewById(R.id.contact_info_add_title_txt_id)).setTypeface(custom_font);

        contactCity_AutoTiet = (AutoCompleteTextView) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_city_auto_tiet_layout_id);
        dialogAddressTiet = (TextInputEditText) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_address_tiet_id);
        dialogpostalCodeTiet = (TextInputEditText) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_postalCode_tiet_id);
        dialogmobileNoTiet = (TextInputEditText) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_mobileNo_tiet_id);
        dialogphoneNoTiett = (TextInputEditText) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_phoneNo_tiet_id);
        dialogemailTiet = (TextInputEditText) contactInfoAlertLayout.findViewById(R.id.contactInfo_email_phoneNo_tiet_id);

        //auto complete city
        cityAdapter = new CutomAutoCompleteAdapter(this, R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialArray);
        contactCity_AutoTiet.setAdapter(cityAdapter);
        contactCity_AutoTiet.setDropDownHeight(300);
        contactCity_AutoTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                contactCity_AutoTiet.showDropDown();
            }
        });
        contactCity_AutoTiet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                initialArray.clear(); //new
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2) {
                    populateCities(generalAPIInterface, s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        contactCity_AutoTiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contactCity.setId(autoCities.get(position).getId());
                contactCity.setText(autoCities.get(position).getText());
                contactCity.setElementStr(autoCities.get(position).getElementStr());
            }
        });
        //auto complete city

        addContactInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formIsValid()) {
                    UserContactModelUpdate newContactInfoItem = new UserContactModelUpdate();
                    newContactInfoItem.setDefaultContact(false);
                    newContactInfoItem.setCity(contactCity);
                    newContactInfoItem.setAddress(dialogAddressTiet.getText().toString());
                    newContactInfoItem.setPostalCode(dialogpostalCodeTiet.getText().toString());
                    newContactInfoItem.setMobileNo(dialogmobileNoTiet.getText().toString());
                    newContactInfoItem.setPhoneNo(dialogphoneNoTiett.getText().toString());
                    newContactInfoItem.setEmailAddress(dialogemailTiet.getText().toString());

                    contactInfoRecyclerAdapter.notifyDataSetChanged();
                    contactCity_AutoTiet.setText("");
                    dialogAddressTiet.setText("");
                    dialogpostalCodeTiet.setText("");
                    dialogmobileNoTiet.setText("");
                    dialogphoneNoTiett.setText("");
                    dialogemailTiet.setText("");
                    contactCity_AutoTiet.requestFocus();
                    //set default first item added
                    if (contactInfosList.size() == 1 && !contactInfosList.get(0).getDefaultContact()) {
                        contactInfosList.get(0).setDefaultContact(true);
//                        Toast.makeText(getApplicationContext(),"CHECKED :)",Toast.LENGTH_SHORT).show();
                    }
                    //set default first item added

                    //req to update list
                    Log.d("error", "Before");
                    createaContact(contactRetrofitAPIInterface, newContactInfoItem);//here
                    Log.d("error", "after");
                    contactInfoRecyclerAdapter.notifyDataSetChanged(); //here
                    contactInfoDialog.dismiss();
                    initialArray.clear(); //new
                    cityAdapter.clear();
                    autoCities.clear();
                    Toast.makeText(getBaseContext(), "ثبت شد", Toast.LENGTH_SHORT).show();
                } else {
                    if (contactCity_AutoTiet.getText().toString().trim().matches(""))
                        contactCity_AutoTiet.setError("این فیلد باید پر شود");
                    if (dialogmobileNoTiet.getText().toString().trim().matches(""))
                        dialogmobileNoTiet.setError("این فیلد باید پر شود");
                    if (!dialogpostalCodeTiet.getText().toString().trim().matches("") && dialogpostalCodeTiet.getText().toString().trim().length() != 10)
                        dialogpostalCodeTiet.setError("این فیلد باید دقیقا 10 رقم باشد");
                    if (!dialogphoneNoTiett.getText().toString().trim().matches("") && dialogphoneNoTiett.getText().toString().trim().length() != 8)
                        dialogphoneNoTiett.setError("این فیلد باید دقیقا 8 رقم باشد");
                    if (!mobileNumberValidator.validate(dialogmobileNoTiet.getText().toString().trim())) {
                        dialogmobileNoTiet.setError("شماره موبایل نامعتبر است");
                    }
                }
            }
        });
        canceladdContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactCity_AutoTiet.setText("");
                dialogAddressTiet.setText("");
                dialogpostalCodeTiet.setText("");
                dialogmobileNoTiet.setText("");
                dialogphoneNoTiett.setText("");
                dialogemailTiet.setText("");
                contactCity_AutoTiet.requestFocus();
//                contactCity = null;//oooohhhhhh
//                autoCities.clear();
                contactInfoDialog.dismiss();
            }
        });
        //dialog ETs fonts
        //dialog
    }

    public void InitialAPI_RequestService() {
        autoCompleteCitiesRequestService = new AutoCompleteCitiesRequestService(this,progressWrapper);
        mobileNumberValidator = new MobileNumberValidator();
    }


}
