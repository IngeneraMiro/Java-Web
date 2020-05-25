import java.util.*;

public class Request implements HttpRequest {

    private String method;
    private String requestUrl;
    private Map<String,String> headers;
    private Map<String,String> bodyParameters;

    public Request(){
         this.headers = new LinkedHashMap<>();
         this.bodyParameters = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return this.bodyParameters;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
         this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
          this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header,value);
    }

    @Override
    public void addBodyParameter(String param, String value) {
         this.bodyParameters.put(param,value);
    }

    @Override
    public boolean isResource() {
        if(this.method.equals("POST")){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
         output.append(String.format("%s %s HTTP/1.1",this.method,this.requestUrl)).append(System.lineSeparator());
          this.headers.entrySet().stream().forEach(p->output.append(String.format("%s: %s",p.getKey(),p.getValue()))
                  .append(System.lineSeparator()));
          output.append(System.lineSeparator());
        List<String> pList = new ArrayList<>();
          this.bodyParameters.forEach((key, value) -> pList.add(String.format("%s=%s",key,value)));
          output.append(String.join("&",pList)).append(System.lineSeparator());
        return output.toString();
    }
}
