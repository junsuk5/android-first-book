package com.suwonsmartapp.quickdustinfo.finedust;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.suwonsmartapp.quickdustinfo.MainActivity;
import com.suwonsmartapp.quickdustinfo.R;
import com.suwonsmartapp.quickdustinfo.data.FineDustRepository;
import com.suwonsmartapp.quickdustinfo.data.LocationFineDustRepository;
import com.suwonsmartapp.quickdustinfo.model.FineDust;


/**
 * Created by junsuk on 2017. 9. 7..
 */

public class FineDustFragment extends Fragment implements FineDustContract.View {

    private TextView mLocationTextView;
    private TextView mTimeTextView;
    private TextView mDustTextView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private FineDustRepository mRepository;
    private FineDustPresenter mPresenter;

    public static FineDustFragment newInstance(double lat, double lng) {
        Bundle args = new Bundle();
        args.putDouble("lat", lat);
        args.putDouble("lng", lng);

        FineDustFragment fragment = new FineDustFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FineDustFragment() {
        // 반드시 필요함
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fine_dust, container, false);

        mLocationTextView = (TextView) view.findViewById(R.id.result_location_text);
        mTimeTextView = (TextView) view.findViewById(R.id.result_time_text);
        mDustTextView = (TextView) view.findViewById(R.id.result_dust_text);

        if (savedInstanceState != null) {
            mLocationTextView.setText(savedInstanceState.getString("location"));
            mTimeTextView.setText(savedInstanceState.getString("time"));
            mDustTextView.setText(savedInstanceState.getString("dust"));
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadFineDustData();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            double lat = getArguments().getDouble("lat");
            double lng = getArguments().getDouble("lng");
            mRepository = new LocationFineDustRepository(lat, lng);
        } else {
            mRepository = new LocationFineDustRepository(0, 0);
            ((MainActivity) getActivity()).getLastKnownLocation();
        }

        mPresenter = new FineDustPresenter(mRepository, this);

        mPresenter.loadFineDustData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.frag_fine_dust, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
            mPresenter.loadFineDustData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("location", mLocationTextView.getText().toString());
        outState.putString("time", mTimeTextView.getText().toString());
        outState.putString("dust", mDustTextView.getText().toString());
    }

    @Override
    public void showFineDustResult(FineDust fineDust) {
        try {
            mLocationTextView.setText(fineDust.getWeather().getDust().get(0).getStation().getName());
            mTimeTextView.setText(fineDust.getWeather().getDust().get(0).getTimeObservation());
            mDustTextView.setText(fineDust.getWeather().getDust().get(0).getPm10().getValue() + " ㎍/㎥, "
                    + fineDust.getWeather().getDust().get(0).getPm10().getGrade());
        } catch (Exception e) {
            mLocationTextView.setText("일일 허용량 초과");
            mTimeTextView.setText("일일 허용량 초과");
            mDustTextView.setText("일일 허용량 초과");
        }
    }

    @Override
    public void showLoadError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadingStart() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void loadingEnd() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void reload(double lat, double lng) {
        mRepository = new LocationFineDustRepository(lat, lng);
        mPresenter = new FineDustPresenter(mRepository, this);
        mPresenter.loadFineDustData();
    }
}
