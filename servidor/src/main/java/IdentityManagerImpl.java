import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class IdentityManagerImpl implements IdentityManager {

    private static final Logger log = Logger.getLogger(IdentityManagerImpl.class.getName());
    private static AtomicInteger atomicInteger;

    {
        atomicInteger = new AtomicInteger(1);
    }

    public int getId() throws RemoteException {
        log.info(String.format("[IDENTIFICADOR GERADO] [%d]", atomicInteger.get()));
        return atomicInteger.getAndIncrement();
    }
}
