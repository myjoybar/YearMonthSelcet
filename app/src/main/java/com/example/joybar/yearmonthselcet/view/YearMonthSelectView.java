package com.example.joybar.yearmonthselcet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

import java.util.Calendar;

public class YearMonthSelectView extends LinearLayout {

	private ValueChangeListener mValueChangeListener;
	private NumberPicker mYearPicker;
	private NumberPicker mMonthPicker;

	private int mYear;
	private int mMomth;

	public YearMonthSelectView(Context context) {
		super(context);
		init(context);
	}

	public YearMonthSelectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public YearMonthSelectView(Context context, AttributeSet attrs,
							   int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public void setmValueChangeListener(ValueChangeListener mValueChangeListener) {
		this.mValueChangeListener = mValueChangeListener;
	}

	private void init(Context context) {
		this.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams LP_FW = new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(LP_FW);
		this.setGravity(Gravity.CENTER);

		mYearPicker = new NumberPicker(context);
		mMonthPicker = new NumberPicker(context);

		LayoutParams lpPicker = new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		lpPicker.setMargins(0, 0, convertDipOrPx(context,10), 0);

		mYearPicker.setLayoutParams(lpPicker);
		lpPicker.setMargins(convertDipOrPx(context,10),0 ,0 , 0);
		mMonthPicker.setLayoutParams(lpPicker);

		Calendar ca = Calendar.getInstance();
		mYear = ca.get(Calendar.YEAR);
		mMomth = ca.get(Calendar.MONTH) + 1;

		mYearPicker.setMaxValue(2100);
		mYearPicker.setMinValue(2000);
		mYearPicker.setValue(mYear);

		mMonthPicker.setMaxValue(12);
		mMonthPicker.setMinValue(1);
		mMonthPicker.setValue(mMomth);

		mYearPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		mMonthPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

		mYearPicker.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				mYear = newVal;
				if(null!=mValueChangeListener){
					mValueChangeListener.onValueChange(mYear,mMomth);
				}


			}
		});
		mMonthPicker.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				mMomth = newVal;
				if (oldVal == 12 && newVal == 1) {
					mYear = mYear + 1;
					mYearPicker.setValue(mYear);
				}

				if (oldVal == 1 && newVal == 12) {
					mYear = mYear - 1;
					mYearPicker.setValue(mYear);
				}
				if(null!=mValueChangeListener){
					mValueChangeListener.onValueChange(mYear,mMomth);
				}
			}
		});

		addView(mYearPicker);
		addView(mMonthPicker);

	}

	public int getmYear() {
		return mYear;
	}

	public void setmYear(int mYear) {
		this.mYear = mYear;
	}

	public int getmMomth() {
		return mMomth;
	}

	public void setmMomth(int mMomth) {
		this.mMomth = mMomth;
	}

	public int convertDipOrPx(Context context, int dip) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
	}

	public interface ValueChangeListener{

		public void onValueChange(int year,int month);
	}
}
