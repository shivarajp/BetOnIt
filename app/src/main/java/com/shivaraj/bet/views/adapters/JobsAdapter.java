package com.shivaraj.bet.views.adapters;

public class JobsAdapter {

/*
    List<JobModel> mJobsList;

    public JobsAdapter(List<JobModel> jobs){
        mJobsList = jobs;
    }

    public void setJobsList(List<JobModel> newJobs) {

        mJobsList = newJobs;
        notifyDataSetChanged();

        //TODO refresh the recyclerview with newly added data.
        */
/*if(mJobsList != null && mJobsList.size() <= 0){
            mJobsList.addAll(newJobs);
            notifyItemRangeInserted(0, mJobsList.size());
        }else {
            mJobsList.addAll(newJobs);
            notifyItemRangeInserted(mJobsList.size()-1, mJobsList.size() + newJobs.size());
        }*//*

    }

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item,
                        viewGroup, false);
        return new JobsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder jobsViewHolder, int position) {

        Picasso.get().load(mJobsList.get(position).getCompanyLogo())
                .into(jobsViewHolder.binding.imageView);
        jobsViewHolder.binding.setJob(mJobsList.get(position));
        jobsViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mJobsList.size();
    }

    public class JobsViewHolder extends RecyclerView.ViewHolder {

        final ItemBinding binding;

        public JobsViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
*/

}
