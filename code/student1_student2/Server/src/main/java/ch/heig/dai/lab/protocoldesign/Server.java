package ch.heig.dai.lab.protocoldesign;

import java.io.*;
import java.net.*;

import static java.nio.charset.StandardCharsets.*;

public class Server {
    final int SERVER_PORT = 5555;

    public static void main(String[] args) {
        // Create a new server and run it
        Server server = new Server();
        server.run();
    }

    private void run() {
        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(
                                 socket.getInputStream(), UTF_8));
                 BufferedWriter out = new BufferedWriter(
                         new OutputStreamWriter(
                                 socket.getOutputStream(), UTF_8))) {
                out.write("Hello! Sur ce serveur vous pouvez effectuer des calculs avec les opérations suivantes:\nAddition, Soustraction, Multiplication\n");
                out.flush();

                String line;
                int operand = 0;
                int operand1 = 0;
                int operand2;
                int resultat;
                int nbOperand = 0;
                while(nbOperand != 2){
                    operand1 = operand;
                    while ((line = in.readLine()) != null){
                        if (line.contains("OPERAND")) {
                            line = line.substring(7);
                            try {
                                operand = Integer.parseInt(line);
                                nbOperand++;
                            } catch (NumberFormatException e) {
                                out.write("ERROR_OPERAND" + line);
                                out.flush();
                                nbOperand--;
                            }
                        }
                        break;
                    }
                }
                operand2 = operand;

                int tentatives = 0;
                label:
                while (tentatives < 3 && (line = in.readLine()) != null){
                    tentatives++;
                    if (line.contains("OPERATOR")) {
                        line = line.substring(8);
                        switch (line) {
                            case "+":
                                resultat = operand1 + operand2;
                                out.write("RESULT" + resultat);
                                out.flush();
                                break label;
                            case "*":
                                resultat = operand1 * operand2;
                                out.write("RESULT" + resultat);
                                out.flush();
                                break label;
                            case "-":
                                resultat = operand1 - operand2;
                                out.write("RESULT" + resultat);
                                out.flush();
                                break label;
                            default:
                                out.write("WRONG_OPERATOR Try Again\n");
                                out.flush();
                                break;
                        }
                    }
                }
                if (tentatives >= 3){
                    out.write("WRONG_OPERATOR End of Program\n");
                    out.flush();
                }

            } catch (IOException e) {
                System.out.println("Server: socket ex.: " + e);
            }
        } catch (IOException e) {
            System.out.println("Server: server socket ex.: " + e);
        }
    }
}