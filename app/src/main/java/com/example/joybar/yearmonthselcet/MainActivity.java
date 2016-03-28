package com.example.joybar.yearmonthselcet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joybar.yearmonthselcet.view.YearMonthSelectView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;
    private Button   mBtn;
    private YearMonthSelectView mYearMonthSelectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) this.findViewById(R.id.tv);
        mBtn = (Button) this.findViewById(R.id.btn);
        mYearMonthSelectView = (YearMonthSelectView) this.findViewById(R.id.year_month_vew);
        mTv.setText(mYearMonthSelectView.getmYear()+"-"+mYearMonthSelectView.getmMomth());
        mYearMonthSelectView.setmValueChangeListener(new YearMonthSelectView.ValueChangeListener() {
            @Override
            public void onValueChange(int year, int month) {
                mTv.setText(year+"-"+month);
            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearMontSelectDialog(mTv);

            }
        });

    }


    private void showYearMontSelectDialog(final TextView tv) {

        final YearMonthSelectView yearMonthSelectView = new YearMonthSelectView(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择年月");
        builder.setNegativeButton("否", null);
        builder.setView(yearMonthSelectView);
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mTv.setText(yearMonthSelectView.getmYear()+"-"+yearMonthSelectView.getmMomth());

            }
        });
        builder.show();

    }
}
