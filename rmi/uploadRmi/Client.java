package uploadRmi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
  private Client() {
  }

  // To convert file to byte array
  public static byte[] fileToBytes(File file) throws IOException {
    // le os dados de um arquivo
    FileInputStream fl = new FileInputStream(file);

    // cria um array de bytes do mesmo tamanho de "file"
    byte[] arr = new byte[(int) file.length()];

    // transfere os dados do FileInputStream para o array de bytes
    fl.read(arr);

    // evita vazamento de memoria
    fl.close();

    return arr;
  }

  public static void main(String[] args) {
    // String host = (args.length < 1) ? null : args[0];

    if (args.length < 1) {
      System.out.println("Digite o nome de um arquivo");
      return;
    }

    String fileName = args[0];

    try {
      Registry registry = LocateRegistry.getRegistry();
      // Obtém referência para o registry

      uploadRmi.Upload stub = (uploadRmi.Upload) registry.lookup("Upload");
      // Procura pelo serviço remoto e obtém stub

      File path = new File(fileName);

      byte[] array = fileToBytes(path);

      Boolean response = stub.upload(fileName, array);

      if (response == true)
        System.out.println("Sucesso ao fazer upload de arquivo");
      else
        System.out.println("Erro ao fazer upload de arquivo");
    } catch (Exception e) {
      System.err.println("Client exception: " + e.toString());
      e.printStackTrace();
    }
  }
}
