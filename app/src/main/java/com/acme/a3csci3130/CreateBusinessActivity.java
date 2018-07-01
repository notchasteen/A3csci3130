package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Class to create Business.
 * Modified the professor's code to do the requires tasks
 *
 * @author chasteenredondo
 */
public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private EditText numField, nameField, primaryField, addressField, provinceField;
    private MyApplicationData appState;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_activity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        numField = (EditText) findViewById(R.id.businessNum);
        nameField = (EditText) findViewById(R.id.businessName);
        primaryField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.businessAddress);
        provinceField = (EditText) findViewById(R.id.province);
    }

    /**
    * For submitting business info
    * @param v
    */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        String businessNum = numField.getText().toString();
        String businessName = nameField.getText().toString();
        String primaryBusiness = primaryField.getText().toString();
        String businessAdd = addressField.getText().toString();
        String province = provinceField.getText().toString();

        Business business = new Business(businessID,businessNum, businessName, primaryBusiness, businessAdd, province);

        appState.firebaseReference.child(businessID).setValue(business);

        finish();

    }
}
