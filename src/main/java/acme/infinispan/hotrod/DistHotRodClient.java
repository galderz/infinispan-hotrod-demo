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
public class DistHotRodClient {

   private final String host = "lo4";
   private final int port = 11311;

   @Test
   public void loadKeys() {
      Cache<String, String> cache = getCache();

      // Get a non-existent key to receive topology view, is this needed?
      cache.get("blahblah");

      for (int i = 0; i < 10; i++)
         cache.put("car" + i, "bmw");
   }

   @Test
   public void getIndexedInLoop() {
      Cache<String, String> cache = getCache();

      // Get a non-existent key to receive topology view, is this needed?
      cache.get("blahblah");

      int index = 0;
      do {
         index = Integer.parseInt(Util.readLine("Enter key index to look up: "));
         System.out.println(cache.get("car" + index));
      } while (index != 99);
   }

   private Cache<String, String> getCache() {
      CacheContainer cacheContainer = new RemoteCacheManager(host, port);
      return cacheContainer.getCache();
   }


}
