package uploadRmi;

import java.rmi.registry.Registry;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements uploadRmi.Upload {
  public Server() {
  }

  @Override
  public boolean upload(String fileName, byte[] bytes) {
    System.out.println("Salvando arquivo " + fileName);
    try {
      String newFileName = "uploaded_" + fileName;
      try (FileOutputStream stream = new FileOutputStream(newFileName)) {
        stream.write(bytes);
      } catch (IOException e) {
        e.printStackTrace();
      }

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static void main(String args[]) {
    try {
      Server obj = new Server();
      // Cria o objeto

      Upload stub = (Upload) UnicastRemoteObject.exportObject(obj, 0);
      // exporta

      // System.setProperty("java.rmi.server.hostname", "192.168.x.x");
      // Registry registry = LocateRegistry.createRegistry(1099);

      Registry registry = LocateRegistry.getRegistry();
      // Registra objeto remoto no registry

      registry.bind("Upload", stub);
      System.err.println("Server ready");
    } catch (Exception e) {
      System.err.println("Server exception: " + e.toString());
      e.printStackTrace();
    }
  }
}
