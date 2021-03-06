package com.dpndncy.course.rest.pojo

import com.dpndncy.db.entity.course.Activity
import com.dpndncy.db.entity.course.ActivitySubmission

/**
 * Created by vaibhav on 03/03/17.
 */
class ActivityInfo implements Serializable {
    Activity activity;
    Long submissionCount;
    List<ActivitySubmission> latestSubmissions;
}
