package com.shivaraj.bet.views;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.*;
import com.shivaraj.bet.data.model.JobsResponseHolder;
import com.shivaraj.bet.data.model.ResponseModel;
import com.shivaraj.bet.data.repo.JobsRepository;
import com.shivaraj.bet.data.source.local.JobModel;
import com.shivaraj.bet.utils.Resource;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private JobsRepository repository;
    final private MutableLiveData<Request> request = new MutableLiveData();

    public MainActivityViewModel(Application application){
        super(application);
        repository = new JobsRepository(application);
    }

    public void load(int page) {
        request.setValue(new Request(page));
    }

    public ResponseModel getSampleData(){
        return repository.getSample();
    }

    public LiveData<Resource<JobsResponseHolder>> getResult() {
        return result;
    }


    final private LiveData<Resource<JobsResponseHolder>> result = Transformations.switchMap(request, new Function<Request, LiveData<Resource<JobsResponseHolder>>>() {
        @Override
        public LiveData<Resource<JobsResponseHolder>> apply(final Request input) {
            LiveData<Resource<List<JobModel>>> resourceLiveData = repository.browseRepo(input.page);
            final MediatorLiveData<Resource<JobsResponseHolder>> mediator = new MediatorLiveData<Resource<JobsResponseHolder>>();
            mediator.addSource(resourceLiveData, jobModels -> {
                JobsResponseHolder resp = new JobsResponseHolder(input.page, jobModels.getData());
                Resource<JobsResponseHolder> response = null;
                switch (jobModels.getStatus()){
                    case LOADING:
                        response =  Resource.loading(resp);
                        break;
                    case SUCCESS:
                        response =  Resource.success(resp);
                        break;
                    case ERROR:
                        response =  Resource.error(jobModels.getException(), null);
                        break;
                }
                mediator.setValue(response);
            });
            return mediator;
        }
    });

    public static class Request {
        final private int page;
        public Request(int page) {
            this.page = page;
        }
    }

}
