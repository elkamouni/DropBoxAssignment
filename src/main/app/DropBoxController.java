package app;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class DropBoxController {
    private static final String ACCESS_TOKEN = "wq9n6iYsNhAAAAAAAAAAVvpzQzhteZ_473Y9FaE-O298EoUyJ_HVS7YndIYtdHEq";
    //Get files
    @RequestMapping("/getfiles")
    @ResponseBody
    public ListFolderResult get() throws DbxException {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        return client.files().listFolder("");
    }

    //Download files
    @RequestMapping("/download/{fileName}")
    @ResponseBody
    public String download(@PathVariable("fileName") String name) throws DbxException, IOException {
        String downloadFileName = "/"+ name;
        try {
            OutputStream downloadPath = new FileOutputStream("/Users/<user>/Downloads/downloaded" + downloadFileName);
            DbxRequestConfig config = DbxRequestConfig.newBuilder("").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            client.files().downloadBuilder(downloadFileName).download(downloadPath);
        }
        catch (Exception e){
            if(e.getMessage().contains("not_found"));
            {
               return ("File not found");
            }
        }
        return (name+" Downloaded successfully!!");
    }

    //Upload files
    @RequestMapping("/upload/{fileName}")
    @ResponseBody
    public String uploadFile(@PathVariable("fileName") String name) throws IOException, DbxException {
        String uploadFileName = "/"+name;
        try {
            InputStream uploadPath = new FileInputStream("/Users/<user>/Downloads" + uploadFileName);
            DbxRequestConfig config = DbxRequestConfig.newBuilder("").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            client.files().uploadBuilder(uploadFileName).uploadAndFinish(uploadPath);
        }
        catch (Exception e){
            return(e.getMessage());

        }
        return (name+" Uploaded successfully!!");
    }

// Upload files to folder
    @RequestMapping("/upload/{folderName}/{fileName}")
    @ResponseBody
    public String uploadFileToFolder(@PathVariable("folderName") String folder, @PathVariable ("fileName")String file) throws IOException, DbxException {
        String uploadTo = "/"+folder;
        String uploadFile = "/"+file;
        try {
            InputStream Path = new FileInputStream("/Users/<user>/Downloads" + uploadFile);
            DbxRequestConfig config = DbxRequestConfig.newBuilder("").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            client.files().uploadBuilder(uploadTo + uploadFile).uploadAndFinish(Path);
        }
        catch (Exception e){
            return(e.getMessage());
        }
        return (file+" uploaded to "+folder+" succesfully");

    }


}
