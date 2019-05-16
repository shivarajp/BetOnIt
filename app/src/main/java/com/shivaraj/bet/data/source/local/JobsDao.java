package com.shivaraj.bet.data.source.local;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;


@Dao
public interface JobsDao {

    @Query("SELECT * FROM  jobs_table WHERE pageNumber = :pageNumber")
    LiveData<List<JobModel>> get(int pageNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(JobModel... jobModels);

    @Delete
    void delete(JobModel jobModel);

    @Query("DELETE FROM jobs_table")
    void deleteAll();
}
