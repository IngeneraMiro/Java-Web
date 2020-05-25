import java.io.IOException;
import java.net.InetAddress;
public class DnsLookUp {
    public static void main(String[] args) throws IOException {
        InetAddress[] address = InetAddress.getAllByName("google.com");
        for (InetAddress a : address) {
            String domain = a.getCanonicalHostName();
            System.out.printf("%s --> %s [Accessible: %s]%n", a, domain, a.isReachable(2000));
        }
    }
}