package acme.infinispan.hotrod;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.server.hotrod.HotRodServer;
import org.infinispan.util.logging.Log;
import org.infinispan.util.logging.LogFactory;
import org.testng.annotations.Test;

import java.util.Properties;

/**
 * // TODO: Document this
 *
 * @author Galder Zamarreño
 * @since // TODO
 */
@Test
public class HotRod {
   private static final Log log = LogFactory.getLog(HotRod.class);

   public EmbeddedCacheManager cacheManager;

   @Test
   public void start() throws Exception {
      String cfg = System.getProperty("hotrod.cfg");
      String host = System.getProperty("hotrod.host");
      log.info("Host: " + host + ", cfg: " + cfg);
      cacheManager = new DefaultCacheManager(cfg);
      startHotRodServer(host);
      cacheManager.getCache().addListener(new Reporter(host, 11311));

      while (true) {
         Thread.sleep(2000);
      }
   }

   private void startHotRodServer(String host) {
      HotRodServer server = new HotRodServer();
      server.start(getProperties(host), cacheManager);
   }

   private Properties getProperties(String host) {
      Properties p = new Properties();
      p.setProperty("infinispan.server.host", host);
      p.setProperty("infinispan.server.port", "11311");
      return p;
   }

}
