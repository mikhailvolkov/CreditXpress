package com.mikhail.creditexpress.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mikhail.creditexpress.CreditInfo;
import com.mikhail.creditexpress.ListUtil;
import com.mikhail.creditexpress.R;
import com.mikhail.creditexpress.activities.fragments.FullCatalog;
import com.mikhail.creditexpress.view.SlidingMenuListAdapter;
import java.io.Serializable;
import java.util.List;


public class CreditActivity extends
        ActionBarActivity {

    private ActionBar actionBar;

    private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private List<List<CreditInfo>> data;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.action_bar_title_layout);

        ((TextView) findViewById(R.id.action_bar_title)).setText(
                Html.fromHtml("<font color=\"white\">" + "Экспресс" + "<br>" + "Кредит" + "</font>"));
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(getResources().getColor(R.color.actionBarColor));
        actionBar.setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.fragment_tab_4);

        createLeftSlidingMenu(savedInstanceState);
        data = (List<List<CreditInfo>>) getIntent().getSerializableExtra("data");

    }

    private void createLeftSlidingMenu(Bundle savedInstanceState) {
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new SlidingMenuListAdapter(this, mScreenTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer_xml,/* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {
/** Called when a drawer has settled in a completely closed state. */
//            public void onDrawerClosed(View view) {
//                getSupportActionBar().setTitle(mTitle);
//                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }

            /** Called when a drawer has settled in a completely open state. */
//            public void onDrawerOpened(View drawerView) {
//                getSupportActionBar().setTitle(mDrawerTitle);
//                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//            }
        };
        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.openDrawer(Gravity.LEFT);
//
        // Initialize the first fragment when the application first loads.
//        if (savedInstanceState == null) {
//            selectItem(0);
//        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
        // true, then it has handled the app icon touch event
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * Swaps fragments in the main content view
     */

    private void selectItem(int position) {
        Fragment fragment = null;
        // Update the main content by replacing fragments
        switch (position) {
            case 0:
                fragment = new FullCatalog(data, CreditActivity.this, this);
                break;
            case 1:
                Intent filterIntent = new Intent(CreditActivity.this, SpinnerActivity.class);
                filterIntent.putExtra("data", (Serializable) ListUtil.getDataInSingleList(data));
                startActivity(filterIntent);
                break;
            case 2:
                Intent promotionIntent = new Intent(CreditActivity.this, PromotionActivity.class);
                startActivity(promotionIntent);
                break;
            case 3:
                Intent feedBackIntent = new Intent(CreditActivity.this, FeedBackActivity.class);
                startActivity(feedBackIntent);
                break;
            default:
                break;
        }

//         Insert the fragment by replacing any existing fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();

            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
            ((TextView) findViewById(R.id.action_bar_title)).setText(
                    Html.fromHtml("<font color=\"white\">" + mScreenTitles[position] + "</font>"));
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }

}