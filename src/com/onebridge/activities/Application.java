package com.onebridge.activities;

import com.onebridge.model.ProductCategory;
import com.onebridge.parse.ConfigHelper;
import com.parse.Parse;
import com.parse.ParseObject;

import android.content.Context;
import android.content.SharedPreferences;

//import com.onebridge.manager.ConfigHelper;
//import com.parse.Parse;

public class Application extends android.app.Application {
  // Debugging switch
  public static final boolean APPDEBUG = true;

  // Debugging tag for the application
  public static final String APPTAG = "OrderBridge";

/*  // Used to pass location from MainActivity to PostActivity
  public static final String INTENT_EXTRA_LOCATION = "location";
*/
/*  // Key for saving the search distance preference
  private static final String KEY_SEARCH_DISTANCE = "searchDistance";

  private static final float DEFAULT_SEARCH_DISTANCE = 250.0f;
*/
  private static SharedPreferences preferences;

  private static ConfigHelper configHelper;

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();

    ParseObject.registerSubclass(ProductCategory.class);
    Parse.enableLocalDatastore(this);
    Parse.initialize(this, "wJHAtrWQQL0urnnnCIdOo6JGcIJ0ZFGEPuapq9JD",
        "RS3CcjYkEEw34o9TtZUpVPV4Msnwqp4mUFz3SNc0");

    preferences = getSharedPreferences("com.orderbridge", Context.MODE_PRIVATE);

    configHelper = new ConfigHelper();
    configHelper.fetchConfigIfNeeded();
  }
  public static ConfigHelper getConfigHelper() {
    return configHelper;
  }

}
