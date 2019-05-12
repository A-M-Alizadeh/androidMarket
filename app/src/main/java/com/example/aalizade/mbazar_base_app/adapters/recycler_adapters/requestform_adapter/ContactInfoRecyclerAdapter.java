package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.requestform_adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.RequestServices.AutoCompleteCitiesRequestService;
import com.example.aalizade.mbazar_base_app.network.apiInterface.ContactRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.contact.UserContactModelUpdate;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.models.general.DeleteModel;
import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.CutomAutoCompleteAdapter;
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

public class ContactInfoRecyclerAdapter extends RecyclerView.Adapter<ContactInfoRecyclerAdapter.NewsViewHolder> {

    public static int choice = -1;
    private Context context;
    private List<UserContactModelUpdate> contactInfos;
    AlertDialog contactInfoDialog;
    View contactInfoAlertLayout, contactInfoAlertLayoutTitle;
    AutoCompleteTextView contactCity_AutoTiet;
    TextInputLayout dialogAddressTietLayout, dialogpostalCodeTietLayout, dialogmobileNoTietLayout, dialogphoneNoTietLayout, dialogemailTietLayout;
    TextInputEditText dialogAddressTiet, dialogpostalCodeTiet, dialogmobileNoTiet, dialogphoneNoTiett, dialogemailTiet;
    Button addContactInfoBtn, canceladdContactBtn;
    CutomAutoCompleteAdapter cityAdapter;
    ProgressDialog progressDialog;
    View view;
    AutoCompleteCitiesRequestService autoCompleteCitiesRequestService;

    private static List<String> initialArray;
    private static List<AutoCompleteModel> autoCities;
    AutoCompleteModel contactCity;
    GeneralRetrofitAPIInterface generalAPIInterface;
    ContactRetrofitAPIInterface contactRetrofitAPIInterface;

    public ContactInfoRecyclerAdapter(Context context, List<UserContactModelUpdate> degrees,View view) {
        this.context = context;
        this.contactInfos = degrees;
        this.view = view;

        generalAPIInterface = RetrofitClient.getclient(view).create(GeneralRetrofitAPIInterface.class);
        contactRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(context,view).create(ContactRetrofitAPIInterface.class);
        autoCompleteCitiesRequestService = new AutoCompleteCitiesRequestService(context,view);
    }

    @Override
    public ContactInfoRecyclerAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_contactinfo_recycler_item, parent, false);
        return new ContactInfoRecyclerAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactInfoRecyclerAdapter.NewsViewHolder holder, final int position) {
        final UserContactModelUpdate contactInfo = contactInfos.get(position);
        holder.contactInfoMobileNo.setText(contactInfo.getMobileNo());
        holder.contactInfoPostalCode.setText(contactInfo.getPostalCode());
        holder.contactInfoCity.setText(contactInfo.getCity().getText() + " " + contactInfo.getCity().getElementStr());
        holder.contactInfoAddress.setText(contactInfo.getAddress());

        if (contactInfo.getDefaultContact() || (position == choice && choice > 0)) {
            contactInfo.setDefaultContact(true);//setting not default
            holder.defaultContactInfoRadio.setChecked(true);
            choice = position;
        } else {
            holder.defaultContactInfoRadio.setChecked(false);
        }

        holder.delete_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //test
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("حذف اطلاعات تماس");
                // set dialog message
                alertDialogBuilder
                        .setMessage("آیا میخواهید اطلاعات تماس را حذف کنید؟")
                        .setCancelable(false)
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                if (contactInfos.size() == 1 || contactInfo.getDefaultContact()) {
                                    Toast.makeText(context, "حذف ممکن نیست", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                } else {
                                    deleteItemRequest(contactRetrofitAPIInterface, contactInfos.get(position).getId());
//                                    removeAt(position);
                                    getContactInfos(contactRetrofitAPIInterface,progressDialog);
                                    notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
                //test
            }
        });

        //click to show items
        holder.edit_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence menuOptions[] = new CharSequence[]{"پیشفرض", "ویرایش", "حذف"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setItems(menuOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: {
                                if (contactInfos.size() > choice && choice > 0) {
                                    contactInfos.get(choice).setDefaultContact(false);
                                    contactInfos.get(position).setDefaultContact(true);
                                    choice = position;
                                } else {
                                    contactInfos.get(position).setDefaultContact(true);
                                    choice = position;
                                }
                                setdefaultContactItem(contactRetrofitAPIInterface, String.valueOf(contactInfos.get(position).getId()),progressDialog);
                                notifyDataSetChanged();
                                break;
                            }
                            case 1: {
                                contactInfoDialog.show();
                                contactCity_AutoTiet.setText(contactInfos.get(position).getCity().getText());
                                dialogAddressTiet.setText(contactInfos.get(position).getAddress());
                                dialogpostalCodeTiet.setText(contactInfos.get(position).getPostalCode());
                                dialogmobileNoTiet.setText(contactInfos.get(position).getMobileNo());
                                dialogphoneNoTiett.setText(contactInfos.get(position).getPhoneNo());
                                dialogemailTiet.setText(contactInfos.get(position).getEmailAddress());
                                contactCity_AutoTiet.requestFocus();
                                updateItem(position);
                                break;
                            }
                            case 2: {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setTitle("حذف اطلاعات تماس");
                                alertDialogBuilder
                                        .setMessage("آیا میخواهید اطلاعات تماس را حذف کنید؟")
                                        .setCancelable(false)
                                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
//                                                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                                                if (contactInfos.size() == 1 || contactInfo.getDefaultContact()) {
                                                    Toast.makeText(context, "حذف ممکن نیست", Toast.LENGTH_SHORT).show();
                                                    dialog.cancel();
                                                } else {
                                                    Toast.makeText(context,String.valueOf(contactInfos.size())+" --> "+String.valueOf(position),
                                                            Toast.LENGTH_SHORT).show();
                                                    Log.d("POS",String.valueOf(position) +" in ---> "+contactInfos.size());
                                                    deleteItemRequest(contactRetrofitAPIInterface, contactInfos.get(position).getId());
                                                    getContactInfos(contactRetrofitAPIInterface,progressDialog);
//                                                    removeAt(position);
                                                    notifyDataSetChanged();
                                                }
                                            }
                                        })
                                        .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                                break;
                            }
                        }
                    }
                });
                builder.show();
            }
        });
        //click to show items

    }

    @Override
    public int getItemCount() {
        return contactInfos.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView contactInfoMobileNo;
        private TextView contactInfoPostalCode;
        private TextView contactInfoCity;
        private TextView contactInfoAddress;
        private RadioButton defaultContactInfoRadio;
        private LinearLayout touchLayout;
        private ImageView edit_img_btn, delete_img_btn;

        public NewsViewHolder(final View itemView) {
            super(itemView);

            // dialog ??? data!!!
            progressDialog = new ProgressDialog(context);
            initialArray = new ArrayList<>();
            contactCity = new AutoCompleteModel();
            autoCities = new ArrayList<>();

            LayoutInflater inflater = LayoutInflater.from(context);
            contactInfoAlertLayout = inflater.inflate(R.layout.add_contact_info_alert_dialog_layout, null);
            contactInfoAlertLayoutTitle = inflater.inflate(R.layout.add_contact_info_dialog_title, null);
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setCustomTitle(contactInfoAlertLayoutTitle);
            alert.setView(contactInfoAlertLayout);
            alert.setCancelable(false);
            contactInfoDialog = alert.create();

            addContactInfoBtn = (Button) contactInfoAlertLayout.findViewById(R.id.add_contactInfo_Btn);
            canceladdContactBtn = (Button) contactInfoAlertLayout.findViewById(R.id.cancel_contactInfAdd_Btn);
            dialogAddressTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_address_tiet_layout_id);
            dialogpostalCodeTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_postalCode_tiet_layout_id);
            dialogmobileNoTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_mobileNo_tiet_layout_id);
            dialogphoneNoTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_phoneNo_tiet_layout_id);
            dialogemailTietLayout = (TextInputLayout) contactInfoAlertLayout.findViewById(R.id.contactInfo_dialog_email_tiet_layout_id);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/B_Yekan.ttf");
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
            // dialog ??? data!!!


            contactInfoMobileNo = (TextView) itemView.findViewById(R.id.mycontactInfo_mobileNum_txt_id);
            contactInfoPostalCode = (TextView) itemView.findViewById(R.id.mycontactInfo_postalCode_txt_id);
            contactInfoCity = (TextView) itemView.findViewById(R.id.mycontactInfo_city_txt_id);
            contactInfoAddress = (TextView) itemView.findViewById(R.id.mycontactInfo_address_txt_id);
            touchLayout = (LinearLayout) itemView.findViewById(R.id.delete_touchLayout_id);
            defaultContactInfoRadio = (RadioButton) itemView.findViewById(R.id.default_contactInfo_radioBtn_id);
            edit_img_btn = (ImageView) itemView.findViewById(R.id.edit_btn_id);
            delete_img_btn = (ImageView) itemView.findViewById(R.id.delete_btn_id);

            defaultContactInfoRadio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (contactInfos.size() > choice && choice != -1) {
                        contactInfos.get(choice).setDefaultContact(false);
                        contactInfos.get(getLayoutPosition()).setDefaultContact(true);
                        choice = getLayoutPosition();
                    } else {
                        contactInfos.get(getLayoutPosition()).setDefaultContact(true);
                        choice = getLayoutPosition();
                    }
//                    Log.d("default---->", String.valueOf(getLayoutPosition()));
//                    for (int i = 0; i < contactInfos.size(); i++) {
//                        Log.d("default---->", String.valueOf(i));
//                        Log.d("default---->", String.valueOf(contactInfos.get(i).getId()) + " " + contactInfos.get(i).getCity().getText());
//                    }
//                    Log.d("default---->", String.valueOf(contactInfos.get(getLayoutPosition()).getId()));
                    setdefaultContactItem(contactRetrofitAPIInterface, String.valueOf(contactInfos.get(getLayoutPosition()).getId()),progressDialog);
                    notifyDataSetChanged();
//                    finishPreogressDialog(progressDialog);
//                    Toast.makeText(context,String.valueOf(getAdapterPosition())+" "+String.valueOf(choice),Toast.LENGTH_SHORT).show();
                }
            });

            //auto complete city
            cityAdapter = new CutomAutoCompleteAdapter(context, R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialArray);
            contactCity_AutoTiet.setAdapter(cityAdapter);
            contactCity_AutoTiet.setDropDownHeight(500);
            contactCity_AutoTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    contactCity_AutoTiet.showDropDown();
                }
            });
            contactCity_AutoTiet.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                shenasnameTown_ET.showDropDown();
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

        }
    }

    public void update(ArrayList<UserContactModelUpdate> data) {
//        degrees.clear();
        contactInfos = data;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        contactInfos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, contactInfos.size());
    }

    public void updateItem(final int position) {
        //dialog ???
        addContactInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formIsValid()) {
                    UserContactModelUpdate newContactInfoItem = new UserContactModelUpdate();
                    if (contactInfos.get(position).getDefaultContact()) {
                        newContactInfoItem.setDefaultContact(true);
                    } else {
                        newContactInfoItem.setDefaultContact(false);
                    }
                    newContactInfoItem.setCity(contactInfos.get(position).getCity());
                    newContactInfoItem.setAddress(dialogAddressTiet.getText().toString());
                    newContactInfoItem.setPostalCode(dialogpostalCodeTiet.getText().toString());
                    newContactInfoItem.setMobileNo(dialogmobileNoTiet.getText().toString());
                    newContactInfoItem.setEmailAddress(dialogphoneNoTiett.getText().toString());
                    newContactInfoItem.setEmailAddress(dialogemailTiet.getText().toString());
                    newContactInfoItem.setId(contactInfos.get(position).getId());
                    removeAt(position);
                    contactInfos.add(newContactInfoItem);
                    notifyDataSetChanged();
                    contactCity_AutoTiet.setText("");
                    dialogAddressTiet.setText("");
                    dialogpostalCodeTiet.setText("");
                    dialogmobileNoTiet.setText("");
                    dialogphoneNoTiett.setText("");
                    dialogemailTiet.setText("");
                    contactCity_AutoTiet.requestFocus();
                    updateItemRequest(contactRetrofitAPIInterface, newContactInfoItem);
                    contactInfoDialog.dismiss();
                    Toast.makeText(context, "ویرایش شد" + String.valueOf(position), Toast.LENGTH_SHORT).show();
                } else {
                    if (contactCity_AutoTiet.getText().toString().trim().matches(""))
                        contactCity_AutoTiet.setError("این فیلد باید پر شود");
                    if (dialogmobileNoTiet.getText().toString().trim().matches(""))
                        dialogmobileNoTiet.setError("این فیلد باید پر شود");
                    if (!dialogpostalCodeTiet.getText().toString().trim().matches("") && dialogpostalCodeTiet.getText().toString().trim().length() != 10)
                        dialogpostalCodeTiet.setError("این فیلد باید دقیقا 10 رقم باشد");
                    if (!dialogphoneNoTiett.getText().toString().trim().matches("") && dialogphoneNoTiett.getText().toString().trim().length() != 8)
                        dialogphoneNoTiett.setError("این فیلد باید دقیقا 8 رقم باشد");
                    if (!mobilenumisValid(dialogmobileNoTiet.getText().toString().trim())) {
                        dialogmobileNoTiet.setError("شماره موبایل نامعتبر است");
                    }
                }
            }
        });
        canceladdContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "لفو شد", Toast.LENGTH_SHORT).show();
                contactCity_AutoTiet.setText("");
                dialogAddressTiet.setText("");
                dialogpostalCodeTiet.setText("");
                dialogmobileNoTiet.setText("");
                dialogphoneNoTiett.setText("");
                dialogemailTiet.setText("");
                contactCity_AutoTiet.requestFocus();
                contactInfoDialog.dismiss();
            }
        });

        //dialog ???
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

    public Boolean mobilenumisValid(String s) {
        Matcher mobileMatcher;
        String mobileExpression = "09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}";
        Pattern mobilePattern = Pattern.compile(mobileExpression, Pattern.CASE_INSENSITIVE);
        mobileMatcher = mobilePattern.matcher(s);
        if (mobileMatcher.find())
            return true;
        return false;
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

        if (!mobilenumisValid(dialogmobileNoTiet.getText().toString().trim())) {
            dialogmobileNoTiet.setError("شماره موبایل نامعتبر است");
            result = false;
        }
        return result;
    }

    public void setdefaultContactItem(ContactRetrofitAPIInterface contactRetrofitAPIInterface, String id,final ProgressDialog progressDialog) {
//        progressDialog = new ProgressDialog(context);
        HashMap<String, String> postParam = new HashMap<String, String>();
        postParam.put("id", id);
//        Log.d("default---->", id);
        Call<PrimitiveResponse> call = contactRetrofitAPIInterface.setdefaultContactItem(postParam);
        initPreogressDialog(progressDialog);
        call.enqueue(new Callback<PrimitiveResponse>() {
            @Override
            public void onResponse(Call<PrimitiveResponse> call, Response<PrimitiveResponse> response) {
                finishPreogressDialog(progressDialog);
                if (response.isSuccessful()) {
                    System.out.println(response.body().toString());
                    Toast.makeText(context, "به عنوان پیشفرض ثبت شد", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Log.d("default---->", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "لیست به روز رسانی نشد !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PrimitiveResponse> call, Throwable t) {
//                Toast.makeText(view.getContext(), "Complete Failure", Toast.LENGTH_SHORT).show();
                finishPreogressDialog(progressDialog);
                t.printStackTrace();
            }
        });
    }

    public void updateItemRequest(ContactRetrofitAPIInterface contactRetrofitAPIInterface, UserContactModelUpdate userContactModelUpdate) {
        Call<ResponseBody> call = contactRetrofitAPIInterface.updateContactListItem(userContactModelUpdate);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        System.out.println(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "آیتم به روز رسانی شد", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Log.d("default---->", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "آیتم به روز رسانی نشد", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(view.getContext(), "Complete Failure", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void deleteItemRequest(final ContactRetrofitAPIInterface contactRetrofitAPIInterface, int id) {
        DeleteModel deleteModel = new DeleteModel();
        List<Integer> idList = new ArrayList<>();
        idList.add(id);
        deleteModel.setIdList(idList);
        Call<List<UserContactModelUpdate>> call = contactRetrofitAPIInterface.deleteContactListItem(deleteModel);
        call.enqueue(new Callback<List<UserContactModelUpdate>>() {
            @Override
            public void onResponse(Call<List<UserContactModelUpdate>> call, Response<List<UserContactModelUpdate>> response) {
                if (response.isSuccessful()) {
                    System.out.println("what???"+response.body().toString());
                    Toast.makeText(context, "آیتم حذف شد", Toast.LENGTH_SHORT).show();
//                    contactInfos.clear();
//                    contactInfos = response.body();
//                    notifyDataSetChanged();
                    getContactInfos(contactRetrofitAPIInterface,progressDialog);
                } else {
                    try {
                        Log.d("delete---->", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "آیتم حذف نشد", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UserContactModelUpdate>> call, Throwable t) {
//                Toast.makeText(view.getContext(), "Complete Failure", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void getContactInfos(ContactRetrofitAPIInterface contactRetrofitAPIInterface,final ProgressDialog progressDialog) {
        HashMap<String, String> postParam = new HashMap<String, String>();
        postParam.put("param", "pm1");
        Toast.makeText(context, "***********", Toast.LENGTH_SHORT).show();
        Call<List<UserContactModelUpdate>> call = contactRetrofitAPIInterface.getContactInfos(postParam);
        initPreogressDialog(progressDialog);
        call.enqueue(new Callback<List<UserContactModelUpdate>>() {
            @Override
            public void onResponse(Call<List<UserContactModelUpdate>> call, Response<List<UserContactModelUpdate>> response) {
                finishPreogressDialog(progressDialog);
                if (response.isSuccessful()) {
                    contactInfos.clear();
//                    Log.d("F default", response.body().toString());
//                    Log.d("F default", response.body().get(0).getId() +" "+response.body().get(0).getCity().getId());
                    contactInfos = response.body();
                    notifyDataSetChanged();
                } else {
                    try {
                        Log.d("Contact", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "اطلاعات کاربر دریافت نشد", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UserContactModelUpdate>> call, Throwable t) {
                finishPreogressDialog(progressDialog);
                Toast.makeText(context, "قطع ارتباط با سرور", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void initPreogressDialog(ProgressDialog enteryprogressDialog) {
        final ProgressDialog progressDoalog;
        progressDoalog = enteryprogressDialog;
        progressDoalog.setMessage("لطفا صبور باشید");
        progressDoalog.setTitle("در حال ارسال درخواست");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
    }

    public void finishPreogressDialog(ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }
}