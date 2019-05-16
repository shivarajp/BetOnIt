package com.shivaraj.bet.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.shivaraj.bet.data.model.JobsResponseHolder;
import com.shivaraj.bet.data.model.ResponseModel;
import com.shivaraj.bet.data.source.local.JobModel;
import com.shivaraj.bet.views.adapters.JobsAdapter;
import com.shivaraj.bet.views.adapters.OnScroll;
import com.shivaraj.bet.views.adapters.RecyclerViewScroll;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel modelView;
    private int page = 1;
    //private ActivityScrollingBinding mBinding;
    private JobsAdapter mAdapter;
    private List<JobModel> mJobs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mBinding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling);
        setUpViewModel();
        //mBinding.setMainActivity(this);
        //mAdapter = new JobsAdapter(mJobs);
        //mBinding.jobsRv.setLayoutManager(new LinearLayoutManager(this));
        //mBinding.jobsRv.setAdapter(mAdapter);

        //mBinding.jobsRv.setOnScrollListener(loadMoreScrollListener);
        //mBinding.jobsRv.setNestedScrollingEnabled(false);
        //subscribe();
        ResponseModel model = modelView.getSampleData();
        Log.d("teee", model.getApiVersion());
    }

    // TODO This is a temporary fix to navigate or load next page of jobs
    public void loadMoreJobs() {
        //mBinding.floatingActionButton.setClickable(false);
        //mBinding.floatingActionButtonLeft.setClickable(false);

        modelView.load(++page);
    }


    // TODO This is a temporary fix to navigate or load next page of jobs
    public void loadOldJobs() {
        --page;
        if(page > 0){
            modelView.load(page);
        }else {
            page = 1;
            Toast.makeText(MainActivity.this, "We are already on first list of jobs", Toast.LENGTH_LONG).show();
        }
    }

    private void subscribe() {
        modelView.getResult().observe(this, repoResponseResource -> {
            Log.d("Status ", ""+repoResponseResource.getStatus());
            //mBinding.progressBar.setVisibility(View.VISIBLE);
            switch (repoResponseResource.getStatus()){
                case LOADING: {
                    JobsResponseHolder data = repoResponseResource.getData();
                    break;
                }
                case ERROR:
                    //mBinding.progressBar.setVisibility(View.GONE);
                    Log.d("Error", repoResponseResource.getException().getMessage());
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
                    break;
                case SUCCESS: {
                    //mBinding.progressBar.setVisibility(View.GONE);
                    //mAdapter.setJobsList(repoResponseResource.getData().getList());
                    //mBinding.floatingActionButton.setClickable(true);
                    //mBinding.floatingActionButtonLeft.setClickable(true);
                    //mBinding.pages.setText("Page = " +page+"");
                    Log.d("new data", "rcvd loaded");
                    break;
                }
            }
        });
        modelView.load(page);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    //TODO Fix endless scroll.
    RecyclerViewScroll loadMoreScrollListener = new RecyclerViewScroll(new OnScroll() {
        @Override
        public void loadMore() {
            loadMoreJobs();
        }
    }, mJobs.size(), 3);

    private void setUpViewModel() {
        modelView = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

}
