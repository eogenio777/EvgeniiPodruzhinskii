package com.epam.tc.hw4.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AttachmentUtils {
    @Attachment(type = "image/png", value = "Try to use name {attachmentName}")
    public byte[] attachPngImage(String attachmentName, byte[] source) {
        return source;
    }
}
