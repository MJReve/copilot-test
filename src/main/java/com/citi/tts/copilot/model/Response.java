package com.citi.tts.copilot.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private Object data;

    public static Response of(Object data) {
        Response response = new Response();
        response.setData(data);
        return response;
    }

    // cascading to Json String
    public String toJson() {
        if (data instanceof List) {
            // convert list object to Json string
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Object o : (List) data) {
                sb.append(o.toString());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return "{\"data\":" + sb + "}";
        }

        return "{\"data\":" + data + "}";
    }

}
