package Drive.Upload;

/*
 * Copyright © 2018-2019 Jitterbit, Inc.
 *
 * Licensed under the JITTERBIT MASTER SUBSCRIPTION AGREEMENT
 * (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * https://www.jitterbit.com/cloud-eula
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import java.io.BufferedReader;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.List;


/**
 * Connection to a Drive endpoint.
 */
public class DriveConnection  {


    /**
     * Constructs a Drive connection using a Drive ProjectId, Client Email and Private Key String.
     * Drive API calls
     */
    public DriveConnection(String projectId, String clientEmail, String privateKeyString) {
        this.projectId = projectId;
        this.clientEmail = clientEmail;
        this.privateKeyString = privateKeyString;
    }

    /**
     * Opens a Drive version 3 connection.
     * @throws
     */
    public Drive open() throws Exception {
        if (client != null) {
            throw  new Exception("Client is not created");
        }
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            PrivateKey privateKey = getPrivateKeyFromKeyString();

            GoogleCredential.Builder builder = new GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId(clientEmail)
                    .setServiceAccountPrivateKey(privateKey)
                    .setServiceAccountProjectId(projectId)
                    .setServiceAccountScopes(scopes);
            credential = builder.build();
            client = getDriveBuilder(httpTransport);
            return client;
        } catch (Exception x) {
            throw new Exception("e");
        }

    }

    public Drive getDriveBuilder(HttpTransport httpTransport) {
        return new Drive.Builder(httpTransport, JSON_FACTORY, credential).build();
    }

    /**
     * Returns the Drive version 3 client of the connection. If there is no client, opens
     * a new Drive connection and returns the client.
     *
     * @return the Drive version 3 client
     */
    public PrivateKey getPrivateKeyFromKeyString()
            throws Exception {
        try  {

            StringBuilder privateKeyLines = new StringBuilder();
            BufferedReader reader = new BufferedReader(new StringReader(privateKeyString));
            String line = null;
            while ((line = reader.readLine()) != null) {
                privateKeyLines.append(line);
            }

            String privateKeyBase64 = privateKeyLines.toString();
            privateKeyBase64 = privateKeyBase64.replace("-----BEGIN PRIVATE KEY-----", "");
            privateKeyBase64 = privateKeyBase64.replace("-----END PRIVATE KEY-----", "");
            privateKeyBase64 = privateKeyBase64.replaceAll("\\\\n", "");
            byte[] encodedKey = Base64.decodeBase64(privateKeyBase64);

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception ex) {
            ex.printStackTrace();

            throw new Exception(ex);
        }
    }


    /**
     * Returns the Drive version 3 client of the connection. If there is no client, opens
     * a new Drive connection and returns the client.
     *
     * @return the Drive version 3 client
     * @throws
     */
    public Drive getClient() throws Exception {
        if (client == null) {
            open();
        }
        return client;
    }

    /**
     * Closes the Drive connection.
     */
    public void close() {
        client = null;
    }

    public GoogleCredential getCredential() {
        return credential;
    }

    private static GoogleCredential credential = null;
    private Drive client;
    private String projectId;
    private String clientEmail;
    private String privateKeyString;
    List<String> scopes = Arrays.asList(
            DriveScopes.DRIVE,
            DriveScopes.DRIVE_FILE
    );
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public static void main(String[] args) throws Exception {
        DriveConnection connection = new DriveConnection("testproject-240819","trygoogledrive@testproject-240819.iam.gserviceaccount.com","-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCqRyY5ZEybqF2M\\nBinpXlAK21PayL8yzNR8TrmXjwGo866N8SzYeDh35Vo3sMkIganC0ormM7Hrh0nw\\nHjXZ5ju8dexhUlXcOs4cbKikXYQxxeLrlPu0HOkBCpcNSdLOKxbnBx+ExtcFGxka\\nZ4VhEV6vM5bzpMzyTsRl7mWLEDWPkdMG/L/3lh04iDzMSoh07B9mT9WXn+eFH7xC\\n7z+3A3pDZBZkxaiJ7oIgjtv5ce8Q5iraiRVQ9mj0BlitppffhAoKDKhnRWbVh4D1\\nn2XOXHLwewNM/D/lXrRbE1zQqN1sluvTTPZ3x0LdmsQSzR/7lvFxn5l3y/s/z46k\\nkoh8nQd5AgMBAAECggEAOloAeNlswUjcqd/w2+a0gkvv/AEgOG3mASVPR2c71+SN\\n9FlqF1J3c2rW1E/STIZ2oOtaqxGbDo4BofYQWTgjT22P0DgRcVN7j1UTI6RiaVYZ\\nccbPzMS9elxhMC4Uvg8ZHZmczPkA9WB4ZUqFdCKqMlr5B0x56S1h81ZTWOj4mdZ9\\nPtTpdOn4YzfTVUiSSEUd0n9PNHvzOhgapd1l4SZVkqHmETv8volMJoeUQ9M/7mtO\\n/vVynnKR8B5ppP+3QMHEugIP9Z6px8zFTou5RrsDUE5We0iJnj2O40Z+mvQOo9xE\\nMGk3EoiHcjzGikGH490iVZxPR8YkJSBPhDzOiaSJ2wKBgQDeDG+ovX5WuvhE5bbc\\n5joExn8STyG2WcEFtVWykuj/qq+axjVptoQoIFxklzc8vwpF4G7z40hwlolPlgWE\\nFNtY5y/iy/JLf9Hvy19LS37OJjtbkUN7IZBzU9lAitG5WiuTPPmg6fruapagiTb6\\n3Im/80/pmUNAgjltl0t8lEr6ywKBgQDEUEYnFyC8sOlbcIJWZ4q/o0MaFmBXhqAA\\nr4K+yELG3cPVtVOEvu5qfEPIkStcvFIvvRioxIH+sfAqhqe22JrqqyG8KpykPwlU\\ndnrDifJlDMyAUJnuJpdlVGGRme1xDItXh+JjrJdDU0MkfSfB4PqlLVOsD6WQS0kT\\nyHnzTzjqSwKBgCInB4WMn8H3P/hY3KddQc86OJE4WpcRdv7JxUSSFAQ+3IORuOVp\\njSoe0BcoqvZM6+eTfX1Fl/65KKV1DaQjERm7dUIM3FeAcWnQffe2xOowNzD+RsLJ\\nhs42N1QWBevcUpdy6XI2isHg4woOEMbE5OsBIWlsiUuCvBJ/Er02kdHBAoGAXB9X\\nbEycHi/esfwwDZ/1KkzQ1lMdzrTsLKYv8ptDStMSbRz0Hl0CwJGjtWD6OkBdSO2x\\nvrDIV97kVvBtj45B1FGOOSC99Q1nsW0P2ubHCshgxu4L7gfKD9DzIEJdfClo0iCA\\nCkoZiYxu+5aHq6GJvS1yEFIfXYEWv+PZ5mWMzdUCgYEAjWK3D8MefMD04ipfMF8Q\\nX+lmmTkJIRlZB1luWKN5khwjRIuQFxuM+tlrENgoeEo6jNPJ5lALaDMrR7qeeGyy\\nPIhitOZCpYHat9HAqB8QviLFjv6H9YBbcNjp/l6eJwRvJDHXPo7Kt0IPFnCnNm8+\\nZvG+XbXOnndWYfSr4Gwd/rA=\\n-----END PRIVATE KEY-----\\n");
      //  System.out.println(credential.getAccessToken());
        connection.open();
    }
}