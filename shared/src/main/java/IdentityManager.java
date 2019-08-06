import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IdentityManager extends Remote {
    int getId() throws RemoteException;
}
