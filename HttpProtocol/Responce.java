import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedHashMap;
import java.util.Map;

public class Responce implements HttpResponce {
    private int statusCode;
    private Map<String, String> headers;
    private byte[] content;

    public Responce() {
        this.headers = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("HTTP/1.1 %d %S", this.statusCode, ResponceCodes.getStaus(this.statusCode))).append(System.lineSeparator())
                .append("Date: ");
        if (!this.headers.containsKey("Date")) {
            output.append(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))).append(System.lineSeparator());
        } else {
            output.append(this.headers.get("Date")).append(System.lineSeparator());
        }
        this.headers.entrySet().stream().filter(f -> !f.getKey().equals("Date")).forEach(p -> output.append(String.format("%s: %s", p.getKey(), p.getValue())).append(System.lineSeparator()));
        output.append(System.lineSeparator()).append(String.format("%s", new String(this.content))).append(System.lineSeparator());

        return output.toString();
    }
}
