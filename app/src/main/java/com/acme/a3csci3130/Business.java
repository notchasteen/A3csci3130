package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */
public class Business implements Serializable {

    public String uid;
    public String businessNumber;
    public String businessName;
    public String primaryBusiness;
    public String address;
    public String province;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Argumented constructor for a Business object
     * @param uid id of the business
     * @param businessNumber Business number which should be a 9-digit number
     * @param businessName Business name, required, 2-48 characters
     * @param primaryBusiness Primary Business, required, {Fisher, Distributor, Processor, Fish Monger}
     * @param address Business address, length < 50
     * @param province Business' province or territory {AB, BC, MB, NB, NL, NS, NT, NU, ON, PE, QC, SK, YT, “ “}
     */
    public Business(String uid, String businessNumber, String businessName, String primaryBusiness, String address, String province){
        this.uid = uid;
        this.businessNumber = businessNumber;
        this.businessName = businessName;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("businessNumber", businessNumber);
        result.put("businessName", businessName);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
