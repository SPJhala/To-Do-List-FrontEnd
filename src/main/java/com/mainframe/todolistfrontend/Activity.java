package com.mainframe.todolistfrontend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Activity{

    @JsonProperty
    public String activity_name;

    public Activity() {
         }

    public Activity(String activityName) {
        this.activity_name = activityName;
    }

    @Override
    public String toString(){
        return "Activity Name: " + activity_name + "\n";
    }
}
