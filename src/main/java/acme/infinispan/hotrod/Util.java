package acme.infinispan.hotrod;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * // TODO: Document this
 *
 * @author Galder Zamarreño
 * @since // TODO
 */
public class Util {

   public static String readLine(String msg) {
       BufferedReader reader=null;
       String tmp=null;

       try {
           System.out.print(msg);
           System.out.flush();
           System.in.skip(System.in.available());
           reader=new BufferedReader(new InputStreamReader(System.in));
           tmp=reader.readLine().trim();
           return tmp;
       } catch(Exception e) {
           return null;
       }
   }

}
