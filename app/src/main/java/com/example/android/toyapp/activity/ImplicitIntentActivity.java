package com.example.android.toyapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.toyapp.R;

public class ImplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_webpage);
    }

    /**
     * This method is called when the Open Website button is clicked. It will open the website
     * specified by the URL represented by the variable urlAsString using implicit Intents.
     *
     * @param v Button that was clicked.
     */
    public void onClickOpenWebpageButton(View v) {
        final String url = "https://www.udacity.com";
        openWebPage(url);
        Toast.makeText(this, "Open a web page when this button is clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is called when the Open Location in Map button is clicked. It will open the
     * a map to the location represented by the variable addressString using implicit Intents.
     *
     * @param v Button that was clicked.
     */
    public void onClickOpenAddressButton(View v) {
        final String addressString = "1600 Amphitheatre Parkway, CA";
        final Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")
                .appendQueryParameter("q", addressString);
        final Uri addressUri = builder.build();
        showMap(addressUri);
        Toast.makeText(this, "Open a map when this button is clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is called when the Share Text Content button is clicked. It will simply share
     * the text contained within the String textThatYouWantToShare.
     *
     * @param v Button that was clicked.
     */
    public void onClickShareTextButton(View v) {
        Toast.makeText(this, "TODO: Share text when this is clicked", Toast.LENGTH_LONG).show();
    }

    /**
     * This is where you will create and fire off your own implicit Intent. Yours will be very
     * similar to what I've done above. You can view a list of implicit Intents on the Common
     * Intents page from the developer documentation.
     *
     * @param v Button that was clicked.
     * @see <http://developer.android.com/guide/components/intents-common.html/>
     */
    public void createYourOwn(View v) {
        Toast.makeText(this,
                "TODO: Create Your Own Implicit Intent",
                Toast.LENGTH_SHORT)
                .show();
    }

    private void openWebPage(@NonNull final String url) {
        final Uri uri = Uri.parse(url);
        final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void showMap(@NonNull final Uri geoLocation){
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
