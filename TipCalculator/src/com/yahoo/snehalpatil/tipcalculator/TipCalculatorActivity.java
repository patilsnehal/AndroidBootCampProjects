package com.yahoo.snehalpatil.tipcalculator;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends Activity {
	public TextView tvTipAmount;
	public EditText etBillAmt, etTipPert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
    }
    
    public void calculate10PertTip(View v) {
    	calculateTipWithPert(0.10);
	}
    
    public void calculate15PertTip(View v) {
    	calculateTipWithPert(0.15);
	}
    
    public void calculate20PertTip(View v) {
    	calculateTipWithPert(0.20);
	}
    
    public void calculateTip(View v) {
    	etTipPert = (EditText) findViewById(R.id.etTipPert);
    	Double tipPert = Double.parseDouble(etTipPert.getText().toString());
    	// Calculate tip 
    	calculateTipWithPert(tipPert/100);
	}
    
    private void calculateTipWithPert(Double tipPert){
    	// Get Bill amount
    	etBillAmt = (EditText) findViewById(R.id.etBillAmt);
    	String billAmtStr = etBillAmt.getText().toString();
		System.out.println("billAmt");
		
		if (billAmtStr.matches("")) {
		    Toast.makeText(this, "You did not enter a bill amount", Toast.LENGTH_SHORT).show();
		    return;
		}
		
		// Calculate tip
    	Double billAmt = Double.parseDouble(billAmtStr);
    	if (billAmt != 0){
    		Double totalTip = billAmt  * tipPert;
        	
        	// set the total tip amount
        	tvTipAmount = (TextView) findViewById(R.id.tvTipAmt);
        	tvTipAmount.setText(String.format( "%.2f", totalTip));
    	} 
    }
}
