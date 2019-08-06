import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class Loader {

    private static final Logger log = Logger.getLogger(Loader.class.getName());

    public static void main(String[] args) {

        try {
            IdentityManager stub = (IdentityManager) UnicastRemoteObject
                    .exportObject(new IdentityManagerImpl(), 0);
            Registry registry = LocateRegistry.createRegistry(2000);
            registry.rebind("IdentityManager", stub);
            log.info("[SERVIDOR EXECUTANDO]");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
