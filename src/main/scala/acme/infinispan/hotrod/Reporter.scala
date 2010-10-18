package acme.infinispan.hotrod

import org.infinispan.notifications.Listener
import org.infinispan.server.core.Logging
import org.infinispan.notifications.cachelistener.annotation.{CacheEntryCreated, CacheEntryVisited}
import org.infinispan.notifications.cachelistener.event.CacheEntryEvent
import org.infinispan.remoting.transport.Address
import org.infinispan.Cache
import scala.collection.JavaConversions._
import collection.mutable.{ListBuffer}
import org.infinispan.util.ByteArrayKey
import org.infinispan.server.hotrod.{TopologyAddress, TopologyView}
import org.infinispan.marshall.jboss.GenericJBossMarshaller

/**
 * NOTE!!! There's a very good reason for not writing this in Java, and that is the fact that Java cannot easily loop
 * through scala.List. So, it's easier to build the listener on Scala.
 *
 * @author Galder Zamarreño
 * @since 1.0
 */
@Listener
class Reporter(host: String, port: Int) extends Logging {

   val marshaller = new GenericJBossMarshaller

   @CacheEntryVisited
   def visisted(e: CacheEntryEvent) {
      if (!e.isPre) {
         val k = e.getKey.asInstanceOf[ByteArrayKey]
         val o = getObject(k)
         info("[{0}:{1}] Visited cache entry with k={2}", host, port, o)
      }
   }

   @CacheEntryCreated
   def created(e: CacheEntryEvent) {
      if (!e.isPre && e.isOriginLocal) {
         val topologyCache: Cache[String, TopologyView] = e.getCache.getCacheManager.getCache("___hotRodTopologyCache")
         val k = e.getKey.asInstanceOf[ByteArrayKey]
         val o = getObject(k)
         val distManager = e.getCache.getAdvancedCache.getDistributionManager
         if (distManager != null) {
            val addresses = distManager.locate(k)
            val topologyAddresses = new ListBuffer[String]
            for (address <- asIterator(addresses.iterator)) {
               val topologyAddress = lookupTopologyAddress(address, topologyCache)
               topologyAddresses += "[" + topologyAddress.host + ":" + topologyAddress.port + "]"
            }
            info("[{0}:{1}] Created cache entry with k={2}, can be located in {3}", host, port, o, topologyAddresses.toList)
         } else {
            // It's replicated
            info("[{0}:{1}] Created cache entry with k={2}", host, port, o)
         }
      }
   }

   def lookupTopologyAddress(a: Address, topologyCache: Cache[String, TopologyView]): TopologyAddress = {
      val view = topologyCache.get("view")
      for (member <- view.members) {
         if (member.clusterAddress == a) {
            return member
         }
      }
      return null
   }

   def getObject(k: ByteArrayKey): Any = marshaller.objectFromByteBuffer(k.getData)
   // new ObjectInputStream(new ByteArrayInputStream(k.getData)).readObject

}