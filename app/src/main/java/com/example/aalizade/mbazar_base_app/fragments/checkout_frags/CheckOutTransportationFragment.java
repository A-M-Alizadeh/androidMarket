package com.example.aalizade.mbazar_base_app.fragments.checkout_frags;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.checkout_steps_adapters.CheckoutTransporterTypeRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IBadgeUpdate;
import com.example.aalizade.mbazar_base_app.network.Globals;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartProductTypeModel;
import com.example.aalizade.mbazar_base_app.network.models.product.CustomCategorizeTransportingItems;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckOutTransportationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckOutTransportationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckOutTransportationFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static List<CartProductTypeModel> cartItemsList;
    static TextView quantity_title_bar_txt, totalPrice_withount_transport_cost_txt_id;
    static TextView totalTransport_price_txt, total_price_to_pay_txt;
    static CartAPIInterface cartAPIInterface;
    static LinearLayout linearLayout;
    static RecyclerView transportItemsRecycler;
    static Activity activity;
    static ProgressBar progressBar;
    static Context context;

    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public CheckOutTransportationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckOutTransportationFragment.
     */
    public static CheckOutTransportationFragment newInstance(String param1, String param2) {
        CheckOutTransportationFragment fragment = new CheckOutTransportationFragment();
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
        final View view = inflater.inflate(R.layout.fragment_check_out_transportation, container, false);
        activity = getActivity();
        context = getContext();

        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_id);
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(getContext(), linearLayout).create(CartAPIInterface.class);
        cartItemsList = new ArrayList<>();

        quantity_title_bar_txt = (TextView) view.findViewById(R.id.products_total_payment_txt_id);
        totalPrice_withount_transport_cost_txt_id = (TextView) view.findViewById(R.id.total_price_without_transport_cost_txt_id);
        totalTransport_price_txt = (TextView) view.findViewById(R.id.total_transfer_title_id);
        total_price_to_pay_txt = (TextView) view.findViewById(R.id.total_price_to_pay_txt_id);

        transportItemsRecycler = (RecyclerView) view.findViewById(R.id.checkout_transport_recycler_id);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar_id);
//        SetRecyclerAdapter(transportItemsRecycler);
        FetchData();

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

    public static void FetchData() {
        Globals.updateBasketItems(cartAPIInterface, activity, linearLayout, new IBadgeUpdate() {
            @Override
            public void doUpdate() {
                calculateData(activity);
            }
        });
    }

    public static void calculateData(Activity activity){
        Set<CustomCategorizeTransportingItems> categoriziedItems = new HashSet<>();
        cartItemsList.clear();

        Integer totalPrice_without_transfer_cost = 0;
        Integer total_transfer_cost = 0;
        Integer total_price_to_pay = 0;
        GlobalVariables.cartTotalItemsWithQuantity = 0;
        //todo uncomment cartmodel changed
//        for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//            for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                for (CartProductTypeModel item : entry2.getValue()) {
//                    System.out.println("OOPS===))) " + item);
//                    categoriziedItems.add(new CustomCategorizeTransportingItems(item.getCarrier_id(), item.getCarrier_title(), new ArrayList<CartProductTypeModel>()));
//                    cartItemsList.add(item);
//                    GlobalVariables.cartTotalItemsWithQuantity += Integer.parseInt(item.getQuantity());
////                    String s = Integer.parseInt(item.getProductType_unitPriceTaxInclude()) * Integer.parseInt(item.getQuantity());
//                    Log.d("OOPS price item", item.getProductType_unitPriceTaxInclude());
//                    totalPrice_without_transfer_cost += Integer.parseInt(item.getProductType_unitPriceTaxInclude()) * Integer.parseInt(item.getQuantity());
//
//                    if (!item.getCarrier_unitPriceTaxInclude().equals("0.0"))
//                        total_transfer_cost += Integer.parseInt(item.getCarrier_unitPriceTaxInclude());
//
//                    Log.d("OOPS price", String.valueOf(totalPrice_without_transfer_cost));
//
//                }
//            }
//        }
        total_price_to_pay = totalPrice_without_transfer_cost + total_transfer_cost;
        //title prices

        quantity_title_bar_txt.setText(activity.getResources().getString(R.string.cart_items_total_quantity_string_2, GlobalVariables.cartTotalItemsWithQuantity));
        totalPrice_withount_transport_cost_txt_id.setText(String.valueOf(totalPrice_without_transfer_cost));
        totalTransport_price_txt.setText(String.valueOf(total_transfer_cost));
        total_price_to_pay_txt.setText(String.valueOf(total_price_to_pay));
        //title prices
        for (CustomCategorizeTransportingItems c : categoriziedItems) {
            for (int i = 0; i < cartItemsList.size(); i++) {
                if (c.getCarrierId().equals(cartItemsList.get(i).getCarrier_id())) {
                    c.getCarriedItems().add(cartItemsList.get(i));
                }
            }
        }

        for (CustomCategorizeTransportingItems c : categoriziedItems) {
            System.out.println(c.getCarrierId() + "===> " + c.toString());
        }

        List<CustomCategorizeTransportingItems> categorizedList = new ArrayList<>();
        categorizedList.addAll(categoriziedItems);

        CheckoutTransporterTypeRecyclerAdapter newsAdapter = new CheckoutTransporterTypeRecyclerAdapter(activity, categorizedList,linearLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        transportItemsRecycler.setLayoutManager(linearLayoutManager);
        transportItemsRecycler.setAdapter(newsAdapter);
        transportItemsRecycler.setHasFixedSize(true);
        transportItemsRecycler.setItemViewCacheSize(20);
        transportItemsRecycler.setDrawingCacheEnabled(true);
        transportItemsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        transportItemsRecycler.setNestedScrollingEnabled(false);

        progressBar.setVisibility(View.GONE);

    }

    public static void updateCarrier(final IBadgeUpdate iBadgeUpdate) {
        Call<CartModel> call = cartAPIInterface.updateCarrierOption(GlobalVariables.LocalCart);
        call.enqueue(new CallbackWithRetry<CartModel>(call, activity, linearLayout) {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                if (response.isSuccessful()) {
                    GlobalVariables.LocalCart = response.body();
                    System.out.println("carrier stepdone " + GlobalVariables.LocalCart.getStepDone());
                    response.body().toString();
                    iBadgeUpdate.doUpdate();
                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
