
package com.example.aalizade.mbazar_base_app.activities.credit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.credit.UserCreditTransactionsDescriptionRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.entities.UserCreditTransaction;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class UserCreditTransactionDescriptionActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    RecyclerView transactionsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_credit_transaction_description);
        transactionsRecycler = (RecyclerView) findViewById(R.id.credit_transactions_recycler_id);
        SetRecyclerAdapter();
    }


    public List<UserCreditTransaction> getData() {          //دیتا استاتیک است - api داده نشده است.
        List<UserCreditTransaction> transactions = new ArrayList<>();
        UserCreditTransaction creditTransaction1 = new UserCreditTransaction("شارژ اعتبار از طریق درگاه بانکی",
                "1396/09/01", true, "3000000",
                "3000000", "1396/09/01");

        UserCreditTransaction creditTransaction2 = new UserCreditTransaction("شارژ اعتبار از طریق واریز انجام شده",
                "1396/09/02", true, "500000",
                "3500000", "");

        UserCreditTransaction creditTransaction3 = new UserCreditTransaction("کاهش اعتبار از طریق اصلاحیه",
                "1396/09/03", false, "3000000",
                "3400000", "1396/09/01");

        UserCreditTransaction creditTransaction4 = new UserCreditTransaction("کاهش اعتبار از طریق سفارش",
                "1396/09/04", false, "1100000",
                "2300000", "");

        UserCreditTransaction creditTransaction5 = new UserCreditTransaction("کاهش اعتبار از طریق عودت",
                "1396/09/05", false, "300000",
                "2000000", "1396/09/04");

        UserCreditTransaction creditTransaction6 = new UserCreditTransaction("شارژ اعتبار از طریق درگاه بانکی",
                "1396/09/06", true, "100000",
                "2100000", "1396/09/06");

        UserCreditTransaction creditTransaction7 = new UserCreditTransaction("کاهش اعتبار از طریق اصلاحیه",
                "1396/09/07", false, "100000",
                "3000000", "1396/09/07");

        transactions.add(creditTransaction1);
        transactions.add(creditTransaction2);
        transactions.add(creditTransaction3);
        transactions.add(creditTransaction4);
        transactions.add(creditTransaction5);
        transactions.add(creditTransaction6);
        transactions.add(creditTransaction7);

        return transactions;
    }

    private void SetRecyclerAdapter() {
        UserCreditTransactionsDescriptionRecyclerAdapter newsAdapter = new UserCreditTransactionsDescriptionRecyclerAdapter(this, getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        transactionsRecycler.setLayoutManager(linearLayoutManager);
        transactionsRecycler.setAdapter(newsAdapter);
        transactionsRecycler.setHasFixedSize(true);
        transactionsRecycler.setItemViewCacheSize(20);
        transactionsRecycler.setDrawingCacheEnabled(true);
        transactionsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        transactionsRecycler.setNestedScrollingEnabled(false);
    }

}


