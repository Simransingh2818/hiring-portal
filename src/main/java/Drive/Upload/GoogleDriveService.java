package Drive.Upload;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;
import com.google.api.client.json.gson.GsonFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;


@Service
public class GoogleDriveService {
    private final HttpTransport transport = new NetHttpTransport();
    private final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    public static void uploadBasic(String fileId ) throws Exception {






    }

    public static void main(String[] args) throws Exception {
        File fileMetadata = new File();
        fileMetadata.setName("Drive.json");

        java.io.File file = createWriteFile ();
        FileContent content = new FileContent("*/*", file);
        DriveConnection connection = new DriveConnection("hiring-portal-380708", "simran@hiring-portal-380708.iam.gserviceaccount.com", "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDVBQNzzMaqHrFL\nNqHh+pMgM6pqouEnDGMqspK3DI5gee4Qrj8BzF/ICYZxR5MktapJe97/EGTR4EnW\n4lF1neCcyfOleMtFJKq6Wf1f4wtk4gJH7unBs7Uu6iIOzJ12pa4ytKYOfoneYteu\nSeIln8XgqPfpVqKqcTekak26FusR6/unBubiEiZL+cAriNbb/XwTKUtYsu8eXpbM\nSamIok8HktIdFTjTDfzYs4KVfianGMJFTZRURTAoU/iQpbOE2sLKuAkJzCwzER8w\na0k8BudMIxLREeFiHjlNuu4GyywVVrpxVJQCysEsUpQz9tabPSwNWcsIEzlobOOR\n3cWXlXotAgMBAAECggEAAYrUQBHi/FdVjEhcerDgQNLu8G+2dpxp2WTKmCWwoDUx\nypAgZ6htpxqejRJqF9Fy5q5NaoZZXIWlszcXROU8O5Zgu7l4q0r9K3U8VuXjbrsO\nLiwmKB9fd/P9BCNqHnJLpRVWwHMdF22axynEx+X0vlAq31cfvWEteHTGlErXL92E\npq48ImZxe1jLs+UoNOGX7MH/xBhScPLBgQ8U0fpX1T1hGN4+Il+lr1ZWQgkCi38k\nmpT3ywwH7iLMktxWRVJdchDLF0Cja5B9XNg7jHvGNuVm+HPn0k7qjOVX3ouUf3XH\nhDTwHBrb5hIf/Mw12KOuMEJ2ue0haHtEBjxbhsm2dwKBgQDsazT8xXBsgiTFAzmP\nMO8AaBmXAwAFbc93JQMaKlzfwd50COwDAzPRXgiJQHyQTPaydkjSwD2SgCNSA7iU\nac168IxN0hQr2p5DA7TNgm6mnA0O+nQeldm18fu0bJkVIK6NdB6G5YWGaU9lUTqv\nW17xAH9esUq8ukzVqeWy1uw71wKBgQDmqaw/jVk5fzqnKOzDd175uatXfMQoZbrZ\nncm6Ok4rP6fG4Yhu8BI/cxpVHl7hV1WH/85VIKsZ7NrBK5eJkwVQQIzCefSaiZfb\nL1vm+NBBZ7pejh5Jj7ayZckQHqyJT694zGjdWBgtlN8BEZDqGHgV2yK50ZmjdwA4\nQdyvrEzZmwKBgQDVWIWPmYizktolo5xcuJblcgutt/pb69VtukvLX/FBxwy8/5Qj\nv88SH95qJXSDL8DsGUvsS6kEvHejOlJmM/hG+i5Sx8KGNJne9chCMHM0DNzH6IHD\nXA0EYON41QtsAnsikWg+6X8OZZhNYPkpZ7YMnBtU038TQre1lMUBF2xFLwKBgC6m\nBeXcaj0MlAoWZrupOpX6jMJmwy0AVeeI17FH9WMdvoqkNflpp+cZGiw4tiuQhAW7\nIDlrH+9gTWVfsYfrKdnvFAiIrYVLamCzlTyC+Wsg1TomN83cuLAfkagm6/HhIbWm\ncVbCaDFfuDH5IXuWc0LITJ+T2iSK/opiF4WXrPsdAoGAF0xBWX8ofpijVMthDYz2\nbfrIp7eh8OoaaOyxUZRjI6Je7RsihczpIh3k3zXORdOvJOk34BrPxqxs9wYH4nYt\n5rJRC6M3Nji4dXSSGgbWYHISdbvnMS4QeDCETDnZxeZGNz3X/ajmBsQsoAT/bdHN\nB0QYePG/sJHMUAhRCKza/nw=\n-----END PRIVATE KEY-----\n");
        Drive client = connection.open();
       File files= client.files().update("", fileMetadata, content)
                .execute();
        files.getId();
    }


    private static java.io.File createWriteFile () throws IOException {
        return new java.io.File("/Quiz1.json");
    }

}


