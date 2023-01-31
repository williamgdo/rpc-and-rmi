package uploadRmi;

import java.rmi.registry.Registry;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements uploadRmi.Upload {
  public Server() {
  }

  // public byte[] download(String fileName) {
  // try {
  // File file = new File("/" + fileName);
  // byte buffer[] = new byte[(int) file.length()];
  // BufferedInputStream input = new BufferedInputStream(new
  // FileInputStream(file.getAbsolutePath()));
  // input.read(buffer, 0, buffer.length);
  // input.close();
  // return (buffer);
  // } catch (Exception e) {
  // e.printStackTrace();
  // return (null);
  // }
  // }

  @Override
  public boolean upload(String fileName, byte[] bytes) {
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
