import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Hrequest {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static DateTimeFormatter dformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) throws IOException {
        Request request = new Request();
        Responce responce = new Responce();
        List<String> urlList = ListValidUrls();
        List<String> headersList = GetHeadersList();
        request.setMethod(headersList.get(0).split("\\s+")[0]);
        request.setRequestUrl(headersList.get(0).split("\\s+")[1]);
        setHeaders(request, headersList);
        setBodyParam(request, headersList);

        ResponceSetHeaders(request, responce);

        boolean valid = validator(urlList, request.getRequestUrl());
        if ((request.isResource() && headersList.get(headersList.size() - 1) == null) || (request.getMethod().equals("POST") && headersList.get(headersList.size() - 1).equals(""))) {
            responce.setStatusCode(400);
            responce.setContent("There was an error with the requested functionality due to malformed request.".getBytes());
        } else {
            if (!valid) {
                responce.setStatusCode(404);
                responce.setContent("The requested functionality was not found.".getBytes());
            } else {
                try {
                    responce.setStatusCode(200);
                    String name = new String(Base64.getDecoder().decode(request.getHeaders().get("Authorization").split("\\s+")[1]));
                    responce.setContent(String.format("Greetings %s! You have successfully created Yum with quantity – %s, price – %s.",
                            name, request.getBodyParameters().get("quantity"), request.getBodyParameters().get("price")).getBytes());

                } catch (NullPointerException e) {
                    responce.setStatusCode(401);
                    responce.setContent("You are not authorized to access the requested functionality.".getBytes());
                }
            }
        }

        System.out.println(responce.toString());
    }

    private static List<String> ListValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    private static List<String> GetHeadersList() throws IOException {
        List<String> headers = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null && line.length() > 0) {
            headers.add(line);
        }
        headers.add("");
        if ((line = reader.readLine()) != null && line.length() > 0) {
            headers.add(line);
        }
        return headers;
    }

    private static void setHeaders(Request request, List<String> list) {
        list.stream().skip(1).filter(h -> h.contains(": ")).map(h -> h.split(": ")).
                forEach(h -> request.addHeader(h[0], h[1]));
    }

    private static void setBodyParam(Request request, List<String> list) {
        if (list.get(list.size() - 1).equals("")) {
            return;
        }
        Arrays.stream(list.get(list.size() - 1).split("&")).map(p -> p.split("=")).
                forEach(p -> request.addBodyParameter(p[0], p[1]));
    }

    private static void ResponceSetHeaders(Request request, Responce responce) {
        request.getHeaders().entrySet().stream().filter(f->!f.getKey().equals("Authorization")).forEach(p -> responce.getHeaders().put(p.getKey(), p.getValue()));
    }

    private static boolean validator(List<String> list, String str) {
        return list.contains(str);
    }
}
