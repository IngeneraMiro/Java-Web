import java.util.Map;

public interface HttpResponce {
    Map<String,String> getHeaders();
    int getStatusCode();
    byte[] getContent();
    byte[] getBytes();
    void setStatusCode(int statusCode);
    void setContent(byte[] content);
    void addHeader(String key,String value);
}
