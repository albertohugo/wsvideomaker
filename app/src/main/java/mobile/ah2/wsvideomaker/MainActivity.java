package mobile.ah2.wsvideomaker;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {

    public WebView youtube;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        youtube = (WebView) findViewById(R.id.youtube);
        youtube.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        youtube.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        youtube.getSettings().setJavaScriptEnabled(true);
        youtube.setWebViewClient(new WebViewClient() { //WebViewClient
            @Override
            public void onLoadResource(WebView view, String url) {
                youtube.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('_mah')[0].style.display = 'none'; " +
                        "})()");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //Toast.makeText(getBaseContext(), "onPageFinished", Toast.LENGTH_SHORT).show();
                view.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Toast.makeText(getBaseContext(), "onPageStarted", Toast.LENGTH_SHORT).show();
                view.setVisibility(View.INVISIBLE);
            }
        });
        youtube.loadUrl("https://m.youtube.com/channel/UChmvKE38_dDGw87vVx0J1SA");


}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK)&& youtube.canGoBack()) {
                youtube.goBack();
                return true;
            }
        else{

        }

        return super.onKeyDown(keyCode, event);
    }

}
