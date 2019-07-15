package com.company.projectoop.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class Focus_area_code {

    public Focus_area_code() {
    }

    private String focus_area_code;

    public String getFocus_area_code() {
        return focus_area_code;
    }

    public void setFocus_area_code(String focus_area_code) {
        this.focus_area_code = focus_area_code;
    }


}
