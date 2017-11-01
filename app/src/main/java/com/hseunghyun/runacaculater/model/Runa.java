
package com.hseunghyun.runacaculater.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Runa {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Runa{" +
                "response=" + response +
                '}';
    }
}
