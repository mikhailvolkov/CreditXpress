package com.mikhail.creditexpress.activities.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mikhail.creditexpress.CreditInfo;
import com.mikhail.creditexpress.activities.adapters.DataBinder;
import com.mikhail.creditexpress.ListDataSender;
import com.mikhail.creditexpress.utils.ListUtil;
import com.mikhail.creditexpress.R;
import com.mikhail.creditexpress.view.SlidingTabLayout;

import java.util.Arrays;
import java.util.List;

/**
 * Фрагмент для отображения каталога кредитных организаций, включает в себя табы с типами кредитов
 * Отображается при клике на меню, реализованное в
 * @see com.mikhail.creditexpress.view.SlidingMenuListAdapter
 * @author Volkov Mikhail
 */
@SuppressLint("ValidFragment")
public class FullCatalog extends Fragment {
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private Context context;//контекст выбранного пункта меню
    private Activity activity;//активити выбранного пункта меню
    private List<List<CreditInfo>> data;


    @SuppressLint("ValidFragment")
    public FullCatalog(List<List<CreditInfo>> data, Context context, Activity activity) {
        this.data = data;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.catalog_full, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new CatalogAdapter());

        // Give the SlidingTabLayout the ViewPager, this must be
        // done AFTER the ViewPager has had it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);

    }

    public class CatalogAdapter extends PagerAdapter {

        /**
         * Return the number of pages to display
         */
        @Override
        public int getCount() {
            return 3;
        }

        /**
         * Return true if the value returned from is the same object as the View
         * added to the ViewPager.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        /**
         * Return the title of the item at position. This is important as what
         * this method returns is what is displayed in the SlidingTabLayout.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Онлайн";
                case 1:
                    return "Наличные";
                case 2:
                    return "Кредиты";

            }
            return null;
        }

        /**
         * Instantiate the View which should be displayed at position. Here we
         * inflate a layout from the apps resources and then change the text
         * view to signify the position.
         * Здесь задается таб для списка с кредитами в зависимости от позиции
         */

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            switch (position) {
                case 0:
                    return instantiateTab(container, ListUtil.getDataInSingleList(Arrays.asList(data.get(0),data.get(1))));
                case 1:
                    return instantiateTab(container, data.get(2));
                case 2:
                    return instantiateTab(container, data.get(3));

            }
            return null;
        }

        private View instantiateTab(ViewGroup container, List<CreditInfo> data) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_tab_4,
                    container, false);
            ListView listView = (ListView) view.findViewById(R.id.best);
            DataBinder bindingData = new DataBinder(activity, data);
            bindingData.notifyDataSetChanged();
            listView.setAdapter(bindingData);
            onItemClicked(listView, data);
            // Add the newly created View to the ViewPager
            container.addView(view);
            return view;

        }

        private void onItemClicked(ListView list, List<CreditInfo> data) {
            final ListDataSender sender = new ListDataSender(data, context);
            // Click event for single list row
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   startActivity(sender.getIntent(position));
                }
            });

        }
        /**
         * Destroy the item from the ViewPager. In our case this is simply
         * removing the View.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
