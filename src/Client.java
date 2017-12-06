/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author qwerty
 */
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] ar) {
        int serverPort = 6666; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "93.77.82.13"; // это IP-адрес компьютера, где исполняется наша серверная программа. 
                                      // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Кто-то слашал о ip адресе " + address + " с портом " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом. 
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Напечатайте что-то и нажмите  Enter, это отправиться на сервер!");
            System.out.println();

            while (true) {
                line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                System.out.println("1 Отправляю на сервер...");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println("2 Сервер ответил " + line);
                System.out.println();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
