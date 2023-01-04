package com.tweesky.cloudtools.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData {

    private boolean valid;
    private List<String> errors;
    private List<String> others;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
