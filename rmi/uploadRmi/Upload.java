package uploadRmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Upload extends Remote {
  boolean upload(String fileName, byte[] bytes) throws RemoteException;
}