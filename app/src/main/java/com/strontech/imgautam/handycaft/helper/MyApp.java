package com.strontech.imgautam.handycaft.helper;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    // Add code to print out the key hash
    try {
      PackageInfo info = getPackageManager().getPackageInfo(
          getPackageName(),
          PackageManager.GET_SIGNATURES);
      for (Signature signature : info.signatures) {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(signature.toByteArray());
        Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
      }
    } catch (NameNotFoundException e) {

    } catch (NoSuchAlgorithmException e) {

    }
  }
}
