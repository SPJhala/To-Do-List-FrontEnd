package com.mainframe.todolistfrontend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivityToDisplay {

    @JsonProperty
    public int id;
    @JsonProperty
    public String activity_name;

    public ActivityToDisplay() {id = 1;
         }

    public ActivityToDisplay(String activityName) {
        this.activity_name = activityName;
    }

    @Override
    public String toString(){
        return "Activity Name: " + activity_name + "\n";
    }
}
