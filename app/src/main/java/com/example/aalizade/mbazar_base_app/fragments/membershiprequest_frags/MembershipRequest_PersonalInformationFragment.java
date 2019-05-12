package com.example.aalizade.mbazar_base_app.fragments.membershiprequest_frags;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.requestform_adapter.DegreeRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.RequestServices.AutoCompleteCitiesRequestService;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UserRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;
import com.example.aalizade.mbazar_base_app.network.models.user.FindUserModel;
import com.example.aalizade.mbazar_base_app.network.models.user.FullUserFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.user.UserEducationModel;
import com.example.aalizade.mbazar_base_app.network.models.user.UserModelUpdate;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.CutomAutoCompleteAdapter;
import com.example.aalizade.mbazar_base_app.utility.NationalCodeValidator;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MembershipRequest_PersonalInformationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MembershipRequest_PersonalInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembershipRequest_PersonalInformationFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    //page things
    Button degree_Button, stepOneDoneBtn;
    AlertDialog dialog;
    View addDegreeDialogView, titleView;

    //birthDateET
    EditText newDegreeField, nationalCodeET, user_name_ET, user_lastname_ET, fatherName_ET, shenasnameCode_ET, shenasnameSerial_ET, job_ET, company_ET;

    AutoCompleteTextView shenasnameTown_ET, birthTown_ET, interest_Teit;
    String newDegreeLevelSTR, newDegreeFieldSTR;
    TextView usernameTv;
    private static ArrayList<UserEducationModel> degrees;
    ProgressDialog progressDialog;
    public static Boolean firstEnter = true, lowestDegree = true;
    UserModelUpdate user;
    ArrayAdapter<String>  marriageAdapter, genderAdapter, academicAdapter;
    CutomAutoCompleteAdapter shenasnameTownAdapter, birthTownAdapter;
    private static List<AutoCompleteModel> shenasnameTownCities,birthTownCities;
    private static List<String> initialArray;
    private static List<String> initialMarriageStatusList;
    private static List<String> initialGenderList;
    private static List<String> initialAcademicLevelsList;
    private static ArrayList<ArrayList<String>> genderList;
    private static ArrayList<ArrayList<String>> marriageStatusList;
    private static ArrayList<ArrayList<String>> academicLevelsList;
    Spinner marriageSpinner, genderSpinner, DegreeLevelSpinner;

    TextView birthDatePickerClicker;

    //LoggedInUserModel objects
    AutoCompleteModel shLocation;
    AutoCompleteModel bornLocation;
    CustomDate dateOfBorn;
    ComboRequestModel gender;
    ComboRequestModel maritalStatus;
    List<UserEducationModel> userUserEducationSet;
    LinearLayout progressWrapper;
    //LoggedInUserModel objects
    PersianCalendar persianCalendar;
    DatePickerDialog datePickerDialog;
    AutoCompleteCitiesRequestService autoCompleteCitiesRequestService;
    NationalCodeValidator nationalCodeValidator;
    UserRetrofitAPIInterface userRetrofitAPIInterface;
    GeneralRetrofitAPIInterface generalAPIInterface;


    //page things
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MembershipRequest_PersonalInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MembershipRequest_PersonalInformationFragment.
     */
    public static MembershipRequest_PersonalInformationFragment newInstance(String param1, String param2) {
        MembershipRequest_PersonalInformationFragment fragment = new MembershipRequest_PersonalInformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_membershiprequest_personal_information, container, false);
        progressWrapper = (LinearLayout) view.findViewById(R.id.progress_wrapper_id);
        nationalCodeValidator = new NationalCodeValidator();
        //date picker
        birthDatePickerClicker = (TextView) view.findViewById(R.id.member_register_PI_birthDate_TV_id);
        //date picker

        shenasnameTownCities = new ArrayList<>();
        birthTownCities = new ArrayList<>();
        initialArray = new ArrayList<>();

        genderList = new ArrayList<>();
        marriageStatusList = new ArrayList<>();
        academicLevelsList = new ArrayList<>();

        initialGenderList = new ArrayList<>();
        initialMarriageStatusList = new ArrayList<>();
        initialAcademicLevelsList = new ArrayList<>();

        //LoggedInUserModel objects instantiate
        shLocation = new AutoCompleteModel();
        bornLocation = new AutoCompleteModel();
        dateOfBorn = new CustomDate();
        gender = new ComboRequestModel();
        maritalStatus = new ComboRequestModel();
        userUserEducationSet = new ArrayList<>();
        //LoggedInUserModel objects instantiate

        userRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(getContext(), progressWrapper).create(UserRetrofitAPIInterface.class);
        generalAPIInterface = RetrofitClient.getclient(progressWrapper).create(GeneralRetrofitAPIInterface.class);
        MBZ_Token_Prefs.initTokenSharedPrefs(getContext());
        autoCompleteCitiesRequestService = new AutoCompleteCitiesRequestService(getContext(),progressWrapper);

        //getting spinner data
        getGenderMarriageEducationCombo(generalAPIInterface);
        //getting spinner data

        progressDialog = new ProgressDialog(getActivity());
        //pre request
        if (firstEnter) {
            RegisterFindByUsername(userRetrofitAPIInterface, view);
            firstEnter = false;
        }
        //pre request

        //degree listview adapter
        degrees = new ArrayList<>();
        final RecyclerView degreeRecycler = (RecyclerView) view.findViewById(R.id.degree_recycler_id);

        final DegreeRecyclerAdapter degreeRecyclerAdapter = new DegreeRecyclerAdapter(view.getContext(), degrees);
        GridLayoutManager myGridLayoutManager = new GridLayoutManager(getContext(), 1);
        degreeRecycler.setLayoutManager(myGridLayoutManager);
        degreeRecycler.setAdapter(degreeRecyclerAdapter);
        degreeRecycler.setHasFixedSize(true);
        degreeRecycler.setItemViewCacheSize(20);
        degreeRecycler.setDrawingCacheEnabled(true);
        degreeRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        degreeRecycler.setNestedScrollingEnabled(false);
        //degree listview adapter

        //title and dialog
        titleView = inflater.inflate(R.layout.add_degree_dialog_title, container, false);
        degree_Button = view.findViewById(R.id.degree_add_dialog_btn_id);
        addDegreeDialogView = inflater.inflate(R.layout.add_degree_dialog, container, false);
        dialog = new AlertDialog.Builder(getContext()).
                setCustomTitle(titleView)
                .setView(addDegreeDialogView).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                    }
                }).create();

        //dialog ETs
        DegreeLevelSpinner = (Spinner) addDegreeDialogView.findViewById(R.id.add_degree_level_spinner_id);
        newDegreeField = (EditText) addDegreeDialogView.findViewById(R.id.add_degree_field_ET_id);
        nationalCodeET = (EditText) view.findViewById(R.id.member_register_PI_nationalCode_et_id);
//        birthDateET = (EditText) view.findViewById(R.id.member_register_PI_birthDate_autoTV_id);
        user_name_ET = (EditText) view.findViewById(R.id.member_register_PI_name_et_id);
        user_lastname_ET = (EditText) view.findViewById(R.id.member_register_PI_lastname_et_id);
        job_ET = (EditText) view.findViewById(R.id.member_register_PI_job_ET_id);
        company_ET = (EditText) view.findViewById(R.id.member_register_PI_company_ET_id);

        shenasnameTown_ET = (AutoCompleteTextView) view.findViewById(R.id.member_register_PI_shenasnameTown_autoTV_id);
        birthTown_ET = (AutoCompleteTextView) view.findViewById(R.id.member_register_PI_birthTown_autoTV_id);
        interest_Teit = (AutoCompleteTextView) view.findViewById(R.id.member_register_PI_interest_et_id);

        fatherName_ET = (EditText) view.findViewById(R.id.member_register_PI_fathername_et_id);
        shenasnameCode_ET = (EditText) view.findViewById(R.id.member_register_PI_shenasnameCode_et_id);
        shenasnameSerial_ET = (EditText) view.findViewById(R.id.member_register_PI_shenasnameSerial_et_id);

        usernameTv = (TextView) view.findViewById(R.id.member_register_PI_username_txt_id);
        stepOneDoneBtn = (Button) view.findViewById(R.id.step_one_done_btn_id);
        //dialog ETs

        degree_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
//                Toast.makeText(view.getContext(),"Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        DegreeLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (academicLevelsList.get(position).get(1).matches("بی سواد") ||
                        academicLevelsList.get(position).get(1).matches("سیکل")) {
                    lowestDegree = true;
                    newDegreeField.setEnabled(false);
                } else {
                    newDegreeField.setEnabled(true);
                    newDegreeField.requestFocus();
                    lowestDegree = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addDegreeDialogView.findViewById(R.id.add_degree_Btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDegreeLevelSTR = DegreeLevelSpinner.getSelectedItem().toString();
                newDegreeFieldSTR = newDegreeField.getText().toString();
                if (lowestDegree) {
                    if ((newDegreeLevelSTR != null && !newDegreeLevelSTR.trim().isEmpty())) {
                        UserEducationModel newDegree = new UserEducationModel();
                        newDegree.setId(null);
                        newDegree.setCertificateLevel_langKey(academicLevelsList.get(DegreeLevelSpinner.getSelectedItemPosition()).get(1));
                        newDegree.setCertificateLevel_id(academicLevelsList.get(DegreeLevelSpinner.getSelectedItemPosition()).get(0));
                        newDegree.setCertificateField("");
                        newDegree.setDefaultItem("");
                        newDegree.setHidden("");
                        newDegree.setInvalid("");

//                        newDegree.setCertificateLevelLangKey(DegreeLevelSpinner.getSelectedItem().toString());
                        degrees.add(newDegree);
                        degreeRecyclerAdapter.notifyDataSetChanged();
                        DegreeLevelSpinner.setSelection(0);
                        newDegreeField.setText("");
                        newDegreeField.requestFocus();
                        dialog.dismiss();
                    }
                } else {
                    if ((newDegreeLevelSTR != null && !newDegreeLevelSTR.trim().isEmpty()) &&
                            (newDegreeFieldSTR != null && !newDegreeFieldSTR.trim().isEmpty())) {
//                        Degree newDegree = new Degree(DegreeLevelSpinner.getSelectedItem().toString(), newDegreeField.getText().toString());
                        UserEducationModel newDegree = new UserEducationModel();
                        newDegree.setId(null);
                        newDegree.setCertificateLevel_langKey(academicLevelsList.get(DegreeLevelSpinner.getSelectedItemPosition()).get(1));
                        newDegree.setCertificateLevel_id(academicLevelsList.get(DegreeLevelSpinner.getSelectedItemPosition()).get(0));
                        newDegree.setCertificateField(newDegreeField.getText().toString());
                        newDegree.setDefaultItem("");
                        newDegree.setHidden("");
                        newDegree.setInvalid("");

                        degrees.add(newDegree);
                        degreeRecyclerAdapter.notifyDataSetChanged();
                        DegreeLevelSpinner.setSelection(0);
                        newDegreeField.setText("");
                        newDegreeField.requestFocus();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "اطلاعات را درست وارد کنید", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //title and dialog

        //academic level spinner
        academicAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, initialAcademicLevelsList);
        academicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DegreeLevelSpinner.setAdapter(academicAdapter);
        //academic level spinner

        //marriage spinner
        marriageSpinner = (Spinner) view.findViewById(R.id.marriage_status_spinner_id);
        marriageAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, initialMarriageStatusList);
        marriageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marriageSpinner.setAdapter(marriageAdapter);
        //marriage spinner

        //gender spinner
        genderSpinner = (Spinner) view.findViewById(R.id.member_register_PI_gender_spinner_id);
        genderAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, initialGenderList);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        //gender spinner

        //auto complete city
        shenasnameTownAdapter = new CutomAutoCompleteAdapter(getContext(), R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialArray);

        shenasnameTown_ET.setAdapter(shenasnameTownAdapter);
        shenasnameTown_ET.setDropDownHeight(300);
        shenasnameTown_ET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                shenasnameTown_ET.showDropDown();
            }
        });
        shenasnameTown_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                shenasnameTown_ET.showDropDown();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2) {
                    populateCities2(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        shenasnameTown_ET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shLocation.setId(shenasnameTownCities.get(position).getId());
                shLocation.setText(shenasnameTownCities.get(position).getText());
                shLocation.setElementStr(shenasnameTownCities.get(position).getElementStr());

                System.out.println("sure " + shLocation);
                System.out.println("sure " + shenasnameTownCities);
            }
        });
        //auto complete city

        //auto complete city
        birthTownAdapter = new CutomAutoCompleteAdapter(getContext(), R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialArray);
        birthTown_ET.setAdapter(birthTownAdapter);
        birthTown_ET.setDropDownHeight(300);

        birthTown_ET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                birthTown_ET.showDropDown();
            }
        });

        birthTown_ET.addTextChangedListener(new TextWatcher() {
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

        birthTown_ET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bornLocation.setId(birthTownCities.get(position).getId());
                bornLocation.setText(birthTownCities.get(position).getText());
                bornLocation.setElementStr(birthTownCities.get(position).getElementStr());
                System.out.println("sure " + bornLocation);
                System.out.println("sure " + birthTownCities);
            }
        });
//        //auto complete city

        //empty textwatcher
        TextWatcher watchDog = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    MemberShipRequestActivity.step1 = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        user_name_ET.addTextChangedListener(watchDog);
        user_lastname_ET.addTextChangedListener(watchDog);
        fatherName_ET.addTextChangedListener(watchDog);
        nationalCodeET.addTextChangedListener(watchDog);
        //empty textwatcher

        //step one done
        stepOneDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyFirstStep()) {
                    MemberShipRequestActivity.memberCreateModel.setUser_username(usernameTv.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_name(user_name_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_family(user_lastname_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_fatherName(fatherName_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_nationalCode(nationalCodeET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_shNo(shenasnameCode_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_shSerialNo(shenasnameSerial_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_shLocation(shLocation);
                    MemberShipRequestActivity.memberCreateModel.setUser_bornLocation(bornLocation);
                    if (!birthDatePickerClicker.getText().toString().equals("----/--/--") && birthDatePickerClicker.getText().toString().length() == 10) {
                        String[] separatedDate = birthDatePickerClicker.getText().toString().split("/");
                        dateOfBorn.setDay(separatedDate[0]);
                        dateOfBorn.setMonth(separatedDate[1]);
                        dateOfBorn.setYear(separatedDate[2]);
                    }
                    MemberShipRequestActivity.memberCreateModel.setUser_dateOfBorn(dateOfBorn);
                    MemberShipRequestActivity.memberCreateModel.setUser_gender_id(genderList.get(genderSpinner.getSelectedItemPosition()).get(0));
                    MemberShipRequestActivity.memberCreateModel.setUser_jobTitle(job_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_jobCompanyName(company_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_maritalStatus_id(marriageStatusList.get(marriageSpinner.getSelectedItemPosition()).get(0));
                    MemberShipRequestActivity.memberCreateModel.setUser_favorite(interest_Teit.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_userEducationSet(new HashSet<UserEducationModel>(degrees));
                    ((MemberShipRequestActivity) getActivity()).FirstStepisDone();
                }
            }
        });
        //step one done

        Activity activity = (Activity) getContext();

        persianCalendar = new PersianCalendar();
        datePickerDialog = DatePickerDialog.newInstance(
                this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        birthDatePickerClicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        usernameTv.requestFocus();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
        birthDatePickerClicker.setText(date);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void RegisterFindByUsername(UserRetrofitAPIInterface loginAPIInterface, final View view) {
        FindUserModel findUserModel = new FindUserModel();
        findUserModel.setParam(MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCOUNT_USERNAME));
        findUserModel.setUpDiscriminator("");

        Call<FullUserFrontModel> call = loginAPIInterface.findUserInfoByUsername(findUserModel);
        initPreogressDialog(progressDialog);
        call.enqueue(new CallbackWithRetry<FullUserFrontModel>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<FullUserFrontModel> call, Response<FullUserFrontModel> response) {
                finishPreogressDialog(progressDialog);
                if (response.isSuccessful()) {
                    user = response.body().getUser();
                    usernameTv.setText(user.getUsername());
                    user_name_ET.setText(user.getName());
                    user_lastname_ET.setText(user.getFamily());
                    if (user.getDateOfBorn().getDay() != null && !user.getDateOfBorn().getDay().equals(""))
                        birthDatePickerClicker.setText(user.getDateOfBorn().getYear() + "/" + user.getDateOfBorn().getMonth() + "/" + user.getDateOfBorn().getDay());
                    verifyFirstStep();
                } else {
                    Toast.makeText(view.getContext(), "اطلاعات کاربر دریافت نشد", Toast.LENGTH_SHORT).show();
                }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void populateCities(String city) {
        autoCompleteCitiesRequestService.populateCities(generalAPIInterface, city, new IResponseHandler() {
            @Override
            public void HandleAfterResponse(Object o) {
                List<AutoCompleteModel> responseModel = (List<AutoCompleteModel>) o;
                initialArray.clear();
                birthTownCities.clear();
                birthTownAdapter.clear();
                for (AutoCompleteModel temp : responseModel) {
                    System.out.println(temp.toString());
                    birthTownCities.add(temp);
                    birthTownAdapter.add(temp.getText() + " " + temp.getElementStr());
                }
                birthTownAdapter.notifyDataSetChanged();

            }
        });
    }
    public void populateCities2(String city) {
        autoCompleteCitiesRequestService.populateCities(generalAPIInterface, city, new IResponseHandler() {
            @Override
            public void HandleAfterResponse(Object o) {
                List<AutoCompleteModel> responseModel = (List<AutoCompleteModel>) o;
                initialArray.clear();
                shenasnameTownAdapter.clear();
                shenasnameTownAdapter.clear();
                for (AutoCompleteModel temp : responseModel) {
                    System.out.println(temp.toString());
                    shenasnameTownCities.add(temp);
                    shenasnameTownAdapter.add(temp.getText() + " " + temp.getElementStr());
                }
                shenasnameTownAdapter.notifyDataSetChanged();

            }
        });
    }


    public void getGenderMarriageEducationCombo(GeneralRetrofitAPIInterface getCombo) {
        final ComboRequestModel gender = new ComboRequestModel("etcItem", "gender", "");
        ComboRequestModel maritalStatus = new ComboRequestModel("etcItem", "maritalStatus", "");
        ComboRequestModel usercertificateLevel = new ComboRequestModel("etcItem", "certificateLevel", "");

        Map<String, ComboRequestModel> combos = new HashMap<>();
        combos.put("gender", gender);
        combos.put("maritalStatus", maritalStatus);
        combos.put("usercertificateLevel", usercertificateLevel);
        Call<Map<String, ArrayList<ArrayList<String>>>> call = getCombo.getCombo(combos);
        call.enqueue(new CallbackWithRetry<Map<String, ArrayList<ArrayList<String>>>>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
                if (response.isSuccessful()) {
                    Log.d("Success Combo 222", String.valueOf(response.body()));

                    genderList = response.body().get("gender");
                    marriageStatusList = response.body().get("maritalStatus");
                    academicLevelsList = response.body().get("usercertificateLevel");

                    System.out.println("Done " + genderList);
                    System.out.println("Done " + marriageStatusList);
                    System.out.println("Done " + academicLevelsList);

                    for (int i = 0; i < genderList.size(); i++) {
                        initialGenderList.add(genderList.get(i).get(1));
                    }
                    genderAdapter.notifyDataSetChanged();
                    for (int i = 0; i < marriageStatusList.size(); i++) {
                        initialMarriageStatusList.add(marriageStatusList.get(i).get(1));
                    }
                    marriageAdapter.notifyDataSetChanged();
                    for (int i = 0; i < academicLevelsList.size(); i++) {
                        initialAcademicLevelsList.add(academicLevelsList.get(i).get(1));
                        System.out.println("HERE " + academicLevelsList.get(i).get(1));
                    }
                    academicAdapter.notifyDataSetChanged();

                } else {
                    try {
                        Log.d("Fail cus register Combo", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public Boolean verifyFirstStep() {
        Boolean result = true;
        if (user_name_ET.getText().toString().isEmpty()) {
            user_name_ET.setError("این فیلد را کامل کنید.");
            result = false;
        }

        if (user_lastname_ET.getText().toString().isEmpty()) {
            user_lastname_ET.setError("این فیلد را کامل کنید.");
            result = false;
        }

        if (fatherName_ET.getText().toString().isEmpty()) {
            fatherName_ET.setError("این فیلد را کامل کنید.");
            result = false;
        }

        if (!nationalCodeValidator.validate(nationalCodeET.getText().toString())) {
            nationalCodeET.setError("کد ملی نامعتبر است.");
            result = false;
        }

        return result;
    }


}
