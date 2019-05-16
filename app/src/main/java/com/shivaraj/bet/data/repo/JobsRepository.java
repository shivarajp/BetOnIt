package com.shivaraj.bet.data.repo;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;
import java.util.concurrent.Executor;

import com.shivaraj.bet.data.source.local.JobModel;
import com.shivaraj.bet.data.source.local.JobsDao;
import com.shivaraj.bet.data.model.ResponseModel;
import com.shivaraj.bet.data.source.local.JobsDatabase;
import com.shivaraj.bet.utils.ApiResponse;
import com.shivaraj.bet.utils.Resource;
import com.shivaraj.bet.data.source.remote.GitHubApi;
import com.shivaraj.bet.data.source.remote.RetrofitGenerator;
import com.shivaraj.bet.data.source.remote.NetworkBoundResource;


public class JobsRepository {
    final private GitHubApi api;
    final private JobsDao dao;

    public JobsRepository(Application application) {
        JobsDatabase db = JobsDatabase.getDatabase(application);
        this.api = RetrofitGenerator.createService(GitHubApi.class);
        this.dao = db.jobsDao();
    }

    public ResponseModel getSample(){
        return api.getData();
    }

    public LiveData<Resource<List<JobModel>>> browseRepo(final int page) {
        LiveData<Resource<List<JobModel>>> liveData = new NetworkBoundResource<List<JobModel>, List<JobModel>>() {
            @Override
            protected void saveCallResult(@NonNull List<JobModel> items) {
                JobModel[] arr = new JobModel[items.size()];
                for (JobModel item : items) {
                    item.setPageNumber(page);
                }
                items.toArray(arr);
                dao.insertAll(arr);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<JobModel> data) {
                if(data != null && data.size() > 0){
                    return false;
                }else {
                    return true;
                }
            }

            @NonNull
            @Override
            protected LiveData<List<JobModel>> loadFromDb() {
                return dao.get(page);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<JobModel>>> createCall() {
                LiveData<ApiResponse<List<JobModel>>> response = api.browseRepoLiveData(page);
                return response;
            }
        }.getAsLiveData();

        return liveData;
    }

    public void clearCache() {
        new Executor() {
            @Override
            public void execute(Runnable command) {
                dao.deleteAll();
            }
        };
    }
}
