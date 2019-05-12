package com.example.aalizade.mbazar_base_app.fragments.editmemberpersonalinfo_frags;

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
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.EditMemberPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.requestform_adapter.DegreeRecyclerAdapter_ServerAdapted;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.RequestServices.AutoCompleteCitiesRequestService;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;
import com.example.aalizade.mbazar_base_app.network.models.user.UserEducationModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
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
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditMemberPersonalInfo_PersonalInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditMemberPersonalInfo_PersonalInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditMemberPersonalInfo_PersonalInfoFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //page things
    Button degree_Button, nextPageBtn;
    AlertDialog dialog;
    View addDegreeDialogView, titleView;

    //birthDateET
    EditText newDegreeField, nationalCodeET, user_name_ET, user_lastname_ET, fatherName_ET, shenasnameCode_ET, shenasnameSerial_ET, job_ET;

    AutoCompleteTextView shenasnameTown_ET, birthTown_ET, interest_Teit;
    String newDegreeLevelSTR, newDegreeFieldSTR;
    TextView usernameTv;
    private static ArrayList<UserEducationModel> degrees;
    ProgressDialog progressDialog;
    public static Boolean firstEnter = true, lowestDegree = true;
    //    User user;
    ArrayAdapter<String> marriageAdapter, genderAdapter, academicAdapter;
    CutomAutoCompleteAdapter shenasnameTownAdapter,birthTownAdapter;
    private static List<AutoCompleteModel> shenasnameCities,birthTownCities;
    private static List<String> initialArray;
    private static List<String> initialMarriageStatusList;
    private static List<String> initialGenderList;
    private static List<String> initialAcademicLevelsList;
    private static ArrayList<ArrayList<String>> genderList;
    private static ArrayList<ArrayList<String>> marriageStatusList;
    private static ArrayList<ArrayList<String>> academicLevelsList;
    Spinner marriageSpinner, genderSpinner, DegreeLevelSpinner;

    //LoggedInUserModel objects
    AutoCompleteModel shLocation;
    AutoCompleteModel bornLocation;
    CustomDate dateOfBorn;
    ComboRequestModel gender;
    ComboRequestModel maritalStatus;
    List<UserEducationModel> userUserEducationSet;
    FrameLayout motherLayout;
    //LoggedInUserModel objects

    TextView birthDatePickerClicker;
    GeneralRetrofitAPIInterface getCitiesAPIInterface;
    NationalCodeValidator nationalCodeValidator;
    AutoCompleteCitiesRequestService autoCompleteCitiesRequestService;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditMemberPersonalInfo_PersonalInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditMemberPersonalInfo_PersonalInfoFragment.
     */
    public static EditMemberPersonalInfo_PersonalInfoFragment newInstance(String param1, String param2) {
        EditMemberPersonalInfo_PersonalInfoFragment fragment = new EditMemberPersonalInfo_PersonalInfoFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_edit_member_personal_info_personal_info, container, false);
        nextPageBtn = view.findViewById(R.id.step_one_done_btn_id);

        //date picker
        birthDatePickerClicker = (TextView)view.findViewById(R.id.member_register_PI_birthDate_TV_id);
        //date picker

        shenasnameCities = new ArrayList<>();
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

        motherLayout = view.findViewById(R.id.EditMemberPersonalInfo_PersonalInfoFragment_mother_layout_id);
        nationalCodeValidator = new NationalCodeValidator();
        getCitiesAPIInterface = RetrofitClient.getclient(motherLayout).create(GeneralRetrofitAPIInterface.class);
        autoCompleteCitiesRequestService = new AutoCompleteCitiesRequestService(getContext(),motherLayout);
        MBZ_Token_Prefs.initTokenSharedPrefs(getContext());
        //getting spinner data
        getGenderMarriageEducationCombo(getCitiesAPIInterface);
        //getting spinner data

        progressDialog = new ProgressDialog(getActivity());

        //degree listview adapter
        degrees = new ArrayList<>();
        final RecyclerView degreeRecycler = (RecyclerView) view.findViewById(R.id.degree_recycler_id);

        final DegreeRecyclerAdapter_ServerAdapted degreeRecyclerAdapter = new DegreeRecyclerAdapter_ServerAdapted(view.getContext(), degrees);
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
        nationalCodeET = (EditText) view.findViewById(R.id.member_edit_PI_nationalCode_et_id);
        user_name_ET = (EditText) view.findViewById(R.id.member_edit_PI_name_et_id);
        user_lastname_ET = (EditText) view.findViewById(R.id.member_edit_PI_lastname_et_id);
        job_ET = (EditText) view.findViewById(R.id.member_edit_PI_job_ET_id);

        shenasnameTown_ET = (AutoCompleteTextView) view.findViewById(R.id.member_edit_PI_shenasnameTown_autoTV_id);
        birthTown_ET = (AutoCompleteTextView) view.findViewById(R.id.member_edit_PI_birthTown_autoTV_id);
        interest_Teit = (AutoCompleteTextView) view.findViewById(R.id.member_edit_PI_interest_et_id);

        fatherName_ET = (EditText) view.findViewById(R.id.member_edit_PI_fathername_et_id);
        shenasnameCode_ET = (EditText) view.findViewById(R.id.member_edit_PI_shenasnameCode_et_id);
        shenasnameSerial_ET = (EditText) view.findViewById(R.id.member_edit_PI_shenasnameSerial_et_id);

        usernameTv = (TextView) view.findViewById(R.id.member_edit_PI_username_txt_id);
        //dialog ETs

        //set values
        usernameTv.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getUsername());
        user_name_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getName());
        user_lastname_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getFamily());
        if (EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn() != null && EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn().equals("")){
            birthDatePickerClicker.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn().getYear()+"/"+
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn().getMonth()+"/"+
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn().getDay());
        }

        //set values

        degree_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
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
                        newDegree.setDefaultItem("false"); //boolean to string
                        newDegree.setHidden("false");
                        newDegree.setInvalid("false");
//                        newDegree.setDeleted(false);

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
                        newDegree.setDefaultItem("false");
                        newDegree.setHidden("false");
                        newDegree.setInvalid("false");

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
        genderSpinner = (Spinner) view.findViewById(R.id.member_edit_PI_gender_spinner_id);
        genderAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, initialGenderList);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        //gender spinner

        //auto complete city
        shenasnameTownAdapter  = new CutomAutoCompleteAdapter(getContext(), R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialArray);
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
                    populateCities(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        shenasnameTown_ET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shLocation.setId(shenasnameCities.get(position).getId());
                shLocation.setText(shenasnameCities.get(position).getText());
                shLocation.setElementStr(shenasnameCities.get(position).getElementStr());

                System.out.println("sure " + shLocation);
                System.out.println("sure " + shenasnameCities);
            }
        });

        //auto complete city

        //filling disabling fields ----------------------------------------
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getName().trim().equals("")){
            user_name_ET.setEnabled(false);
            user_name_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getName());
        }

        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getFamily().trim().equals("")){
            user_lastname_ET.setEnabled(false);
            user_lastname_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getFamily());
        }

        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getFatherName().trim().equals("")){
            fatherName_ET.setEnabled(false);
            fatherName_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getFatherName());
        }

        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getNationalCode().trim().equals("")){
            nationalCodeET.setEnabled(false);
            nationalCodeET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getNationalCode());
        }

        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getGender_id().trim().equals("")) {
            genderAdapter.clear();
            genderAdapter.add(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getGender_langKey());
            genderSpinner.setEnabled(false);
        }

        //filling
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getShNo().trim().equals("")) {
            shenasnameCode_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getShNo());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getShSerialNo().trim().equals("")) {
            shenasnameSerial_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getShNo());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getShLocation().getText().equals("")) {
            shenasnameTown_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getShLocation().getText()+" "+
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getShLocation().getText());
            shLocation.setId(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getBornLocation().getId());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getBornLocation().getText().equals("")) {
            birthTown_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getBornLocation().getText()+" "+
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getBornLocation().getText());
            bornLocation.setId(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getBornLocation().getId());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn().getYear().equals("")) {
            dateOfBorn.setYear(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn().getYear());
            dateOfBorn.setMonth(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn().getMonth());
            dateOfBorn.setDay(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn().getDay());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getJobTitle().trim().equals("")) {
            job_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getJobTitle());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getFavorite().trim().equals("")) {
            interest_Teit.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getFavorite());
        }
        if (EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getUserEducationSet() != null) {
            degrees.clear();
            degrees.addAll(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getUserEducationSet());
            degreeRecyclerAdapter.notifyDataSetChanged();
        }
        //filling
        //filling disabling fields ----------------------------------------

        //auto complete city
        birthTownAdapter  = new CutomAutoCompleteAdapter(getContext(), R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialArray);
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
                    populateCities2(s.toString());
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
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyFirstStep()) {
                    //test
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setUsername((usernameTv.getText().toString()));
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setName(user_name_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setFamily(user_lastname_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setFatherName(fatherName_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setNationalCode(nationalCodeET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setShNo(shenasnameCode_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setShSerialNo(shenasnameSerial_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setShLocation(shLocation);
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setBornLocation(bornLocation);
                    if (!birthDatePickerClicker.getText().toString().equals("----/--/--") && EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn() != null) {
                        String[] separatedDate = birthDatePickerClicker.getText().toString().split("/");
                        dateOfBorn.setDay(separatedDate[0]);
                        dateOfBorn.setMonth(separatedDate[1]);
                        dateOfBorn.setYear(separatedDate[2]);
                    }
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDateOfBorn(dateOfBorn);
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setGenderId(genderList.get(genderSpinner.getSelectedItemPosition()).get(0));
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setJobTitle(job_ET.getText().toString());
//                    EditMemberPersonalInfoActivity.userModelUpdateForm.setJobCompanyName(company_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setMaritalStatusId(marriageStatusList.get(marriageSpinner.getSelectedItemPosition()).get(0));
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setFavorite(interest_Teit.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setUserEducationSet(new HashSet<UserEducationModel>(degrees));
                    //test

                    ((EditMemberPersonalInfoActivity) getActivity()).FirstStepisDone();
                }
            }
        });
        //step one done
        final PersianCalendar persianCalendar = new PersianCalendar();
        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
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
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year+"/"+(monthOfYear+1)+"/"+dayOfMonth;
        birthDatePickerClicker.setText(date);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void populateCities(String city) {
        autoCompleteCitiesRequestService.populateCities(getCitiesAPIInterface, city, new IResponseHandler() {
            @Override
            public void HandleAfterResponse(Object o) {
                List<AutoCompleteModel> responseModel = (List<AutoCompleteModel>) o;
                initialArray.clear();
                shenasnameCities.clear();
                shenasnameTownAdapter.clear();
                for (AutoCompleteModel temp : responseModel) {
                    System.out.println(temp.toString());
                    shenasnameCities.add(temp);
                    shenasnameTownAdapter.add(temp.getText() + " " + temp.getElementStr());
                }
                shenasnameTownAdapter.notifyDataSetChanged();
            }
        });
    }

    public void populateCities2(String city) {
        autoCompleteCitiesRequestService.populateCities(getCitiesAPIInterface, city, new IResponseHandler() {
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



    public void getGenderMarriageEducationCombo(GeneralRetrofitAPIInterface generalRetrofitAPIInterface) {
        final ComboRequestModel gender = new ComboRequestModel("etcItem", "gender", "");
        ComboRequestModel maritalStatus = new ComboRequestModel("etcItem", "maritalStatus", "");
        ComboRequestModel usercertificateLevel = new ComboRequestModel("etcItem", "certificateLevel", "");

        Map<String,ComboRequestModel> combos = new HashMap<>();
        combos.put("gender",gender);
        combos.put("maritalStatus",maritalStatus);
        combos.put("usercertificateLevel",usercertificateLevel);

        Call<Map<String, ArrayList<ArrayList<String>>>> call = generalRetrofitAPIInterface.getCombo(combos);
        call.enqueue(new Callback<Map<String, ArrayList<ArrayList<String>>>>() {
            @Override
            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(view.getContext(), "Success Combo", Toast.LENGTH_SHORT).show();
                    Log.d("Success Combo 222", String.valueOf(response.body()));

                    genderList = response.body().get("gender");
                    marriageStatusList = response.body().get("maritalStatus");
                    academicLevelsList = response.body().get("usercertificateLevel");

//                    System.out.println("Done " + genderList);
//                    System.out.println("Done " + marriageStatusList);
//                    System.out.println("Done " + academicLevelsList);

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
//                        System.out.println("HERE " + academicLevelsList.get(i).get(1));
                    }
                    academicAdapter.notifyDataSetChanged();

                } else {
                    try {
                        Log.d("Fail Combo EditMember_f", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, ArrayList<ArrayList<String>>>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public Boolean verifyFirstStep() {
        Boolean result = true;
        if (user_name_ET.getText().toString().isEmpty()) {
            user_name_ET.setError("این فیلد را کامل کنید");
            result = false;
        }

        if (user_lastname_ET.getText().toString().isEmpty()) {
            user_lastname_ET.setError("این فیلد را کامل کنید");
            result = false;
        }


        if (fatherName_ET.getText().toString().isEmpty()) {
            fatherName_ET.setError("این فیلد را کامل کنید");
            result = false;
        }


        if (!nationalCodeValidator.validate(nationalCodeET.getText().toString())) {
            nationalCodeET.setError("کد ملی نامعتبر است");
            result = false;
        }

        return result;
    }
}
