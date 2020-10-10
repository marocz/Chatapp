package com.lit.lms.services.core;

import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import org.springframework.util.ResourceUtils;
import java.util.Scanner;

/**
 * @author Iyanu Adelekan on 18/07/2016.
 */
class Keys {

    private String TEST_SECRET_KEY;
    private String TEST_PUBLIC_KEY;
    private String LIVE_SECRET_KEY;
    private String LIVE_PUBLIC_KEY;
    String KEY_IN_USE;

    /**
     * Used to initialise all necessary API keys
     *
     * @throws FileNotFoundException
     */
    void initKeys() throws FileNotFoundException {
        JSONObject keyObject;
        String fileContent = "";
       //  File file = ResourceUtils.getFile("classpath:Keys.json");
       // Scanner scanner = new Scanner(file);

//        while (scanner.hasNext()) {
//            fileContent += scanner.nextLine();
//        }

        //keyObject = new JSONObject(fileContent).getJSONObject("API_KEYS");

//        this.KEY_IN_USE = keyObject.getString("KEY_IN_USE");
//        this.TEST_SECRET_KEY = keyObject.getString("TEST_SECRET_KEY");
//        this.TEST_PUBLIC_KEY = keyObject.getString("TEST_PUBLIC_KEY");
//        this.LIVE_SECRET_KEY = keyObject.getString("LIVE_SECRET_KEY");
//        this.LIVE_PUBLIC_KEY = keyObject.getString("LIVE_PUBLIC_KEY");
        this.KEY_IN_USE = "pk_live_fb225b0ba131fec3a2fe74c9ddec83411e54d77a";
        this.TEST_SECRET_KEY = "TEST_SECRET_KEY";
        this.TEST_PUBLIC_KEY = "TEST_PUBLIC_KEY";
        this.LIVE_SECRET_KEY = "sk_live_350e18cd2c6b06bf95557e0ec41d6dfdb452fb8d";
        this.LIVE_PUBLIC_KEY = "pk_live_fb225b0ba131fec3a2fe74c9ddec83411e54d77a";

    }

    /**
     * Used to set test secret key
     *
     * @param key
     */
    protected void setTest_SECRET_KEY(String key) {
        this.TEST_SECRET_KEY = key;
    }

    /**
     * Used to get test secret key
     *
     * @return
     */
    protected String getTEST_SECRET_KEY() {
        return this.TEST_SECRET_KEY;
    }

    /**
     * Used to set test public key
     *
     * @param key
     */
    protected void setTEST_PUBLIC_KEY(String key) {
        this.TEST_PUBLIC_KEY = key;
    }

    /**
     * Used to get test public key
     *
     * @return
     */
    protected String getTEST_PUBLIC_KEY() {
        return this.TEST_PUBLIC_KEY;
    }

    /**
     * Used to set live secret key
     *
     * @param key
     */
    protected void setLIVE_SECRET_KEY(String key) {
        this.LIVE_SECRET_KEY = key;
    }

    /**
     * Used to get live secret key
     *
     * @return
     */
    protected String getLIVE_SECRET_KEY() {
        return this.LIVE_SECRET_KEY;
    }

    /**
     * Used to set live public key
     *
     * @param key
     */
    protected void setLIVE_PUBLIC_KEY(String key) {
        this.LIVE_PUBLIC_KEY = key;
    }

    /**
     * Used to get live public key
     *
     * @return
     */
    protected String getLIVE_PUBLIC_KEY() {
        return this.LIVE_PUBLIC_KEY;
    }

}
