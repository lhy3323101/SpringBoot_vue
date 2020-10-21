package com.lhy.systemdemo.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Description:
 *
 * @since 2020/10/16 11:16
 */
@Component
public class NoStaticResourceHttpRequestHandler extends ResourceHttpRequestHandler {

    public final static String ATTR_FILE = "NON-STATIC-FILE";

    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {
        final Path filePath = (Path)request.getAttribute(ATTR_FILE);
        return new FileSystemResource(filePath);
    }
}
