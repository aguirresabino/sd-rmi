import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

public class Loader {

    private static final int MAX = 1000;
    private static final Logger log = Logger.getLogger(Loader.class.getName());

    public static void main(String args[]) throws RemoteException, NotBoundException {

        final ArrayBlockingQueue<Entidade> updateQueue = new ArrayBlockingQueue(3);
        final ArrayBlockingQueue<Entidade> deleteQueue = new ArrayBlockingQueue(3);
        final Repository repository = new Repository();

        final IdentityManager identityManager = (IdentityManager) LocateRegistry
                .getRegistry(2000)
                .lookup("IdentityManager");

        Runnable inserir = () -> {
            try {
                Entidade e = new Entidade(identityManager.getId());
                repository.inserir(e);
                updateQueue.put(e);
                log.info("[INSERIR] " + "[" + e + "]");
            } catch (InterruptedException | RemoteException e) {
                e.printStackTrace();
            }
        };

        Runnable editar = () -> {
            try {
                Entidade e = updateQueue.take();
                e.setEditado(true);
                repository.atualizar(e.getId());
                log.info("[EDITAR] " + "[" + e + "]");
                deleteQueue.put(e);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        Runnable deletar = () -> {
            try {
                Entidade e = deleteQueue.take();
                e.setExcluido(true);
                repository.excluir(e.getId());
                log.info("[DELETAR] "  + "[" + e + "]");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        final long tempoInicial = System.currentTimeMillis();
        for(int i = 0; i < MAX; i++) {
            new Thread(inserir).run();
            new Thread(editar).run();
            new Thread(deletar).run();
        }
        final long tempoFinal = System.currentTimeMillis();

        final long tempoTotalEmMilissegundo = tempoFinal - tempoInicial;
        final long minutos = (tempoTotalEmMilissegundo / 1000) / 60;
        final long segundos = (tempoTotalEmMilissegundo / 1000) % 60;

        log.info("[TEMPO DE EXECUCÃO] " + String.format("[%d MINUTOS E %d SEGUNDOS]", minutos, segundos));
    }
}
