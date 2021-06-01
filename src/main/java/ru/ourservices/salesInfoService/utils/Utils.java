package ru.ourservices.salesInfoService.utils;

import org.springframework.web.context.request.WebRequest;
import ru.ourservices.salesInfoService.model.entity.Deal;

public class Utils {
    public static String buildMessage(Exception ex, WebRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Internal error occurred!\n")
                .append("Exception class: ")
                .append(ex.getClass())
                .append("\n")
                .append("Exception message: ")
                .append(ex.getLocalizedMessage())
                .append("\n");
        for (Throwable th: ex.getSuppressed()) {
            sb.append("Exception message: ")
                    .append(th.getLocalizedMessage())
                    .append("\n");
        }
        sb.append("Request: ")
                .append(request.toString())
                .append("\n");
        return sb.toString();
    }
}
