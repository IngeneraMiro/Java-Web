import java.net.*;

public class DnsLookUp{
  public static void main(String[] args) throws Exception{
   
   InetAddress[] address = InetAddress.getAllByName("nabytky.com");
   for(InetAddress a: address){
    String domain = a.getCanonicalHostName();
    System.out.printf("%s --> %s [Accessible: %s]%n",a,address,a.isReachable(2000));

   }
  }
}