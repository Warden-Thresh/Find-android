package com.warden.find.gson;

import java.util.List;

/**
 * Created by Warden on 2017/4/16.
 */

public class JobList {


    private List<Job> joblist;

    public List<Job> getJoblist() {
        return joblist;
    }

    public void setJoblist(List<Job> joblist) {
        this.joblist = joblist;
    }

    public static class Job {
        /**
         * jobId : 1
         * jobName : 12
         * jobDetial : 1234
         * userId : 11
         * image : null
         */

        private int jobId;
        private String jobName;
        private String jobDetial;
        private int userId;
        private String image;

        public int getJobId() {
            return jobId;
        }

        public void setJobId(int jobId) {
            this.jobId = jobId;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public String getJobDetial() {
            return jobDetial;
        }

        public void setJobDetial(String jobDetial) {
            this.jobDetial = jobDetial;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
