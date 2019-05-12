package com.example.aalizade.mbazar_base_app.activities.gift;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift.PaymentMethodAdapter;
import com.example.aalizade.mbazar_base_app.network.RequestServices.AutoCompleteCitiesRequestService;
import com.example.aalizade.mbazar_base_app.utility.CutomAutoCompleteAdapter;
import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GiftAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UploadAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.credit.UploadFileModel;
import com.example.aalizade.mbazar_base_app.network.models.credit.UserGiftRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboResponseModel;
import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by sbayatani on 4/21/2018.
 */

public class UserGiftRequestActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    CoordinatorLayout masterLayout;
    PersianCalendar persianCalendar;
    DatePickerDialog datePickerDialog;
    Button fileDialogBtn, submit, reject;
    TextView expireGiftDate, moneyTransferDate;
    CheckBox printCheck, tempAddressCheck;
    RelativeLayout paymentInformation, addressContainer, motherLayout;
    LinearLayout tempAddressContainer, existAddressContainer;
    RadioGroup paymentTypeValueGroup;
    RadioButton byCard, byDeposite;

    //====API=============================
    GeneralRetrofitAPIInterface generalAPI;
    UploadAPIInterface uploadAPI;
    GiftAPIInterface giftAPI;
    //====================================

    //== Error Text View ====================
    TextView quantityError, amountError, transferSourceError;
    //=======================================

    LinearLayout progressWrapper;
    AutoCompleteTextView productTypeGroup, addressCity;
    CutomAutoCompleteAdapter citySelectionAdapter;
    ArrayAdapter<String> productGroupSelectionAdapter;
    PaymentMethodAdapter paymentMethodAdapter, addressAdapter;
    ArrayList<ComboResponseModel> paymentMethodList, addressList;
    Spinner addressSpn, paymentMethodSpn;
    CoordinatorLayout masterPager;
    HashMap<String, String> taxType;

    //====requestField=====================
    ArrayList<ArrayList<String>> paymentMethodType;
    AutoCompleteModel selectedProductGroup, selectedCity;
    String addressId, paymentMethodEnum, paymentMethodId, paymentTypeValue, fileKeyImage;
    CustomDate exDate, mTransferDate;
    EditText userGiftQuantity, userGiftUnitAmount, moneyTransferDescription, moneyTransferSource, tempContactAddress, paymentReference;
    Boolean userContactTemp, printed = false;
    TextView userGiftAmount, userGiftAmountTaxInclude, userGiftTaxTitle, userGiftTaxCurrent, userGiftTaxAmount;
    //====================================
    AutoCompleteCitiesRequestService autoCompleteCitiesRequestService;

    private static List<AutoCompleteModel> autoCities;
    private static List<String> initialCityArray;
    private static List<AutoCompleteModel> autoProductGroup;
    private static List<String> initialProductGroupArray;


    //===utility================================
    public static final int PERMISSION_REQUEST = 200;
    private int flag;
    //==========================================


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_user_gift_activity);
        initialFindViews();
        InitialAPI_RequestService();
        //== initial Date Picker View =======================
        persianCalendar = new PersianCalendar();
        datePickerDialog = DatePickerDialog.newInstance(
                UserGiftRequestActivity.this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        //===================================================
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                submitUserGiftRequest();
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        expireGiftDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                initialDatePicker();
            }
        });

        moneyTransferDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                initialDatePicker();
            }
        });

        paymentTypeValueGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (byCard.isChecked()) {
                    paymentTypeValue = "BYCARD";
                    Log.v("PaymentTypeValue;", "BYCARD");
                    transferSourceError.setText("چهار رقم آخر کارت");
                } else if (byDeposite.isChecked()) {
                    paymentTypeValue = "BYDEPOSITE";
                    transferSourceError.setText("شماره حساب مبدا");
                    Log.v("PaymentTypeValue;", "BYDEPOSITE");
                }
            }
        });

        selectedProductGroup = new AutoCompleteModel();
        selectedCity = new AutoCompleteModel();
        initialProductGroupArray = new ArrayList<>();
        initialCityArray = new ArrayList<>();
        autoProductGroup = new ArrayList<>();
        paymentMethodList = new ArrayList<>();
        autoCities = new ArrayList<>();
        addressList = new ArrayList<>();
        paymentMethodAdapter = new PaymentMethodAdapter(this, paymentMethodList, R.layout.simple_textview);
        addressAdapter = new PaymentMethodAdapter(this, addressList, R.layout.simple_textview);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }

        paymentMethodSpn.setAdapter(paymentMethodAdapter);
        addressSpn.setAdapter(addressAdapter);

        paymentMethodSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (paymentMethodType.get(position).get(0).equals("120")) {
                    paymentMethodId = paymentMethodType.get(position).get(0);
                    paymentMethodEnum = paymentMethodType.get(position).get(2);
                    Toast.makeText(getApplicationContext(), paymentMethodType.get(position).get(2), Toast.LENGTH_SHORT).show();
                    paymentInformation.setVisibility(View.VISIBLE);
                } else if (paymentMethodType.get(position).get(0).equals("119")) {
                    paymentMethodId = paymentMethodType.get(position).get(0);
                    paymentMethodEnum = paymentMethodType.get(position).get(2);
                    Toast.makeText(getApplicationContext(), paymentMethodType.get(position).get(2), Toast.LENGTH_SHORT).show();
                    paymentInformation.setVisibility(View.GONE);
                }
//                Log.v("PaymentMethod:" ,paymentMethodEnum);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addressSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addressId = addressList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        printCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    printed = true;
                    addressContainer.setVisibility(View.VISIBLE);
                } else {
                    printed = false;
                    addressCity.clearListSelection();
                    addressCity.setText("");
                    addressContainer.setVisibility(View.GONE);
                }

            }
        });

        tempAddressCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    userContactTemp = true;
                    tempAddressContainer.setVisibility(View.VISIBLE);
                    existAddressContainer.setVisibility(View.GONE);
                } else {
                    userContactTemp = false;
                    addressCity.clearListSelection();
                    addressCity.setText("");
                    tempAddressContainer.setVisibility(View.GONE);
                    existAddressContainer.setVisibility(View.VISIBLE);
                }
            }
        });

        fileDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(UserGiftRequestActivity.this)
                        .withRequestCode(123)
                        .withFilter(Pattern.compile(".*\\.jpg$"))
                        .withFilterDirectories(false)
                        .withHiddenFiles(false)
                        .start();
            }
        });

        productGroupSelectionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, initialProductGroupArray);
        productTypeGroup.setAdapter(productGroupSelectionAdapter);
        productTypeGroup.setDropDownHeight(300);
        productTypeGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                productTypeGroup.showDropDown();
            }
        });

        citySelectionAdapter = new CutomAutoCompleteAdapter(this, R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialCityArray);
        addressCity.setAdapter(citySelectionAdapter);
        addressCity.setDropDownHeight(300);
        addressCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressCity.showDropDown();
            }
        });

        productTypeGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2) {
                    productGroupSelectionAdapter.clear();
                    populateProductType(s.toString());
                    for (int i = 0; i < autoProductGroup.size(); i++) {
                        productGroupSelectionAdapter.add(autoProductGroup.get(i).getText() + " " + autoProductGroup.get(i).getElementStr());
                    }
                    productGroupSelectionAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        productTypeGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedProductGroup.setId(autoProductGroup.get(position).getId());
                selectedProductGroup.setText(autoProductGroup.get(position).getText());
                selectedProductGroup.setElementStr(autoProductGroup.get(position).getElementStr());
            }
        });

        addressCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2) {
                    populateCities(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addressCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("city sajjad selected "+autoCities.get(position));
                selectedCity.setId(autoCities.get(position).getId());
                selectedCity.setText(autoCities.get(position).getText());
                selectedCity.setElementStr(autoCities.get(position).getElementStr());
            }
        });

        userGiftUnitAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !Objects.equals(userGiftQuantity.getText().toString(), "")) {
                    Integer t1 = Integer.valueOf(userGiftQuantity.getText().toString());
                    Integer t2 = Integer.valueOf(userGiftUnitAmount.getText().toString());
                    Integer result = t1 * t2;
                    Integer resultIncludeTax = result * 2;
                    userGiftAmount.setText(result + "");
                    userGiftAmountTaxInclude.setText(result + "");
                    userGiftTaxTitle.setText(taxType.get("title"));
                    userGiftTaxCurrent.setText(taxType.get("currentTotalRate"));
                    userGiftTaxAmount.setText(taxType.get("currentTotalRate"));
                } else {

                }
            }
        });
        getComboList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 123:
                if (resultCode == RESULT_OK) {
                    String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                    File file = new File(filePath);
                    fileKeyImage = UUID.randomUUID().toString();
                    UploadFileModel uploadFileModel = new UploadFileModel(fileKeyImage, file.getName(), "", "1", "0");
                    Log.v("selectedFilePath:", file.getPath() + "");
                    Log.v("FilePath:", filePath + "");
                    Log.v("uploadFileModel:", uploadFileModel.toString());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    RequestBody params = RequestBody.create(MediaType.parse("multipart/form-data"), uploadFileModel.toString());
                    MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                    upload(body, params);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "" + year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
        switch (flag) {
            case 0:
                exDate = new CustomDate(year + "", monthOfYear + 1 + "", dayOfMonth + "");
                this.expireGiftDate.setText(date);
                break;
            case 1:
                mTransferDate = new CustomDate(year + "", monthOfYear + 1 + "", dayOfMonth + "");
                this.moneyTransferDate.setText(date);
                break;
        }
    }

    public void populateProductType(String productType) {
        String url = "frontPanel/general/autocomplete?entity=productTypeGroup&value=" + productType + "&parametersMode=";
        Call<List<AutoCompleteModel>> call = generalAPI.cityAutocompleteRequest(url);
        call.enqueue(new Callback<List<AutoCompleteModel>>() {
            @Override
            public void onResponse(Call<List<AutoCompleteModel>> call, Response<List<AutoCompleteModel>> response) {
                initialProductGroupArray.clear();
                if (response.isSuccessful()) {
//                    Toast.makeText(view.getContext(), "Success2", Toast.LENGTH_SHORT).show();
                    Log.d("ProductGroup", String.valueOf(response.body()));
                    autoProductGroup.clear();
                    for (AutoCompleteModel temp : response.body()) {
                        System.out.println(temp.toString());
                        autoProductGroup.add(temp);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("ProductGroup", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AutoCompleteModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void populateCities(String city) {
        autoCompleteCitiesRequestService.populateCities(generalAPI, city, new IResponseHandler() {
            @Override
            public void HandleAfterResponse(Object o) {
                List<AutoCompleteModel> responseModel = (List<AutoCompleteModel>) o;
                initialCityArray.clear();
                autoCities.clear();
                for (AutoCompleteModel temp : responseModel) {
                    System.out.println(temp.toString());
                    autoCities.add(temp);
                    citySelectionAdapter.add(temp.getText() + " " + temp.getElementStr());
                }
                citySelectionAdapter.notifyDataSetChanged();
            }
        });
    }

    public void getComboList() {
        final ComboRequestModel paymentMethod = new ComboRequestModel("etcItem", "userCreditRequestPaymentMethod", "");
        final ComboRequestModel productTypeGroup = new ComboRequestModel("productTypeGroup", "", "");
        final ComboRequestModel userContact = new ComboRequestModel("userContact", "loggedInUserAddress", "");
        Map<String, ComboRequestModel> combo = new HashMap<>();
        combo.put("paymentMethod_id", paymentMethod);
        combo.put("productTypeGroup", productTypeGroup);
        combo.put("userContact", userContact);
        Call<Map<String, ArrayList<ArrayList<String>>>> call = generalAPI.getCombo(combo);
        call.enqueue(new CallbackWithRetry<Map<String, ArrayList<ArrayList<String>>>>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
                if (response.isSuccessful()) {
                    getTaxType();
                    paymentMethodType = response.body().get("paymentMethod_id");
                    ArrayList<ArrayList<String>> addressType = response.body().get("userContact");
                    paymentMethodAdapter.notifyDataSetChanged();
                    addressAdapter.notifyDataSetChanged();
                    for (int i = 0; i < paymentMethodType.size(); i++) {
                        paymentMethodList.add(new ComboResponseModel(paymentMethodType.get(i).get(1), paymentMethodType.get(i).get(0)));
                    }
                    for (int i = 0; i < addressType.size(); i++) {
                        addressList.add(new ComboResponseModel(addressType.get(i).get(1), addressType.get(i).get(0)));
                    }
                    paymentMethodAdapter.notifyDataSetChanged();
                    addressAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void submitUserGiftRequest() {//todo api was corrupted
        UserGiftRequestModel userGiftRequestModel = null;
        String quantity = userGiftQuantity.getText().toString();
        String unitAmount = userGiftUnitAmount.getText().toString();
        String transferDescription = moneyTransferDescription.getText().toString();
        String transferSource = moneyTransferSource.getText().toString();
        String tempAddress = tempContactAddress.getText().toString();
        String payReference = paymentReference.getText().toString();

        if (Objects.equals(quantity, "") && Objects.equals(unitAmount, "") && expireGiftDate.getText() == "----/--/--") {
            Log.v("RequestResponse: ", "else====");
            quantityError.setTextColor(Color.RED);
            amountError.setTextColor(Color.RED);
            expireGiftDate.setTextColor(Color.RED);
        } else {
            userGiftRequestModel = new UserGiftRequestModel("", unitAmount, quantity, "", paymentMethodId,
                    payReference, "", paymentTypeValue, "", "", mTransferDate, "",
                    transferSource, transferDescription, fileKeyImage, selectedProductGroup, exDate, printed.toString(),
                    "", addressId, selectedCity, tempAddress);
            Log.v("userRequestModel: ", userGiftRequestModel.toString());
            Call<ResponseBody> call = giftAPI.submitUserGiftRequest(userGiftRequestModel);
            call.enqueue(new CallbackWithRetry<ResponseBody>(call, this, progressWrapper) {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.v("RequestResponse: ", response.code() + "");
                    if (response.isSuccessful()) {
                        MySnackBar.snackBarWithNoAction("درخواست با موفقیت ثبت شد", masterLayout);
                        finish();
                    } else {
                        MySnackBar.snackBarWithNoAction("خطای ارتباط با سرور", masterLayout);
                    }
                }
            });

        }
    }


    public void getTaxType() {
        HashMap<String, String> requestModel = new HashMap<>();
        requestModel.put("taxType", "USER_GIFT_TAX");
        Call<HashMap<String, String>> call = giftAPI.getUserGiftTaxType(requestModel);
        call.enqueue(new CallbackWithRetry<HashMap<String, String>>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                if (response.isSuccessful()) {
                    taxType = response.body();
                }
            }
        });

    }

    public void upload(MultipartBody.Part body, RequestBody params) {
        Call<ResponseBody> call = uploadAPI.upload(body, params);
        call.enqueue(new CallbackWithRetry<ResponseBody>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v("response:", response.code() + "");
                if (response.isSuccessful()) {
                    MySnackBar.snackBarWithNoAction("تصویر بارگذاری شد", masterPager);
                } else {
                    MySnackBar.snackBarWithNoAction("متاسفانه نشد :(", masterPager);
                }
            }
        });
    }

    public void initialDatePicker() {
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public void initialFindViews() {
        submit = (Button) findViewById(R.id.submit);
        reject = (Button) findViewById(R.id.reject);
        masterLayout = (CoordinatorLayout) findViewById(R.id.gift_request_activity_mother_layout_id);
        paymentInformation = (RelativeLayout) findViewById(R.id.payment_information_container);
        productTypeGroup = (AutoCompleteTextView) findViewById(R.id.special_product_type_group_spn);
        addressCity = (AutoCompleteTextView) findViewById(R.id.temp_address_city_auto);
        expireGiftDate = (TextView) findViewById(R.id.user_gift_request_expire_date);
        moneyTransferDate = (TextView) findViewById(R.id.money_transfer_date);
        quantityError = (TextView) findViewById(R.id.user_gift_quantity_error);
        amountError = (TextView) findViewById(R.id.user_gift_unit_amount_error);
        userGiftAmount = (TextView) findViewById(R.id.user_gift_amount);
        userGiftAmountTaxInclude = (TextView) findViewById(R.id.user_gift_amount_tax_include);
        userGiftTaxTitle = (TextView) findViewById(R.id.tax_title);
        userGiftTaxCurrent = (TextView) findViewById(R.id.tax_current_total_rate_txt);
        userGiftTaxAmount = (TextView) findViewById(R.id.tax_amount_txt);
        transferSourceError = (TextView) findViewById(R.id.money_transfer_source_error);
        addressContainer = (RelativeLayout) findViewById(R.id.address_container);
        tempAddressContainer = (LinearLayout) findViewById(R.id.temporary_address_container);
        existAddressContainer = (LinearLayout) findViewById(R.id.saved_address_container);
        byCard = (RadioButton) findViewById(R.id.by_card);
        byDeposite = (RadioButton) findViewById(R.id.by_deposite);
        paymentTypeValueGroup = (RadioGroup) findViewById(R.id.payment_type_value_radio_group);
        userGiftQuantity = (EditText) findViewById(R.id.user_gift_quantity_txt);
        userGiftUnitAmount = (EditText) findViewById(R.id.user_gift_unit_amount);
        moneyTransferDescription = (EditText) findViewById(R.id.transfer_description);
        moneyTransferSource = (EditText) findViewById(R.id.money_transfer_source);
        tempContactAddress = (EditText) findViewById(R.id.temp_address_txt);
        paymentReference = (EditText) findViewById(R.id.payment_reference);
        masterPager = (CoordinatorLayout) findViewById(R.id.gift_request_activity_mother_layout_id);
        motherLayout = (RelativeLayout) findViewById(R.id.demo_page_container_layout_id);
        fileDialogBtn = (Button) findViewById(R.id.choose_file_dialog);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        printCheck = (CheckBox) findViewById(R.id.user_gift_print);
        tempAddressCheck = (CheckBox) findViewById(R.id.temp_address_check);
        paymentMethodSpn = (Spinner) findViewById(R.id.gift_request_payment_method_spn);
        addressSpn = (Spinner) findViewById(R.id.exist_address_spn);


    }

    public void InitialAPI_RequestService() {
        uploadAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(), findViewById(R.id.masterPage_mother_drawer_layout)).create(UploadAPIInterface.class);
        generalAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(), findViewById(R.id.masterPage_mother_drawer_layout)).create(GeneralRetrofitAPIInterface.class);
        giftAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(), findViewById(R.id.masterPage_mother_drawer_layout)).create(GiftAPIInterface.class);
        autoCompleteCitiesRequestService = new AutoCompleteCitiesRequestService(this, motherLayout);
    }
}
