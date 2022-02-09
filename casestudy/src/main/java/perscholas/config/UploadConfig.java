package perscholas.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Slf4j
@Configuration
public class UploadConfig implements WebMvcConfigurer {

    @Value("${fileupload.maxFileSize}")
    private String maxFileSize;

    @Bean
    public CommonsMultipartResolver commonsMultipartResolver(){
        long bytes = DataSize.parse(maxFileSize).toBytes();
        log.debug("Maximum file size upload is " + bytes + " bytes");

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSizePerFile(bytes);
        return commonsMultipartResolver;
    }

    // https://stackoverflow.com/questions/59289571/how-to-display-files-from-external-folder-with-spring-boot-2-1-1
    String myExternalFilePath = System.getProperty("java.io.tmpdir") + File.separator + "perscholas"; // end your path with a /

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/perscholas/**").addResourceLocations(myExternalFilePath);
    }

}
