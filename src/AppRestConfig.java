import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest")
public class AppRestConfig extends ResourceConfig {
   public AppRestConfig(){
       packages("rest");
   }
}
