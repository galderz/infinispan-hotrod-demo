package acme.infinispan.hotrod;

import org.infinispan.Cache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.manager.CacheContainer;
import org.testng.annotations.Test;

/**
 * // TODO: Document this
 *
 * @author Galder Zamarreño
 * @since // TODO
 */
public class ReplHotRodClient {

   private final String host = "lo2";
   private final int port = 11311;

   @Test
   public void connectAndLoad() {
      Cache<String, String> cache = getCache();
      cache.put("car", "bmw");
   }

   @Test
   public void getOnReadLoop() {
      Cache<String, String> cache = getCache();
      String line = "";
      boolean stop = false;
      do {
         line = Util.readLine("Hit enter to query value of 'car' key: ");
         if (line != null && !line.equals("q"))
            System.out.println("Value is = " + cache.get("car"));
         else
            stop = true;
      } while (!stop);
   }

   private Cache<String, String> getCache() {
      CacheContainer cacheContainer = new RemoteCacheManager(host, port);
      return cacheContainer.getCache();
   }

}
