package it.joint.address.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.util.ResourceUtils;

public class TestUtil {

    public static String fileAsJson(String filePath) throws IOException {
	File file = ResourceUtils.getFile(filePath);
	return new String(Files.readAllBytes(file.toPath()));
    }
}
