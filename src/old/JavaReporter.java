package acme.infinispan.hotrod;

import org.infinispan.Cache;
import org.infinispan.distribution.DistributionManager;
import org.infinispan.marshall.Marshaller;
import org.infinispan.marshall.jboss.GenericJBossMarshaller;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryVisited;
import org.infinispan.notifications.cachelistener.event.CacheEntryEvent;
import org.infinispan.remoting.transport.Address;
import org.infinispan.server.hotrod.TopologyAddress;
import org.infinispan.server.hotrod.TopologyView;
import org.infinispan.util.ByteArrayKey;
import org.infinispan.util.logging.Log;
import org.infinispan.util.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * // TODO: Document this
 *
 * @author Galder Zamarreño
 * @since // TODO
 * @deprecated Hard to implement via Java
 */
@Listener
@Deprecated
public class JavaReporter {
//   private static final Log log = LogFactory.getLog(JavaReporter.class);
//
//   private final Marshaller marshaller = new GenericJBossMarshaller();
//   private final String host;
//   private final int port;
//
//   public JavaReporter(String host, int port) {
//      this.host = host;
//      this.port = port;
//   }
//
//   @CacheEntryVisited
//   public void visisted(CacheEntryEvent e) throws Exception {
//      if (!e.isPre()) {
//         ByteArrayKey k = (ByteArrayKey) e.getKey();
//         Object o = getObject(k);
//         log.info("[{0}:{1}] entry visited {2}", host, port, o);
//      }
//   }
//
//   @CacheEntryCreated
//   public void created(CacheEntryEvent e) throws Exception {
//      if (!e.isPre() && e.isOriginLocal()) {
//         Cache<String, TopologyView> topologyCache = e.getCache().getCacheManager().getCache("___hotRodTopologyCache");
//         ByteArrayKey k = (ByteArrayKey) e.getKey();
//         DistributionManager distManager = e.getCache().getAdvancedCache().getDistributionManager();
//         if (distManager != null) {
//            List<Address> addresses = distManager.locate(k);
//            Object o = getObject(k);
//            List<String> topologyAddresses = new ArrayList<String>();
//            for (Address address : addresses) {
//               TopologyAddress topologyAddress = lookupTopologyAddress(address, topologyCache);
//               topologyAddresses.add("[" + topologyAddress.host + ":" + topologyAddress.port + "]");
//            }
//
//            log.info("[{0}:{1}] entry created, {2} can be located in {3}", host, port, o, topologyAddresses);
//         }
//      }
//   }
//
//   public TopologyAddress lookupTopologyAddress(Address a, Cache<String, TopologyView> topologyCache) {
////      TopologyView view = topologyCache.get("view");
////      for (TopologyAddress member : view.members) {
////         if (member.clusterAddress == a) {
////            return member
////         }
////      }
//      return null;
//   }
//
//   private Object getObject(ByteArrayKey k) throws Exception {
//      return marshaller.objectFromByteBuffer(k.getData());
//   }
//
}
